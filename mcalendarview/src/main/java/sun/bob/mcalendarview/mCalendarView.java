package sun.bob.mcalendarview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.Date;

import sun.bob.mcalendarview.adapters.CalendarViewAdapter;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.listeners.OnMonthChangeListener;
import sun.bob.mcalendarview.utils.CurrentCalendar;
import sun.bob.mcalendarview.vo.DateData;
import sun.bob.mcalendarview.vo.MarkedDates;

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
    private CalendarViewAdapter adapter;


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
        // TODO: 15/8/28 Will this cause trouble when achieved?
        if (this.getId() == View.NO_ID){
            this.setId(R.id.calendarViewPager);
        }
        adapter = new CalendarViewAdapter(activity.getSupportFragmentManager()).setDate(currentDate);
        this.setAdapter(adapter);
        this.setCurrentItem(500);
    }

    //// TODO: 15/8/28 May cause trouble when invoked after inited
    public mCalendarView travelTo(DateData dateData){
        this.currentDate = dateData;

        return this;
    }

    public mCalendarView markDate(int year, int month, int day){
        MarkedDates.getInstance().add(new DateData(year, month, day));
        return this;
    }

    public mCalendarView unMarkDate(int year, int month, int day){
        MarkedDates.getInstance().remove(new DateData(year, month, day));
        return this;
    }

    public mCalendarView markDate(DateData date){
        MarkedDates.getInstance().add(date);
        return this;
    }

    public mCalendarView unMarkDate(DateData date){
        MarkedDates.getInstance().remove(date);
        return this;
    }

    public MarkedDates getMarkedDates(){
        return MarkedDates.getInstance();
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

    public mCalendarView setOnMonthChangeListener(OnMonthChangeListener listener){
        this.addOnPageChangeListener(listener);
        return this;
    }

    public mCalendarView setOnDateClickListener(OnDateClickListener onDateClickListener){
        OnDateClickListener.instance = onDateClickListener;
        return this;
    }
}
