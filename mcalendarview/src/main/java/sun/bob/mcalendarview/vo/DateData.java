package sun.bob.mcalendarview.vo;

import sun.bob.mcalendarview.MarkStyle;

/**
 * Created by bob.sun on 15/8/27.
 */
public class DateData {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private MarkStyle markStyle;

    public DateData(int year, int month, int day){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = 0;
        this.minute = 0;
        this.markStyle = new MarkStyle();
    }

    public int getYear() {
        return year;
    }

    public DateData setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthString(){
        return month > 9 ? String.format("%d", month) : String.format("0%d", month);
    }

    public DateData setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getDay() {
        return day;
    }

    public String getDayString(){
        return day > 9 ? String.format("%d", day) : String.format("0%d", day);
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public String getHourString(){
        return hour > 9 ? String.format("%d", hour) : String.format("0%d", hour);
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getMinuteString(){
        return minute > 9 ? String.format("%d", minute) : String.format("0%d", minute);
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public boolean equals(Object o) {
        DateData data = (DateData) o;
        return  ((data.year == this.year) && (data.month == this.month) && (data.day == this.day));
    }

    public MarkStyle getMarkStyle() {
        return markStyle;
    }

    public DateData setMarkStyle(MarkStyle markStyle) {
        this.markStyle = markStyle;
        return this;
    }

    public DateData setMarkStyle(int style, int color){
        this.markStyle = new MarkStyle(style, color);
        return this;
    }
}
