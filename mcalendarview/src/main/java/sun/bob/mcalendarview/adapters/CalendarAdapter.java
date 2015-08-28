package sun.bob.mcalendarview.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sun.bob.mcalendarview.vo.DayData;

/**
 * Created by bob.sun on 15/8/27.
 */
public class CalendarAdapter extends ArrayAdapter {
    private ArrayList data;
    public CalendarAdapter(Context context, int resource, ArrayList data) {
        super(context, resource);
        this.data = data;
    }

    public View getView(int posistion, View convertView, ViewGroup viewGroup){
        View ret = new TextView(getContext());
        ((TextView) ret).setText(((DayData) data.get(posistion)).getText());
        return ret;
    }

    @Override
    public int getCount(){
        return data.size();
    }
}
