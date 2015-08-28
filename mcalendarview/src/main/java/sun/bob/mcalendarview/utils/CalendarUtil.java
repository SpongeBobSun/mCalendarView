package sun.bob.mcalendarview.utils;

import java.util.Calendar;

/**
 * Created by bob.sun on 15/8/27.
 */
public class CalendarUtil {
    public static int position2Year(int pos){
        int tmpYear,tmpMonth;
        Calendar c = Calendar.getInstance();
        tmpYear = c.get(Calendar.YEAR);
        tmpMonth = CalendarUtil.position2Month(pos);
        int ret;
        if(pos == 500){
            return tmpYear;
        }
        if(pos > 500){

            ret = tmpYear + ((pos - 500) + c.get(Calendar.MONTH))/12;

        }else{
            ret =  tmpYear - ((500 - pos)+tmpMonth)/12;
        }
        return ret;
    }

    public static int position2Month(int pos){
        int tmpMonth;
        Calendar c = Calendar.getInstance();
        tmpMonth = c.get(Calendar.MONTH) + 1;
        int ret;
        if(pos == 500){
            return tmpMonth;
        }
        if(pos > 500){
            ret = (tmpMonth + (pos - 500)%12) % 12;
        }else{
            ret = (tmpMonth - (500 - pos)%12) % 12;
            ret = ret<0?12+ret:ret;
        }
        return ret==0?12:ret;
    }
}
