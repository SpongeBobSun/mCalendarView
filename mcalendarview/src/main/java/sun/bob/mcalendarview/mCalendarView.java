package sun.bob.mcalendarview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import sun.bob.mcalendarview.adapters.CalendarViewAdapter;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.listeners.OnMonthChangeListener;
import sun.bob.mcalendarview.utils.CalendarUtil;
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

    private int width, height;

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
        addBackground();
    }

    private void addBackground(){
        ShapeDrawable drawable = new ShapeDrawable(new RectShape());
        drawable.getPaint().setColor(Color.LTGRAY);
        drawable.getPaint().setStyle(Paint.Style.STROKE);
        this.setBackground(drawable);
    }

    //// TODO: 15/8/28 May cause trouble when invoked after inited
    public mCalendarView travelTo(DateData dateData){
        this.currentDate = dateData;
        CalendarUtil.date = dateData;
        this.initted = false;
        init((FragmentActivity) getContext());
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

    @Override
    protected void onMeasure(int measureWidthSpec,int measureHeightSpec){
        super.onMeasure(measureWidthSpec, measureHeightSpec);
        width = measureWidth(measureWidthSpec);
        height = measureHeight(measureHeightSpec);
        this.setMeasuredDimension(width, height);
//        if (getContext() instanceof FragmentActivity){
//            init((FragmentActivity) getContext());
//        }
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 0;
        if (specMode == MeasureSpec.AT_MOST) {
            result = getWidth();
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }
    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 0;
        if (specMode == MeasureSpec.AT_MOST) {
            result = getWidth();
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }
}
