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
    private boolean hasTitle = true;

    private boolean initted = false;

    private DateData currentDate;
    private CalendarViewAdapter adapter;

    private int width, height;
    private int currentIndex;

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
//        addBackground();
        float density = getContext().getResources().getSystem().getDisplayMetrics().density;
        CellConfig.cellHeight = getContext().getResources().getSystem().getDisplayMetrics().widthPixels / 7 / density;
        CellConfig.cellWidth = getContext().getResources().getSystem().getDisplayMetrics().widthPixels / 7 / density;
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

    public mCalendarView setDateCell(int resId){
        adapter.setDateCellId(resId);
        return this;
    }

    public mCalendarView setMarkedStyle(int style, int color){
        MarkStyle.current = style;
        MarkStyle.color = color;
        return this;
    }

    public mCalendarView setMarkedStyle(int style){
        MarkStyle.current = style;
        return this;
    }

    public mCalendarView setMarkedCell(int resId) {
        adapter.setMarkCellId(resId);
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

    public mCalendarView hasTitle(boolean hasTitle){
        this.hasTitle = hasTitle;
        adapter.setTitle(hasTitle);
        return this;
    }

    @Override
    protected void onMeasure(int measureWidthSpec,int measureHeightSpec){
        width = measureWidth(measureWidthSpec);
        height = measureHeight(measureHeightSpec);
        measureHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(measureWidthSpec, measureHeightSpec);
    }
    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 0;
        if (specMode == MeasureSpec.AT_MOST) {
            float destiney = getContext().getResources().getSystem().getDisplayMetrics().density;
            result = (int) (CellConfig.cellWidth * 7 * destiney);
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) CellConfig.cellHeight;
        }
        return result;
    }
    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 0;
        if (specMode == MeasureSpec.AT_MOST) {
            int columns = CalendarUtil.getWeekCount(currentIndex);
            columns = hasTitle ? columns + 1 : columns;
            float density = getContext().getResources().getSystem().getDisplayMetrics().density;
            result = (int) (CellConfig.cellHeight * columns * density);
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) CellConfig.cellHeight;
        }
        return result;
    }


    public void measureCurrentView(int currentIndex) {
        this.currentIndex = currentIndex;
        requestLayout();
    }
}
