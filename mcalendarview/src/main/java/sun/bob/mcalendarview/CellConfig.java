package sun.bob.mcalendarview;

import sun.bob.mcalendarview.vo.DateData;

/**
 * Created by bob.sun on 15/8/29.
 */
public class CellConfig {
    public static float cellWidth = 100;
    public static float cellHeight = 100;


    public static boolean ifMonth = true;
    // 中间页的位置
    public static int middlePosition = 500;
    // 下面两个:
    // 点击了收缩展开后，记录当前页的位置，后期换算位移用他俩
    public static int Month2WeekPos = 500;
    public static int Week2MonthPos = 500;
    // 下面两个:
    // 一开始只设置了一个，后来发现数据有交差（在使用前就做了更改）
    // 所以分成了两个
    public static DateData w2mPointDate;
    public static DateData m2wPointDate;

    public static DateData weekAnchorPointDate;
}
