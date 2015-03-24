package sun.bob.simplecalendarview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sunkuan on 15/3/23.
 */
public class mCalendarViewFragment extends Fragment {

    private mCalendarView calendarView;

    private mMonthData monthData;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState){
        calendarView = new mCalendarView(getActivity());
        calendarView.setMonthData(monthData);
        return calendarView;
    }
    public void setMonthData(mMonthData m){
        this.monthData = m;
    }
}
