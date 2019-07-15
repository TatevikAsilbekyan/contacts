package am.kspe.contactsapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Login extends RealmObject implements Parcelable {

    @PrimaryKey
    private String uuid;
    private String username;

    public Login() {
    }

    protected Login(Parcel in) {
        uuid = in.readString();
        username = in.readString();
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(username);
    }

    public String id() {
        return uuid;
    }

    public String username() {
        return username;
    }
}
