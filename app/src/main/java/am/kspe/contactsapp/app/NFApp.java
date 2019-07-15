package am.kspe.contactsapp.app;

import android.app.Application;

import am.kspe.contactsapp.helpers.Configurator;

public class NFApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        final Configurator config = new Configurator(this);
        config.init();
    }
}
