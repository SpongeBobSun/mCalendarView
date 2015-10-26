package sun.bob.mcalendarview.vo;

/**
 * Created by bob.sun on 15/8/27.
 */
public class DateData {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DateData(int year, int month, int day){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = 0;
        this.minute = 0;
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

    public int getHour() {
        return hour;
    }

    public String getHourString(){
        return hour > 10 ? String.format("%d", hour) : String.format("0%d", hour);
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getMinuteString(){
        return minute > 10 ? String.format("%d", minute) : String.format("0%d", minute);
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public boolean equals(Object o) {
        DateData data = (DateData) o;
        return  ((data.year == this.year) && (data.month == this.month) && (data.day == this.day));
    }
}
