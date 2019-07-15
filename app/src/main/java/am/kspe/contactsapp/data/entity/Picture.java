package am.kspe.contactsapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable {

    private static final String COVER = "https://i.ytimg.com/vi/jxqVL2txv20/maxresdefault.jpg";
    private static final String PLACEHOLDER = "https://www.voanews.com/themes/custom/voa/images/Author__Placeholder.png";

    private String large;
    private String medium;
    private String thumbnail;

    public Picture() {
    }

    protected Picture(Parcel in) {
        large = in.readString();
        medium = in.readString();
        thumbnail = in.readString();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(large);
        dest.writeString(medium);
        dest.writeString(thumbnail);
    }

    public String avatar() {
        return large == null ? Picture.PLACEHOLDER : large;
    }

    public String cover() {
        return Picture.COVER;
    }
}
