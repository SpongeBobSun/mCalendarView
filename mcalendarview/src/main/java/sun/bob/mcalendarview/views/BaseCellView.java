package sun.bob.mcalendarview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

/**
 * Created by bob.sun on 15/8/28.
 */
public abstract class BaseCellView extends LinearLayout {
    private OnDateClickListener clickListener;
    private DateData date;

    public BaseCellView(Context context) {
        super(context);
    }

    public BaseCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseCellView setDate(DateData date){
        this.date = date;
        return this;
    }

    public BaseCellView setOnDateClickListener(OnDateClickListener clickListener){
        this.clickListener = clickListener;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseCellView.this.clickListener != null){
                    BaseCellView.this.clickListener.onDateClick(BaseCellView.this, date);
                }
            }
        });
        return this;
    }

    public BaseCellView removeOnDateClickListener(){
        this.clickListener = null;
        return this;
    }
    public OnDateClickListener getOnDateClickListener(){
        return this.clickListener;
    }
    public abstract void setDisplayText(String text);
}
