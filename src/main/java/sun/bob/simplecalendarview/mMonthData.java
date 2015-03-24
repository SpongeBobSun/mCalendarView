package sun.bob.simplecalendarview;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Calendar;

import sun.bob.mcalendarview.R;

/**
 * Created by sunkuan on 15/3/17.
 */
public class mMonthData{
    private static Context appContext;
//    private static mMonthData instance;
    private ArrayList<mDateData> content;
    private Calendar calendar;
    private int totalDay;
    private int lastMonthTotalDay;
    private int today;
    private int startDay;
    private int year;
    private int month;
    private int lastMonth;
    public mMonthData(Context context){
        appContext = context;
//        instance = this;
        content = new ArrayList<mDateData>();
        initCalendar();
    }
//    public static mMonthData getInstance(Context context){
//        if(appContext == null){
//            instance =  new mMonthData(context);
//        }
//        return instance;
//    }
    private void initCalendar(){
        calendar = Calendar.getInstance();
        totalDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        today = calendar.get(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        Calendar tmpCal = Calendar.getInstance();
        tmpCal.clear();
        tmpCal.set(year, month, 1);
        startDay = tmpCal.get(Calendar.DAY_OF_WEEK) - 1;
        totalDay = totalDay + startDay;
        if(month > 0) {
            lastMonth = month - 1;
            tmpCal.set(year, lastMonth, 1);
        }else{
            lastMonth = 11;
            tmpCal.set(year-1,11,1);
        }
        lastMonthTotalDay = tmpCal.get(Calendar.DAY_OF_MONTH);
//        initArray();
    }
    private void initArray(){
        for (int i = 0;i < 7;i++){
            content.add(new mTitleData(i+1));
        }
        mDateData addDate;
        for(int i = 0;i < totalDay+7;i++){
            if(i < startDay) {
                addDate = new mDateData(lastMonthTotalDay - (startDay- i)+1);
                addDate.setMonth(lastMonth);
                addDate.setTextColor(appContext.getResources().getColor(R.color.lightgray));
                addDate.setTextSize(0);
                content.add(addDate);
                continue;
            }
            if((i >= totalDay) && (i % 7 !=0)){
                addDate = new mDateData((i - totalDay )+1);
                addDate.setMonth(month+1);
                addDate.setTextColor(appContext.getResources().getColor(R.color.lightgray));
                addDate.setTextSize(0);
                content.add(addDate);
                continue;
            }else if((i >= totalDay) && (i % 7 ==0)){
                return;
            }
            addDate = new mDateData(i + 1 - startDay);
            addDate.setMonth(month);
            addDate.setTextColor(appContext.getResources().getColor(R.color.black));
            addDate.setTextSize(1);
            content.add(addDate);
        }
    }

    //Notice -
    //          monthArg must NOT be zero.
    public void changeMonth(int yearArg,int monthArg){
        monthArg -= 1;
        year = yearArg;
        month = monthArg;
        calendar = Calendar.getInstance();
        calendar.set(year,month,1);
        totalDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        Calendar tmpCal = Calendar.getInstance();
        tmpCal.clear();
        tmpCal.set(year, month, 1);

        totalDay = totalDay + startDay;
        if(month > 1) {
            lastMonth = month - 1;
            tmpCal.set(year, lastMonth, 1);
        }else{
            lastMonth = 11;
            tmpCal.set(year-1,11,1);
        }
        lastMonthTotalDay = tmpCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        content.clear();
        initArray();
    }
    public ArrayList<mDateData> getArray() {
        return content;
    }
    public int getStartDay() {
        return startDay;
    }
    public void markDay(int day,int color, int style){
        //Plus 7 because the title views.
        content.get(day + 7 + startDay - 1).mark(color,style);
    }
    public static int getCurrentYear(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }
    public static int getCurrentMonth(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH)+1;
    }
}
