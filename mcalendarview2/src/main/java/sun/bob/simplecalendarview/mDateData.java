package sun.bob.simplecalendarview;

/**
 * Created by sunkuan on 15/3/17.
 */
public class mDateData {
    private int Day;
    private boolean Blank;
    private boolean isMarked;
    private int MarkColor;
    private int MarkStyle;
    private int Month;
    private int textColor;
    private int textSize;

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

    public mDateData(int day){
        if(day == 0){
            setBlank();
        }
        Day = day;
    }

    public int getDay() {
        return Day;
    }


    public boolean isBlank() {
        return Blank;
    }

    public void setBlank() {
        Blank = true;
    }

    public int getMarkStyle() {
        return MarkStyle;
    }

    public void setMarkStyle(int markStyle) {
        MarkStyle = markStyle;
    }

    public int getMarkColor() {
        return MarkColor;
    }

    public void setMarkColor(int markColor) {
        MarkColor = markColor;
    }
    public boolean isMarked() {
        return isMarked;
    }

    public void mark(int color, int style) {
        isMarked = true;
        MarkColor = color;
        MarkStyle = style;
    }
    public void setMonth(int month){
        this.Month = month;
    }
    public int getMonth(){
        return this.Month;
    }
}

