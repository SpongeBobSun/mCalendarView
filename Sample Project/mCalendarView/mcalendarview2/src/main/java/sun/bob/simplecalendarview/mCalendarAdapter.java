package sun.bob.simplecalendarview;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import sun.bob.mcalendarview.R;

/**
 * Created by sunkuan on 15/3/17.
 */
public class mCalendarAdapter extends ArrayAdapter<mDateData> {
    ArrayList<mDateData> dateDatas;
    private int cellSize;
    private mMonthData monthData;
    public mCalendarAdapter(Context context, int resource,ArrayList<mDateData> arrayList) {
        super(context, resource, arrayList);
//        monthData = new mMonthData(context);
        dateDatas = arrayList;
        getDefaultcellSize();
    }
    private void getDefaultcellSize(){
        DisplayMetrics dm;
        dm = getContext().getResources().getDisplayMetrics();
        cellSize = (dm.widthPixels-10) / 7;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
//        ArrayList<mDateData> list = mMonthData.getInstance(getContext()).getArray();_
        ArrayList<mDateData> list = monthData.getArray();
        View retView;
        retView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.date_cell,null);
        TextView textView = (TextView)retView.findViewById(R.id.id_date_cell_textview);
        mDateData dateData = list.get(position);
        if(position < 7){
            textView.setText(((mTitleData)dateData).getTitle());
            textView.setTextSize(cellSize / 4);
            textView.setBackgroundColor(getContext().getResources().getColor(R.color.lightblue));
            textView.setWidth(cellSize);
            LinearLayout linearLayout = (LinearLayout)retView.findViewById(R.id.id_date_cell_text_container);
            linearLayout.removeViewAt(1);
            return retView;
        }
        if(dateData.getDay() == 0) {
            textView.setText("");
        }else{
            textView.setText(String.format("%d",dateData.getDay()));
        }
        textView.setTextSize(cellSize/3);
        if(dateData.isMarked()){
            View view = retView.findViewById(R.id.id_date_cell_mark_bar);
            view.setBackgroundColor(dateData.getMarkColor());
        }
        retView.setLayoutParams(new GridView.LayoutParams(cellSize,cellSize));
        return retView;
    }

    public mMonthData getMonthData() {
        return monthData;
    }
    public void setMonthData(mMonthData monthData){this.monthData = monthData;}
}
