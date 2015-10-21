package sun.bob.mcalendarview.vo;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Calendar;

import sun.bob.mcalendarview.R;

/**
 * Created by bob.sun on 15/8/27.
 */
public class MonthData {
    private DateData date;
    private Calendar calendar;

    private int startDay, totalDay, lastMonth, lastMonthTotalDay;

    private ArrayList<DayData> content;

    private boolean hasTitle;
    public MonthData(DateData dateData, boolean hasTitle){
        date = dateData;
        calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonth() - 1, date.getDay());
        content = new ArrayList<DayData>();
        this.hasTitle = hasTitle;
        initHeadToTail();
        initArray();
    }

    private void initHeadToTail(){
        Calendar tmpCal = Calendar.getInstance();
        tmpCal.clear();
        tmpCal.set(date.getYear(), date.getMonth() - 1, 1);
        totalDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        startDay = tmpCal.get(Calendar.DAY_OF_WEEK) - 1;
        totalDay = totalDay + startDay;
        if(date.getMonth() - 1 > 0) {
            lastMonth = date.getMonth() - 2;
            tmpCal.set(date.getYear(), lastMonth, 1);
            lastMonthTotalDay = tmpCal.getActualMaximum(Calendar.DAY_OF_MONTH);
            lastMonth += 1;
        }else{
            lastMonth = 12;
            tmpCal.set(date.getYear()-1,11,1);
            lastMonthTotalDay = tmpCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
    }

    private void initArray(){
        if (hasTitle){
            for (int i = 0;i < 7;i++){
                content.add(new TitleData(new DateData(0,0,i+1)));
            }
        }
        DayData addDate;
        for(int i = 0;i < totalDay+7;i++){
            if(i < startDay) {
                addDate = new DayData(new DateData(date.getYear(),
                                                    lastMonth,
                                                    lastMonthTotalDay - (startDay- i)+1));
                addDate.setTextColor(Color.GRAY);
                addDate.setTextSize(0);
                content.add(addDate);
                continue;
            }
            if((i >= totalDay) && (i % 7 !=0)){
                // Maybe move them to DateData class.
                boolean happyNewYear = false;
                int nextYear, nextMonth;
                happyNewYear = date.getMonth() == 12;
                nextMonth = happyNewYear ? 1 : date.getMonth() + 1;
                nextYear = happyNewYear ? date.getYear() + 1 : date.getYear();

                addDate = new DayData(new DateData(nextYear,
                                                    nextMonth,
                                                    i - totalDay + 1));
                addDate.setTextColor(Color.GRAY);
                addDate.setTextSize(0);
                content.add(addDate);
                continue;
            }else if((i >= totalDay) && (i % 7 ==0)){
                return;
            }
            addDate = new DayData(new DateData(date.getYear(),
                                    date.getMonth(),
                                    i + 1 - startDay));
            addDate.setTextColor(Color.BLACK);
            addDate.setTextSize(1);
            content.add(addDate);
        }
    }

    public void travelTo(DateData date){
        this.date = date;
        calendar = Calendar.getInstance();
        calendar.set(date.getYear(),date.getMonth() - 1,1);
        totalDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        initHeadToTail();

        content.clear();
        initArray();
    }

    public ArrayList getData(){
        return content;
    }

    public DateData getDate(){
        return date;
    }
}
