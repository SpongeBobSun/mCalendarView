package sun.bob.mcalendarview.utils;

import android.util.Log;

import java.util.Calendar;

import sun.bob.mcalendarview.CellConfig;
import sun.bob.mcalendarview.vo.DateData;

/**
 * Created by Bigflower on 2015/12/8.
 */
public class ExpCalendarUtil {

    /**
     * the number to Week
     *
     * @param number
     * @return
     */
    public static String number2Week(int number) {
        if (number < 1 || number > 7)
            return null;
        String result = null;
        switch (number) {
            case 1:
                result = "一";
                break;
            case 2:
                result = "二";
                break;
            case 3:
                result = "三";
                break;
            case 4:
                result = "四";
                break;
            case 5:
                result = "五";
                break;
            case 6:
                result = "六";
                break;
            case 7:
                result = "日";
                break;
        }
        return result;
    }

    public static DateData position2Month(int absPos) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(CellConfig.m2wPointDate.getYear(), CellConfig.m2wPointDate.getMonth() - 1, CellConfig.m2wPointDate.getDay());
        int distance = absPos - CellConfig.Week2MonthPos;
        calendar.add(Calendar.MONTH, distance);
        Log.i("ExpDateData month", CellConfig.m2wPointDate.toString()+" distance :"+distance);
        Log.i("ExpDateData month", calendar.get(Calendar.YEAR)+"年"+calendar.get(Calendar.MONTH));
        return new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
    }

    public static DateData position2Week(int absPos) {
        Log.i("ExpDateData", CellConfig.w2mPointDate.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.set(CellConfig.w2mPointDate.getYear(), CellConfig.w2mPointDate.getMonth() - 1, CellConfig.w2mPointDate.getDay());
        int distance = absPos - CellConfig.Month2WeekPos;
        calendar.add(Calendar.DATE, distance * 7);

        return new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
    }


    public static int getMonthDayNumber(int offset) {
        Calendar calendar = Calendar.getInstance();
        if (offset != 0)
            calendar.add(Calendar.MONTH, offset);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
