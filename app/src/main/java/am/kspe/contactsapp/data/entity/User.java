package am.kspe.contactsapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class User implements Parcelable {

    private static final String FLAG_URL = "https://www.countryflags.io/%s/shiny/64.png";

    private String gender;
    private Name name;
    private Location location;
    private String email;
    private Login login;
    private Dob dob;
    private Registered registered;
    private String phone;
    private String cell;
    private Picture picture;
    private String nat;
    private boolean seen;

    public User() {
    }

    protected User(Parcel in) {
        gender = in.readString();
        email = in.readString();
        phone = in.readString();
        cell = in.readString();
        nat = in.readString();
        seen = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gender);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(cell);
        dest.writeString(nat);
        dest.writeByte((byte) (seen ? 1 : 0));
    }

    public String avatarUrl() {
        return picture.avatar();
    }

    public String coverUrl() {
        return picture.cover();
    }

    public String fullName() {
        return name.firstName() + " " + name.lastName();
    }

    public String fullNameWithTitle() {
        return name.title() + " " + name.firstName() + " " + name.lastName();
    }

    public String birthday() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss'Z'", Locale.getDefault());
            Date date = dateFormat.parse(dob.date());
            dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dob.date();
    }

    public String shortAddress() {
        return location.shortAddress();
    }

    public String address() {
        return location.address();
    }

    public String phone() {
        return phone;
    }

    public String phones() {
        return cell + ", " + phone;
    }

    public String id() {
        return login.id();
    }

    public Login login() {
        return login;
    }

    public String flagUrl() {
        return String.format(FLAG_URL, nat);
    }

    public String cell() {
        return cell;
    }

    public String email() {
        return email;
    }

    public String location() {
        return location.coordinates();
    }

    public boolean seen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String username() {
        return login.username();
    }

    public String timezone() {
        return location.timezone();
    }
}
