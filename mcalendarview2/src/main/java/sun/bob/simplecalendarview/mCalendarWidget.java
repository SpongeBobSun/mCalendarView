package sun.bob.simplecalendarview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import sun.bob.mcalendarview.R;

/**
 * Created by bob.sun on 2015/3/24.
 */
public class mCalendarWidget extends LinearLayout {
    private mCalendarViewPager calendarViewPager;
    View title;
    Context context;

    public mCalendarWidget(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public mCalendarWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }
    private void initView(){
        this.setOrientation(LinearLayout.VERTICAL);
        title = LayoutInflater.from(context).inflate(R.layout.calendar_title,null);
        calendarViewPager = new mCalendarViewPager(context);

        TextView year = (TextView)title.findViewById(R.id.id_textview_title_year);
        year.setText(String.valueOf(calendarViewPager.getYear()));
        TextView month = (TextView)title.findViewById(R.id.id_textview_title_month);
        month.setText(String.valueOf(calendarViewPager.getMonth()));

        LayoutParams titleParams = new LayoutParams(LayoutParams.MATCH_PARENT
                                                    , LayoutParams.WRAP_CONTENT,1f);
        this.addView(title,titleParams);

        LayoutParams calendarParms = new LayoutParams(LayoutParams.MATCH_PARENT
                , LayoutParams.WRAP_CONTENT,5f);
        this.addView(calendarViewPager,calendarParms);

        calendarViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                calendarViewPager.setYear(mCalendarViewPager.position2Year(position));
                calendarViewPager.setMonth(mCalendarViewPager.position2Month(position));
                TextView year = (TextView)title.findViewById(R.id.id_textview_title_year);
                year.setText(String.valueOf(calendarViewPager.getYear()));
                TextView month = (TextView)title.findViewById(R.id.id_textview_title_month);
                month.setText(String.valueOf(calendarViewPager.getMonth()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

