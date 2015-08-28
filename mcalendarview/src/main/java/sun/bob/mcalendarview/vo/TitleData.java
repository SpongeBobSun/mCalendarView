package sun.bob.mcalendarview.vo;

/**
 * Created by bob.sun on 15/8/27.
 */
public class TitleData extends DayData{
    private String title;
    private boolean isTitle;
    public TitleData(DateData dayData) {
        super(dayData);
        isTitle = true;
        switch (dayData.getDay()){
            case 1:
                setTitle("Sun");
                break;
            case 2:
                setTitle("Mon");
                break;
            case 3:
                setTitle("Tue");
                break;
            case 4:
                setTitle("Wed");
                break;
            case 5:
                setTitle("Thu");
                break;
            case 6:
                setTitle("Fri");
                break;
            case 7:
                setTitle("Sat");
                break;
            default:
                break;
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
