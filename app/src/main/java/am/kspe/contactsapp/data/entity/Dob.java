package am.kspe.contactsapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Dob implements Parcelable {

    private String date;
    private int age;

    public Dob() {
    }

    protected Dob(Parcel in) {
        date = in.readString();
        age = in.readInt();
    }

    public static final Creator<Dob> CREATOR = new Creator<Dob>() {
        @Override
        public Dob createFromParcel(Parcel in) {
            return new Dob(in);
        }

        @Override
        public Dob[] newArray(int size) {
            return new Dob[size];
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

    public String date() {
        return date;
    }
}
