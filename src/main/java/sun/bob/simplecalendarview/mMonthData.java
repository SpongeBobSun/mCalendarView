package sun.bob.simplecalendarview;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by sunkuan on 15/3/17.
 */
public class mMonthData{
    private static Context appContext;
    private static mMonthData instance;
    private ArrayList<mDateData> content;
    private Calendar calendar;
    private int totalDay;
    private int today;
    private int startDay;
    private int year;
    private int month;
    private mMonthData(Context context){
        appContext = context;
        instance = this;
        content = new ArrayList<mDateData>();
        initCalendar();
    }
    public static mMonthData getInstance(Context context){
        if(appContext == null){
            return new mMonthData(context);
        }
        return instance;
    }
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
        initArray();
    }
    private void initArray(){
        for (int i = 0;i < 7;i++){
            content.add(new mTitleData(i+1));
        }
        for(int i = 0;i < totalDay+7;i++){
            if(i < startDay) {
                content.add(new mDateData(0));
                continue;
            }
            if((i >= totalDay) && (i % 7 !=0)){
                content.add(new mDateData(0));
                continue;
            }else if((i >= totalDay) && (i % 7 ==0)){
                return;
            }
            content.add(new mDateData(i + 1 - startDay));
        }
    }
    private void initTitle(){

    }
    public void changeMonth(int month) throws NullPointerException{
        if(instance == null){
            throw new NullPointerException();
        }

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
}
