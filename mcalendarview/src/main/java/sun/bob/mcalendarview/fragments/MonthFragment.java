package sun.bob.mcalendarview.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import sun.bob.mcalendarview.adapters.CalendarAdapter;
import sun.bob.mcalendarview.views.MonthView;
import sun.bob.mcalendarview.vo.MonthData;

/**
 * Created by bob.sun on 15/8/27.
 */
public class MonthFragment extends Fragment {
    private MonthData monthData;
    public void setData(MonthData monthData){
        this.monthData = monthData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        LinearLayout ret = new LinearLayout(getContext());
        ret.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(getContext());
        textView.setText("" + monthData.getDate().getMonth());
        ret.addView(textView);
        MonthView monthView = new MonthView(getContext());
        monthView.setAdapter(new CalendarAdapter(getContext(), 1, monthData.getData()));
        ret.addView(monthView);
        return ret;
    }
}
