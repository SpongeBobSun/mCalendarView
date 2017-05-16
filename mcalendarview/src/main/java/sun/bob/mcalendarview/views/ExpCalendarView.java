package sun.bob.mcalendarview.views;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import android.util.Log;
import sun.bob.mcalendarview.CellConfig;
import java.util.Calendar;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.R;
import sun.bob.mcalendarview.adapters.CalendarViewExpAdapter;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.listeners.OnMonthChangeListener;
import sun.bob.mcalendarview.listeners.OnMonthScrollListener;
import sun.bob.mcalendarview.utils.CalendarUtil;
import sun.bob.mcalendarview.utils.CurrentCalendar;
import sun.bob.mcalendarview.vo.DateData;
import sun.bob.mcalendarview.vo.MarkedDates;


/**
 * Created by 明明大美女 on 2015/12/8.
 */
public class ExpCalendarView extends ViewPager {
    private int dateCellViewResId = -1;
    private View dateCellView = null;
    private int markedStyle = -1;
    private int markedCellResId = -1;
    private View markedCellView = null;
    private boolean hasTitle = true;

    private boolean initted = false;

    private DateData currentDate;
    private CalendarViewExpAdapter adapter;

    private int width, height;
    private int currentIndex;

    public ExpCalendarView(Context context) {
        super(context);
        if (context instanceof FragmentActivity) {
            init((FragmentActivity) context);
        }
    }

    public ExpCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (context instanceof FragmentActivity) {
            init((FragmentActivity) context);
        }
    }

    public void init(FragmentActivity activity) {
        if (initted) {
            return;
        }
        initted = true;
        if (currentDate == null) {
            currentDate = CurrentCalendar.getCurrentDateData();
        }
        // TODO: 15/8/28 Will this cause trouble when achieved?
        if (this.getId() == View.NO_ID) {
            this.setId(R.id.calendarViewPager);
        }
        adapter = new CalendarViewExpAdapter(activity.getSupportFragmentManager()).setDate(currentDate);
        this.setAdapter(adapter);
        this.setCurrentItem(500);
//        addBackground();
        float density = getContext().getResources().getSystem().getDisplayMetrics().density;
        CellConfig.cellHeight = getContext().getResources().getSystem().getDisplayMetrics().widthPixels / 7 / density;
        CellConfig.cellWidth = getContext().getResources().getSystem().getDisplayMetrics().widthPixels / 7 / density;

//        this.addOnPageChangeListener(new );
    }

    //// TODO: 15/8/28 May cause trouble when invoked after inited
    public ExpCalendarView travelTo(DateData dateData) {
        // 获得当前页面的年月（position=500）
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH);
         int realPosition = 500 + (dateData.getYear() - thisYear) * 12 + (dateData.getMonth() - thisMonth - 1);
         if (realPosition > 1000 || realPosition < 0)
             throw new RuntimeException("Please travelto a right date: today-500~today~today+500");

         // 来个步进滑动？因为一次滑个几百页，界面有时候不刷新（蛋疼）
         for (int i = getCurrentItem(); i < realPosition; i=i+50) {
             setCurrentItem(i);
             Log.i("step", " "+i);
         }
         for (int i = getCurrentItem(); i > realPosition; i=i-50) {
             setCurrentItem(i);
             Log.i("step", " "+i);
         }
         setCurrentItem(realPosition);
         // 标记
         MarkedDates.getInstance().add(dateData);
          return this;
    }
    public void expand() {
        adapter.notifyDataSetChanged();
    }

    public void shrink() {
        adapter.notifyDataSetChanged();
    }


    public ExpCalendarView markDate(int year, int month, int day) {
        MarkedDates.getInstance().add(new DateData(year, month, day));
        return this;
    }

    public ExpCalendarView unMarkDate(int year, int month, int day) {
        MarkedDates.getInstance().remove(new DateData(year, month, day));
        return this;
    }

    public ExpCalendarView markDate(DateData date) {
        MarkedDates.getInstance().add(date);
        return this;
    }

    public ExpCalendarView unMarkDate(DateData date) {
        MarkedDates.getInstance().remove(date);
        return this;
    }
    
    public ExpCalendarView removeAdd() {
        MarkedDates.getInstance().removeAdd();
        return this;
    }

    public MarkedDates getMarkedDates() {
        return MarkedDates.getInstance();
    }

    public ExpCalendarView setDateCell(int resId) {
        adapter.setDateCellId(resId);
        return this;
    }

    public ExpCalendarView setMarkedStyle(int style, int color) {
        MarkStyle.current = style;
        MarkStyle.defaultColor = color;
        return this;
    }

    public ExpCalendarView setMarkedStyle(int style) {
        MarkStyle.current = style;
        return this;
    }

    public ExpCalendarView setMarkedCell(int resId) {
        adapter.setMarkCellId(resId);
        return this;
    }

    public ExpCalendarView setOnMonthChangeListener(OnMonthChangeListener listener) {
        this.addOnPageChangeListener(listener);
        return this;
    }

    public ExpCalendarView setOnMonthScrollListener(OnMonthScrollListener listener) {
        this.addOnPageChangeListener(listener);
        return this;
    }

    public ExpCalendarView setOnDateClickListener(OnDateClickListener onDateClickListener) {
        OnDateClickListener.instance = onDateClickListener;
        return this;
    }

    public ExpCalendarView hasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
        adapter.setTitle(hasTitle);
        return this;
    }

    @Override
    protected void onMeasure(int measureWidthSpec, int measureHeightSpec) {
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
        float density = getContext().getResources().getSystem().getDisplayMetrics().density;
        if (specMode == MeasureSpec.AT_MOST) {
            if (CellConfig.ifMonth)
                return (int) (CellConfig.cellHeight * 6 * density);
            else
                return (int) (CellConfig.cellHeight * density);
        } else if (specMode == MeasureSpec.EXACTLY) {
            return specSize;
        } else {
            return (int) CellConfig.cellHeight;
        }
    }


    public void measureCurrentView(int currentIndex) {
        this.currentIndex = currentIndex;
        requestLayout();
    }
}

