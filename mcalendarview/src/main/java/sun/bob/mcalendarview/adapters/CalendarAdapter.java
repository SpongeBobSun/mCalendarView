package sun.bob.mcalendarview.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sun.bob.mcalendarview.views.BaseCellView;
import sun.bob.mcalendarview.views.BaseMarkView;
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
        //// TODO: 15/8/28 Add mark date support.
        if (cellView != null){
            cellView.setDisplayText(((DayData) data.get(position)).getText());
            ret = cellView;
        } else {
            //// TODO: 15/8/28 Change TextView to DefaultCellView
            ret = new TextView(getContext());
            ((TextView) ret).setText(((DayData) data.get(position)).getText());
        }
        return ret;
    }

    @Override
    public int getCount(){
        return data.size();
    }
}
