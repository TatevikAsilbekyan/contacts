package am.kspe.contactsapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {

    private String street;
    private String city;
    private String state;
    private String postcode;
    private Coordinates coordinates;
    private Timezone timezone;

    public Location() {
    }

    protected Location(Parcel in) {
        street = in.readString();
        city = in.readString();
        state = in.readString();
        postcode = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(postcode);
    }

    public String shortAddress() {
        return Character.toUpperCase(state.charAt(0)) + state.substring(1) + ", " +
                Character.toUpperCase(city.charAt(0)) + city.substring(1);
    }

    public String address() {
        return postcode + ", " +
                Character.toUpperCase(street.charAt(0)) + street.substring(1) + ", " +
                Character.toUpperCase(city.charAt(0)) + city.substring(1) + ", " +
                Character.toUpperCase(state.charAt(0)) + state.substring(1);
    }

    public String coordinates() {
        return coordinates.latitude() + "," + coordinates.longitude();
    }

    public String timezone() {
        return timezone.offset() + ", " + timezone.description();
    }
}
