package sun.bob.mcalendarview.listeners;

import android.support.v4.view.ViewPager;

import sun.bob.mcalendarview.CellConfig;
import sun.bob.mcalendarview.utils.ExpCalendarUtil;
import sun.bob.mcalendarview.vo.DateData;

/**
 * Created by Bigflower on 2015/12/8.
 *
 * add a onMonthScroll . the aim is for cool effect
 */
public abstract class OnMonthScrollListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        onMonthScroll(positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        CellConfig.middlePosition = position;
        DateData date;
        if (CellConfig.ifMonth)
            date = ExpCalendarUtil.position2Month(position);
        else
            date = ExpCalendarUtil.position2Week(position);
        onMonthChange(date.getYear(), date.getMonth());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public abstract void onMonthChange(int year, int month);

    public abstract void onMonthScroll(float positionOffset);
}
