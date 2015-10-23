package sun.bob.mcalendarview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import sun.bob.mcalendarview.CellConfig;

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
        matchParentParams = new AbsListView.LayoutParams((int) CellConfig.cellWidth, (int) CellConfig.cellHeight);
        this.setLayoutParams(matchParentParams);
        this.setOrientation(VERTICAL);
        textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0));
        this.addView(textView);
    }

    @Override
    public void setDisplayText(String text) {
        textView.setText(text);
    }

    @Override
    protected void onMeasure(int measureWidthSpec,int measureHeightSpec){
        super.onMeasure(measureWidthSpec, measureHeightSpec);
    }
}
