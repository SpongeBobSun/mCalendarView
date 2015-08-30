package sun.bob.mcalendarviewtest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import sun.bob.mcalendarview.views.BaseCellView;

/**
 * Created by bob.sun on 15/8/30.
 */
public class DateCellView extends BaseCellView {
    public DateCellView(Context context) {
        super(context);
    }

    public DateCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setDisplayText(String text) {
        ((TextView) this.findViewById(R.id.id_cell_text)).setText(text);
    }
}
