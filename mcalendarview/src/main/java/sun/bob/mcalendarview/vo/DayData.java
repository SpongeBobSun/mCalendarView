package sun.bob.mcalendarview.vo;

/**
 * Created by bob.sun on 15/8/27.
 */
public class DayData {

    private DateData date;
    private int textColor;
    private int textSize;

    public DayData(DateData date){
        this.date = date;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public String getText(){
        return "" + date.getDay();
    }

    public DateData getDate(){
        return date;
    }

}
