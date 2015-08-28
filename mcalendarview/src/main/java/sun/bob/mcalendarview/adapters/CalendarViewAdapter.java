package sun.bob.mcalendarview.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import sun.bob.mcalendarview.fragments.MonthFragment;
import sun.bob.mcalendarview.utils.CalendarUtil;
import sun.bob.mcalendarview.vo.DateData;
import sun.bob.mcalendarview.vo.MonthData;

/**
 * Created by bob.sun on 15/8/27.
 */
public class CalendarViewAdapter extends FragmentStatePagerAdapter {

    private DateData date;
    public CalendarViewAdapter(FragmentManager fm) {
        super(fm);
    }

    public CalendarViewAdapter setDate(DateData date){
        this.date = date;
        return this;
    }

    @Override
    public Fragment getItem(int position) {
        int year = CalendarUtil.position2Year(position);
        int month = CalendarUtil.position2Month(position);

        MonthFragment fragment = new MonthFragment();
        MonthData monthData = new MonthData(new DateData(year, month, date.getDay()));
        fragment.setData(monthData);
        return fragment;
    }
    @Override
    public int getCount() {
        return 1000;
    }
}
