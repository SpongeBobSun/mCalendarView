package sun.bob.mcalendarviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.listeners.OnMonthChangeListener;
import sun.bob.mcalendarview.mCalendarView;
import sun.bob.mcalendarview.vo.DateData;
import sun.bob.mcalendarview.vo.MarkedDates;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      Get instance.
        final mCalendarView calendarView = ((mCalendarView) findViewById(R.id.calendar));

//      Set up listeners.
        calendarView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                Toast.makeText(MainActivity.this, String.format("%d-%d", date.getMonth(), date.getDay()), Toast.LENGTH_SHORT).show();
            }
        }).setOnMonthChangeListener(new OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                Toast.makeText(MainActivity.this, String.format("%d-%d", year, month), Toast.LENGTH_SHORT).show();
                calendarView.markDate(year, month, 5);
                MarkedDates.getInstance().notifyObservers();
            }
        }).setMarkedStyle(MarkStyle.RIGHTSIDEBAR)
                .markDate(2015, 6, 1).markDate(2015, 6, 25)
                .markDate(2015, 7, 4).markDate(2015, 7, 19)
                .markDate(2015, 8, 20).markDate(2015, 8, 4)
                .markDate(2015, 9, 20).markDate(2015, 9, 1)
                .markDate(2015, 10, 7).markDate(2015,10, 17)
        .hasTitle(false);

//************************************************************************************************************
//        Use default view.
//        If you want to use customized cells, un-comment below line and modify `DateCellView`, `MarkCellView`.
//************************************************************************************************************

//        calendarView.setDateCell(R.layout.layout_date_cell).setMarkedCell(R.layout.layout_mark_cell);

    }

//**********************************************************
//  Generated codes, didn't modified, so you can ignore them.
//**********************************************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
