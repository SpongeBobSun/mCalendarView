package sun.bob.mcalendarviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import sun.bob.mcalendarview.CellConfig;
import sun.bob.mcalendarview.listeners.OnExpDateClickListener;
import sun.bob.mcalendarview.listeners.OnMonthScrollListener;
import sun.bob.mcalendarview.views.ExpCalendarView;
import sun.bob.mcalendarview.vo.DateData;


public class ExpMainActivity extends AppCompatActivity {

    private TextView YearMonthTv;
    private ExpCalendarView expCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exp_main_activity);

//      Get instance.
        expCalendarView = ((ExpCalendarView) findViewById(R.id.calendar_exp));
        YearMonthTv = (TextView) findViewById(R.id.main_YYMM_Tv);
        YearMonthTv.setText(Calendar.getInstance().get(Calendar.YEAR) + "年" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "月");

//      Set up listeners.
        expCalendarView.setOnDateClickListener(new OnExpDateClickListener()).setOnMonthScrollListener(new OnMonthScrollListener() {
            @Override
            public void onMonthChange(int year, int month) {
                YearMonthTv.setText(String.format("%d年%d月", year, month));
            }

            @Override
            public void onMonthScroll(float positionOffset) {
//                Log.i("listener", "onMonthScroll:" + positionOffset);
            }
        });

        imageInit();

        expCalendarView.markDate(2016, 10, 16);
    }

    private boolean ifExpand = true;

    private void imageInit() {
        final ImageView expandIV = (ImageView) findViewById(R.id.main_expandIV);
        expandIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ifExpand) {
                    CellConfig.Month2WeekPos = CellConfig.middlePosition ;
                    CellConfig.ifMonth = false ;
                    expandIV.setImageResource(R.mipmap.icon_arrow_down);
                    expCalendarView.shrink();
                } else {
                    CellConfig.Week2MonthPos = CellConfig.middlePosition ;
                    CellConfig.ifMonth = true ;
                    expandIV.setImageResource(R.mipmap.icon_arrow_up);
                    expCalendarView.expand();
                }
                ifExpand = !ifExpand;
            }
        });
    }
    public void TravelToClick(View v) {
         expCalendarView.travelTo(new DateData(1980, 11, 14));
   }
}
