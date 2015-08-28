package sun.bob.mcalendarview.vo;

/**
 * Created by bob.sun on 15/8/27.
 */
public class DateData {
    private int year;
    private int month;
    private int day;

    public DateData(int year, int month, int day){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
