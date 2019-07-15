package am.kspe.contactsapp.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;

public final class UIHelper {

    private UIHelper() {
    }

    public static void asyncLoadThumb(@NonNull ImageView target, @Nullable String url) {
        Glide.with(target.getContext())
                .load(url)
                .into(target);
    }

    public static void asyncLoadAvatar(@NonNull ImageView target, @Nullable String url) {
        Glide.with(target.getContext())
                .load(url)
                .circleCrop()
                .into(target);
    }

    public static boolean isNetworkAvailable(@NonNull Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }

    public static void showSnackBar(@NonNull View view, @StringRes int resId) {
        Snackbar.make(view, resId, LENGTH_LONG).show();
    }

    public static void call(@NonNull Activity activity, @NonNull String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+ phoneNumber));

        if (Build.VERSION.SDK_INT < 23) {
            activity.startActivity(callIntent);
        } else {
            if (ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                activity.startActivity(callIntent);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 0);
            }
        }
    }

    public static void email(@NonNull Context context, @NonNull String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", email, null));
        context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public static void direction(@NonNull Context context, @NonNull String coordinates) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:" + coordinates));
        context.startActivity(intent);
    }
}