package sun.bob.mcalendarview.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by bob.sun on 15/8/27.
 */
public class TitleData extends DayData {
    private String title;
    private boolean isTitle;
    Calendar cal = Calendar.getInstance();

    public TitleData(DateData dayData) {
        super(dayData);
        isTitle = true;
        switch (dayData.getDay()) {
            case 1:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                break;
            case 2:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                break;
            case 3:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                break;
            case 4:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                break;
            case 5:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                break;
            case 6:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                break;
            case 7:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                break;
            default:
                break;
        }

        String dayValue = new SimpleDateFormat("EEE").format(new java.util.Date(cal.getTimeInMillis())).replaceAll("\\.", "");
        if (dayValue.length() > 1) {
            String DayValueUpperCased = dayValue.substring(0, 1).toUpperCase() + dayValue.substring(1);
            setTitle(DayValueUpperCased);
        }
    }

    @Override
    public String getText() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTitle() {
        return isTitle;
    }
}
