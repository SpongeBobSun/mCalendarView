package sun.bob.mcalendarview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

/**
 * Created by bob.sun on 15/8/28.
 */
public class DefaultCellView extends BaseCellView {
    private TextView textView;
    private AbsListView.LayoutParams matchParentParams;
    public DefaultCellView(Context context) {
        super(context);
        initLayout();
    }

    public DefaultCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    private void initLayout(){
        matchParentParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(matchParentParams);
        this.setOrientation(VERTICAL);
        textView = new TextView(getContext());
        this.addView(textView);
    }

    @Override
    public void setDisplayText(String text) {
        textView.setText(text);
    }
}
