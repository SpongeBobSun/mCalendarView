package sun.bob.simplecalendarview;

/**
 * Created by sunkuan on 15/3/18.
 */
public class mTitleData extends mDateData {
    private String title;
    private boolean isTitle;
    public mTitleData(int day) {
        super(day);
        isTitle = true;
        switch (day){
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTitle() {
        return isTitle;
    }
}
