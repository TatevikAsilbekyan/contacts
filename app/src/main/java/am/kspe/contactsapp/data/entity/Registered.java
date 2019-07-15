package am.kspe.contactsapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Registered implements Parcelable {

    private String date;
    private int age;

    public Registered() {
    }

    protected Registered(Parcel in) {
        date = in.readString();
        age = in.readInt();
    }

    public static final Creator<Registered> CREATOR = new Creator<Registered>() {
        @Override
        public Registered createFromParcel(Parcel in) {
            return new Registered(in);
        }

        @Override
        public Registered[] newArray(int size) {
            return new Registered[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeInt(age);
    }
}
