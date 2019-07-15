package am.kspe.contactsapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Timezone implements Parcelable {

    private String offset;
    private String description;

    public Timezone() {
    }

    protected Timezone(Parcel in) {
        offset = in.readString();
        description = in.readString();
    }

    public static final Creator<Timezone> CREATOR = new Creator<Timezone>() {
        @Override
        public Timezone createFromParcel(Parcel in) {
            return new Timezone(in);
        }

        @Override
        public Timezone[] newArray(int size) {
            return new Timezone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(offset);
        dest.writeString(description);
    }

    public String offset() {
        return offset;
    }

    public String description() {
        return description;
    }
}
