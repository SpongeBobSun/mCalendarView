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
}
