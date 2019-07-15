package am.kspe.contactsapp.helpers;

import android.content.Context;

import io.realm.Realm;

public class Configurator {

    private Context context;

    public Configurator(Context context) {
        this.context = context;
    }

    public void init() {
        Realm.init(context);
    }
}
