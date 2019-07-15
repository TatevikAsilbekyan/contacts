package am.kspe.contactsapp.helpers;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import am.kspe.contactsapp.features.contacts.ui.ContactsActivity;

public final class NavigationHelper {

    private NavigationHelper() {
    }

    public static void goToMainPage(@NonNull Context context) {
        context.startActivity(new Intent(context, ContactsActivity.class));
    }
}
