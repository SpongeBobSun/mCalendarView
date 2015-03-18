package sun.bob.simplecalendarview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by sunkuan on 15/3/17.
 */
public class mCalendarView extends GridView{
    private mMonthData monthData;
    private mCalendarAdapter adapter;
    public mCalendarView(Context context) {
        super(context);
    }

    public mCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        monthData = mMonthData.getInstance(context);
        adapter = new mCalendarAdapter(context,android.R.layout.simple_list_item_1,monthData.getArray());
        this.setAdapter(adapter);
        this.setNumColumns(7);
    }
    public void markDay(int day, int markColor, int markStyle){
        monthData.markDay(day,getResources().getColor(markColor),markStyle);
        adapter.notifyDataSetChanged();
    }
    public void setOnDateClickListener(OnItemClickListener clickListener){
        this.setOnItemClickListener(clickListener);
    }
}