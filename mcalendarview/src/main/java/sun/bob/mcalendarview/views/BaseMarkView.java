package sun.bob.mcalendarview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by bob.sun on 15/8/28.
 */
public abstract class BaseMarkView extends LinearLayout{
    public BaseMarkView(Context context) {
        super(context);
    }

    public BaseMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void setDisplayText(String text);
}
