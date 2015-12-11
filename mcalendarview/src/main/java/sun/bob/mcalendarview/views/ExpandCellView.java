package sun.bob.mcalendarview.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import sun.bob.mcalendarview.MarkStyleExp;


/**
 * Created by 明明大美女 on 2015/12/9.
 */
public class ExpandCellView extends DefaultCellView {


    public ExpandCellView(Context context) {
        super(context);
    }

    public ExpandCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setText_Color(String text, int color) {
        textView.setText(text);
        if (color != 0) {
            textView.setTextColor(color);
        }
    }

    /**
     * 下面三个可以做一个判断，且回调。 执行是否自动换页的功能
     */
    public boolean setDateChoose() {
        setBackgroundDrawable(MarkStyleExp.choose);
        textView.setTextColor(Color.WHITE);
        return true ;
    }

    public void setDateToday(){
        setBackgroundDrawable(MarkStyleExp.today);
        textView.setTextColor(Color.rgb(105, 75, 125));
    }

    public void setDateNormal() {
        textView.setTextColor(Color.BLACK);
        setBackgroundDrawable(null);
    }

}
