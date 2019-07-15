package am.kspe.contactsapp.data;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import am.kspe.contactsapp.api.ApiService;
import am.kspe.contactsapp.data.entity.Login;
import am.kspe.contactsapp.data.entity.User;
import am.kspe.contactsapp.data.service.DataSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Repository implements DataSource {

    private static final String BASE_URL = "https://randomuser.me/";
    private static final String SEED = "abc";

    private ApiService service;
    private Realm realm;
    private RealmResults<Login> users;

    public Repository() {
        init();
    }

    private void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);

        service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder().addInterceptor(logging).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);

        realm = Realm.getDefaultInstance();

        getSavedUsers();
    }

    @Override
    public Single<List<User>> getUsers(int page, int pageSize) {
        return service.getUsers(page, pageSize, SEED)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response.results != null) {
                        for (User user : response.results) {
                            final Login login = users.where().contains("uuid", user.id()).findFirst();
                            if (login != null) {
                                user.setSeen(true);
                            }
                        }
                    } else {
                        response.results = Collections.emptyList();
                    }

                    return response.results;
                })
                .onErrorReturn(throwable -> Collections.emptyList());
    }

    @Override
    public void getSavedUsers() {
        users = realm.where(Login.class).findAll();
    }

    @Override
    public void saveUser(@NonNull User user) {
        realm.executeTransaction(realm -> realm.insertOrUpdate(user.login()));
    }
}
