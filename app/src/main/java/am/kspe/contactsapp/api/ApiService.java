package am.kspe.contactsapp.api;

import am.kspe.contactsapp.data.entity.Response;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/api")
    Single<Response> getUsers(@Query("page") int page, @Query("results") int pageSize, @Query("seed") String seed);
}
