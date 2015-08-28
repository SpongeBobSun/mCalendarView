package sun.bob.mcalendarview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.Date;

import sun.bob.mcalendarview.adapters.CalendarViewAdapter;
import sun.bob.mcalendarview.utils.CurrentCalendar;
import sun.bob.mcalendarview.vo.DateData;

/**
 * Created by bob.sun on 15/8/27.
 */
public class mCalendarView extends ViewPager {
    private int dateCellViewResId = -1;
    private View dateCellView = null;
    private int markedStyle = -1;
    private int markedCellResId = -1;
    private View markedCellView = null;

    private boolean initted = false;

    private DateData currentDate;


    public mCalendarView(Context context) {
        super(context);
        if (context instanceof FragmentActivity){
            init((FragmentActivity) context);
        }
    }



    public mCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (context instanceof FragmentActivity){
            init((FragmentActivity) context);
        }
    }

    public void init(FragmentActivity activity){
        if (initted){
            return;
        }
        initted = true;
        if (currentDate == null){
            currentDate = CurrentCalendar.getCurrentDateData();
        }
        //Todo
        //Will this cause trouble when archive this module to a JAR?
        if (this.getId() == View.NO_ID){
            this.setId(R.id.calendarViewPager);
        }
        //Todo
        //Make CalendarViewAdapter public accessable
        this.setAdapter(new CalendarViewAdapter(activity.getSupportFragmentManager()).setDate(CurrentCalendar.getCurrentDateData()));
        this.setCurrentItem(500);
    }

    public mCalendarView travelTo(DateData dateData){
        this.currentDate = dateData;
        return this;
    }

    public mCalendarView markDate(int year, int month, int day){

        return this;
    }

    public mCalendarView setDateCellViewResId(int resId){

        return this;
    }

    public mCalendarView setDateCellView(View view){

        return this;
    }

    public mCalendarView setMarkedStyle(int style){

        return this;
    }

    public mCalendarView setMarkedCell(int resId){

        return this;
    }

    public mCalendarView setMarkedCellView(View view){

        return this;
    }
}
