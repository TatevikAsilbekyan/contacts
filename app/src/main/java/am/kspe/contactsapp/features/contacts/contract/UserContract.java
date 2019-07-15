package am.kspe.contactsapp.features.contacts.contract;

import androidx.annotation.NonNull;

import am.kspe.contactsapp.data.entity.User;

public interface UserContract {

    interface OnUserItemClickListener {
        void onUserItemClicked(@NonNull User user);
    }

    interface OnUserInteractionListener {
        void onViewReady();
        void onUserViewScrolled();
        void onUserDetailOpened(@NonNull User user);
    }
}
