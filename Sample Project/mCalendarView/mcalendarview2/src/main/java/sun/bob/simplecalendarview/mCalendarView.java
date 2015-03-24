<<<<<<< HEAD
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
//        monthData = new mMonthData(context);
    }
    public void setMonthData(mMonthData monthData){
        this.monthData = monthData;
        adapter = new mCalendarAdapter(getContext(),android.R.layout.simple_list_item_1,monthData.getArray());
        adapter.setMonthData(this.monthData);
        this.setAdapter(adapter);
        this.setNumColumns(7);
    }

    public mCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void markDay(int day, int markColor, int markStyle){
        monthData.markDay(day,getResources().getColor(markColor),markStyle);
        adapter.notifyDataSetChanged();
    }
    public void setOnDateClickListener(OnItemClickListener clickListener){
        this.setOnItemClickListener(clickListener);
    }

    public mCalendarAdapter getCalendarAdapter(){
        return adapter;
    }

=======
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
//        monthData = new mMonthData(context);
    }
    public void setMonthData(mMonthData monthData){
        this.monthData = monthData;
        adapter = new mCalendarAdapter(getContext(),android.R.layout.simple_list_item_1,monthData.getArray());
        adapter.setMonthData(this.monthData);
        this.setAdapter(adapter);
        this.setNumColumns(7);
    }

    public mCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void markDay(int day, int markColor, int markStyle){
        monthData.markDay(day,getResources().getColor(markColor),markStyle);
        adapter.notifyDataSetChanged();
    }
    public void setOnDateClickListener(OnItemClickListener clickListener){
        this.setOnItemClickListener(clickListener);
    }

    public mCalendarAdapter getCalendarAdapter(){
        return adapter;
    }

>>>>>>> origin/master
}