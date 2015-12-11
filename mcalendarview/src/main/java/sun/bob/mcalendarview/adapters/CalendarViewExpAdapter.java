package sun.bob.mcalendarview.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import sun.bob.mcalendarview.views.ExpCalendarView;
import sun.bob.mcalendarview.views.MonthExpFragment;
import sun.bob.mcalendarview.vo.DateData;

/**
 * Created by 明明大美女 on 2015/12/8.
 */
public class CalendarViewExpAdapter extends FragmentStatePagerAdapter {

    private DateData date;

    private int dateCellId;
    private int markCellId;
    private boolean hasTitle = true;

    private Context context;
    private int mCurrentPosition = -1;

    public CalendarViewExpAdapter(FragmentManager fm) {
        super(fm);
    }

    public CalendarViewExpAdapter setDate(DateData date){
        this.date = date;
        return this;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CalendarViewExpAdapter setDateCellId(int dateCellRes){
        this.dateCellId =  dateCellRes;
        return this;
    }


    public CalendarViewExpAdapter setMarkCellId(int markCellId){
        this.markCellId = markCellId;
        return this;
    }


    @Override
    public Fragment getItem(int position) {
        MonthExpFragment fragment = new MonthExpFragment();
        fragment.setData(position, dateCellId, markCellId);
        return fragment;
    }

    @Override
    public int getCount() {
        return 1000;
    }

    public CalendarViewExpAdapter setTitle(boolean hasTitle){
        this.hasTitle = hasTitle;
        return this;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        ((ExpCalendarView) container).measureCurrentView(position);
    }

    /**
     * 重写该方法，为了刷新页面
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        if (object.getClass().getName().equals(MonthExpFragment.class.getName())) {
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

}
