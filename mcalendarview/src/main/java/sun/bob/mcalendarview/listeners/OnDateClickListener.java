package sun.bob.mcalendarview.listeners;

import sun.bob.mcalendarview.vo.DateData;

/**
 * Created by bob.sun on 15/8/28.
 */
public abstract class OnDateClickListener {
    public static OnDateClickListener instance;

    public abstract void onDateClick(DateData date);
}
