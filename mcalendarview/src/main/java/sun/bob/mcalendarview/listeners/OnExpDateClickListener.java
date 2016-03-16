package sun.bob.mcalendarview.listeners;

import android.view.View;

import sun.bob.mcalendarview.utils.CurrentCalendar;
import sun.bob.mcalendarview.views.DefaultCellView;
import sun.bob.mcalendarview.vo.DateData;

/**
 * Created by Bigflower on 2015/12/10.
 * <p>
 * 分别要对上次的和这次的处理
 * 而今日和其他日也有区别 所以有两个判断
 * 1.对上次的点击判断
 * 2.对这次的点击判断
 */
public class OnExpDateClickListener extends OnDateClickListener {

    private View lastClickedView;
    private DateData lastClickedDate = CurrentCalendar.getCurrentDateData();

    @Override
    public void onDateClick(View view, DateData date) {

        if(view instanceof DefaultCellView) {

            // 判断上次的点击
            if (lastClickedView != null) {
                // 节约！
                if (lastClickedView == view)
                    return;
                if (lastClickedDate.equals(CurrentCalendar.getCurrentDateData())) {
                    ((DefaultCellView) lastClickedView).setDateToday();
                } else {
                    ((DefaultCellView) lastClickedView).setDateNormal();
                }
            }
            // 判断这次的点击
            ((DefaultCellView) view).setDateChoose();
            lastClickedView = view;
            lastClickedDate = date;
        }


    }

}
