package am.kspe.contactsapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Name implements Parcelable {

    private String title;
    private String first;
    private String last;

    public Name() {
    }

    protected Name(Parcel in) {
        title = in.readString();
        first = in.readString();
        last = in.readString();
    }

    public static final Creator<Name> CREATOR = new Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel in) {
            return new Name(in);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(first);
        dest.writeString(last);
    }

    public String title() {
        return Character.toUpperCase(title.charAt(0)) + title.substring(1);
    }

    public String firstName() {
        return Character.toUpperCase(first.charAt(0)) + first.substring(1);
    }

    public String lastName() {
        return Character.toUpperCase(last.charAt(0)) + last.substring(1);
    }
}
