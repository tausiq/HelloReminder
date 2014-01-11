package ms.cloudtea.HelloReminder.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shahab on 1/11/14.
 */
public class CalendarModel implements Parcelable {

    private long calendarId;

    private String displayName;

    public CalendarModel(long calendarId, String displayName) {
        this.calendarId = calendarId;
        this.displayName = displayName;
    }

    public long getCalendarId() {
        return calendarId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(getCalendarId());
        parcel.writeString(getDisplayName());
    }

    public static final Parcelable.Creator<CalendarModel> CREATOR = new Parcelable.Creator<CalendarModel>() {
        public CalendarModel createFromParcel(Parcel in) {
            return new CalendarModel(in);
        }

        public CalendarModel[] newArray(int size) {
            return new CalendarModel[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private CalendarModel(Parcel in) {
        setCalendarId(in.readLong());
        setDisplayName(in.readString());
    }
}
