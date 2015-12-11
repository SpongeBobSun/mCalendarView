package sun.bob.mcalendarview.vo;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import sun.bob.mcalendarview.CellConfig;


/**
 * Created by Bigflower on 2015/12/8.
 */
public class MonthWeekData {
    private DateData pointDate;
    private Calendar calendar;

    private int realPosition;
    private int weekIndex, preNumber, afterNumber;

    private ArrayList<DayData> monthContent;
    private ArrayList<DayData> weekContent;

    /**
     * 绝对位置
     *
     * @param position
     */
    public MonthWeekData(int position) {
        realPosition = position;
        calendar = Calendar.getInstance();
        if (CellConfig.m2wPointDate == null) {
            CellConfig.m2wPointDate = new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        }
        if (CellConfig.w2mPointDate == null) {
            CellConfig.w2mPointDate = new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        }

        if (CellConfig.ifMonth) {
            getPointDate();
            initMonthArray();
        } else {
            initWeekArray();
        }
    }

    private void getPointDate() {
        // 获得收缩后的那个point
        calendar.set(CellConfig.w2mPointDate.getYear(), CellConfig.w2mPointDate.getMonth() - 1, 1);
        // 获得周的相对滑动的页面差
        int distance = CellConfig.Week2MonthPos - CellConfig.Month2WeekPos;
        calendar.add(Calendar.DATE, distance * 7);
        Log.i("我是Month", "是我变了吗？" + CellConfig.w2mPointDate.toString());
        Log.i("getPointDate", " 基准日期：" + (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH));
        // 判断是否中间页
        if (realPosition == CellConfig.middlePosition) {
            CellConfig.m2wPointDate = new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            Log.v("我是Month", "哈哈当前页面锚点" + CellConfig.m2wPointDate.toString());
        } else {
            calendar.add(Calendar.MONTH, realPosition - CellConfig.Week2MonthPos);
        }
        calendar.set(Calendar.DATE, 1);
        Log.d("getPointDate", " 最终滚动后：" + (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH));

    }

    /**
     * 这个表是确定的6行，所以共有42个数字
     */
    private void initMonthParams() {
        weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
        //我擦 败家的11月有问题，获得星期不对，拟合一下 TODO
//        if (calendar.get(Calendar.MONTH) == 11)
//            weekIndex--;
        preNumber = weekIndex - 1;
        afterNumber = 42 - calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - preNumber;
//        Log.e("initMonthParams", " weekIndex:" + weekIndex);
//        Log.e("initMonthParams", " preNumber:" + preNumber);
//        Log.e("initMonthParams", " afterNumber:" + afterNumber);
    }

    private void initMonthArray() {
        DayData addDate;
        monthContent = new ArrayList<DayData>();

        initMonthParams();

        // 本月前面的 上个月的灰色的日期
        calendar.add(Calendar.MONTH, -1);
        int lastMonthDayNumber = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int preDay = lastMonthDayNumber - preNumber + 1; preDay < lastMonthDayNumber + 1; preDay++) {
            addDate = new DayData(new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, preDay));
            addDate.setTextColor(Color.LTGRAY);
            monthContent.add(addDate);
        }

        // 本月的 日期
        calendar.add(Calendar.MONTH, 1);
        int thisMonthDayNumber = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int day = 1; day < thisMonthDayNumber + 1; day++) {
            addDate = new DayData(new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, day));
            addDate.setTextColor(Color.BLACK);
            monthContent.add(addDate);
        }

        // 本月的后面 下个月的灰色的日期
        afterNumber = afterNumber + 1;
        calendar.add(Calendar.MONTH, 1);
        for (int afterDay = 1; afterDay < afterNumber; afterDay++) {
            addDate = new DayData(new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, afterDay));
            addDate.setTextColor(Color.LTGRAY);
            monthContent.add(addDate);
        }
        calendar.add(Calendar.MONTH, -1);
    }

    private void thisMonthArray() {

    }

    private void otherMonthArray() {

    }

    /**
     * Week2MonthPos 与 Month2WeekPos 是关键。
     */
    private void initWeekArray() {
        weekContent = new ArrayList<DayData>();

        // 下面是：获得上次记录的位置，根据页数位移，判断该页面的锚点（该页是几月份）
        calendar.set(CellConfig.m2wPointDate.getYear(), CellConfig.m2wPointDate.getMonth() - 1, CellConfig.m2wPointDate.getDay());
        if (CellConfig.Week2MonthPos != CellConfig.Month2WeekPos) {
            // 中间页面与今天的相对页数差
            int distance = CellConfig.Month2WeekPos - CellConfig.Week2MonthPos;
            // 滚到当前页
            calendar.add(Calendar.MONTH, distance);
        }
        // 如果是今天的月份，则锚点的日期为今天； 如果不是今天的月份，则锚点的日期为1号
        calendar.set(Calendar.DAY_OF_MONTH, ifThisMonth());
///////////////////////////////////////////////////////////////////////////////////////////
        // 下面是：获得该页的锚点后，判断三页显示的内容，中间和两边页显示不同
        if (realPosition == CellConfig.Month2WeekPos) {
            ;
        } else {
            calendar.add(Calendar.DATE, (realPosition - CellConfig.Month2WeekPos) * 7);
        }

        // 记录中间页的pointDate
        if (realPosition == CellConfig.middlePosition) {
            CellConfig.w2mPointDate = new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            Log.v("我是Week", " 哈哈当前页面锚点：" + CellConfig.w2mPointDate.toString());
        }

        // 添加数据
        DayData addDate;
        weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -weekIndex + 1);
        for (int i = 0; i < 7; i++) {
            addDate = new DayData(new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)));
            weekContent.add(addDate);
            calendar.add(Calendar.DATE, 1);
        }
    }

    private int ifThisMonth() {
        int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
//        Log.e("","================================");
//        Log.e("",calendar.get(Calendar.MONTH)+" "+thisMonth);
//        Log.e("","================================");
        if (calendar.get(Calendar.MONTH) == thisMonth) {
            return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        } else {
            return 1;
        }
    }

    public ArrayList getData() {
        if (CellConfig.ifMonth)
            return monthContent;
        else
            return weekContent;
    }

}
