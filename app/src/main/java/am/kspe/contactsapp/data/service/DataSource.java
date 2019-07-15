package am.kspe.contactsapp.data.service;

import androidx.annotation.NonNull;

import java.util.List;

import am.kspe.contactsapp.data.entity.User;
import io.reactivex.Single;

public interface DataSource {
    Single<List<User>> getUsers(int page, int pageSize);
    void saveUser(@NonNull User user);
    void getSavedUsers();
}
