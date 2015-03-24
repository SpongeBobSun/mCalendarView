package sun.bob.simplecalendarview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by sunkuan on 15/3/17.
 */
public class MainActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

//        mCalendarView calendarView = (mCalendarView)findViewById(R.id.id_test_calendar_view);
//        calendarView.markDay(5,R.color.skyblue,0);
//        calendarView.markDay(15,R.color.lightpink,0);
//        calendarView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("Calendar Test - ", "Clicked on item:"+position);
//            }
//        });
    }
}
