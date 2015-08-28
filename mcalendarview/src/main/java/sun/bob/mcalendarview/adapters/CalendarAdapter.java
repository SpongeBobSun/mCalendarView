package sun.bob.mcalendarview.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.views.BaseCellView;
import sun.bob.mcalendarview.views.BaseMarkView;
import sun.bob.mcalendarview.views.DefaultCellView;
import sun.bob.mcalendarview.vo.DateData;
import sun.bob.mcalendarview.vo.DayData;

/**
 * Created by bob.sun on 15/8/27.
 */
public class CalendarAdapter extends ArrayAdapter {
    private ArrayList data;
    private BaseCellView cellView;
    private BaseMarkView markView;
    public CalendarAdapter(Context context, int resource, ArrayList data) {
        super(context, resource);
        this.data = data;
    }

    public CalendarAdapter setCellViews(BaseCellView cellView, BaseMarkView markView){
        this.cellView = cellView;
        this.markView = markView;
        return this;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup){
        View ret = null;
        //// TODO: 15/8/28 Add customize date cell here.
        if (cellView != null){
            cellView.setDisplayText(((DayData) data.get(position)).getText());
            ret = cellView;
        } else {
            ret = new DefaultCellView(getContext());
            ((DefaultCellView) ret).setDisplayText(((DayData) data.get(position)).getText());
        }
        ((BaseCellView) ret).setDate(((DayData) data.get(position)).getDate());
        if (OnDateClickListener.instance != null){
            ((BaseCellView) ret).setOnDateClickListener(OnDateClickListener.instance);
        }
        return ret;
    }

    @Override
    public int getCount(){
        return data.size();
    }
}
