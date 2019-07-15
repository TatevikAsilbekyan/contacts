package am.kspe.contactsapp.features.landing;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import am.kspe.contactsapp.R;
import am.kspe.contactsapp.helpers.NavigationHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            NavigationHelper.goToMainPage(SplashActivity.this);
            finish();
            }, 1500);
    }
}
