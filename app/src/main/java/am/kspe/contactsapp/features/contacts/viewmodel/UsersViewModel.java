package am.kspe.contactsapp.features.contacts.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Collections;
import java.util.List;

import am.kspe.contactsapp.R;
import am.kspe.contactsapp.data.Repository;
import am.kspe.contactsapp.data.entity.User;
import am.kspe.contactsapp.helpers.UIHelper;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class UsersViewModel extends AndroidViewModel {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final Context context;
    private final CompositeDisposable disposable;
    private final Repository repository;

    private int currentPage = 1;

    private MutableLiveData<List<User>> data = new MutableLiveData<>();
    private MutableLiveData<Integer> error = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public UsersViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
        disposable = new CompositeDisposable();
        repository = new Repository();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public void loadData() {
        if (isNetworkAvailable()) {
            getOnlineContent();
        } else {
            data.setValue(Collections.emptyList());
            error.setValue(R.string.no_internet);
        }
    }

    public void loadMoreUsers() {
        loadData();
    }

    public void saveSeenUser(@NonNull User user) {
        user.setSeen(true);
        repository.saveUser(user);
    }

    public MutableLiveData<List<User>> loadUsers() {
        return data;
    }

    public MutableLiveData<Integer> getError() {
        return error;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    private void getOnlineContent() {
        disposable.add(getUsers(currentPage)
                .doOnSubscribe(disposable -> loading.postValue(true))
                .subscribe(contents -> {
                    if (contents.size() > 0) {
                        ++currentPage;
                    }
                    data.setValue(contents);
                    loading.setValue(false);
                }, Throwable::printStackTrace));
    }

    private Single<List<User>> getUsers(int page) {
        return repository.getUsers(page, DEFAULT_PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private boolean isNetworkAvailable() {
        return UIHelper.isNetworkAvailable(context);
    }
}
