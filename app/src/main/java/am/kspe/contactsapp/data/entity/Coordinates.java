package am.kspe.contactsapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Coordinates implements Parcelable {

    private String latitude;
    private String longitude;

    public Coordinates() {
    }

    protected Coordinates(Parcel in) {
        latitude = in.readString();
        longitude = in.readString();
    }

    public static final Creator<Coordinates> CREATOR = new Creator<Coordinates>() {
        @Override
        public Coordinates createFromParcel(Parcel in) {
            return new Coordinates(in);
        }

        @Override
        public Coordinates[] newArray(int size) {
            return new Coordinates[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(latitude);
        dest.writeString(longitude);
    }

    public String latitude() {
        return latitude;
    }

    public String longitude() {
        return longitude;
    }
}
