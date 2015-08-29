package sun.bob.mcalendarview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sun.bob.mcalendarview.CellConfig;
import sun.bob.mcalendarview.MarkStyle;

/**
 * Created by bob.sun on 15/8/28.
 */
public class DefaultMarkView extends BaseMarkView {
    private TextView textView;
    private AbsListView.LayoutParams matchParentParams;
    private int orientation;

    private View sideBar;
    private TextView markTextView;
    private ShapeDrawable circleDrawable;

    public DefaultMarkView(Context context) {
        super(context);
    }

    public DefaultMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initLayout(){

        textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        switch (MarkStyle.current){
            case MarkStyle.DEFAULT:
                matchParentParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CellConfig.cellHeight);
                this.setLayoutParams(matchParentParams);
                this.setOrientation(HORIZONTAL);
                textView.setTextColor(Color.WHITE);
                circleDrawable = new ShapeDrawable(new OvalShape());
                circleDrawable.getPaint().setColor(MarkStyle.color);
                this.setPadding(20, 20, 20, 20);
                textView.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0));
                textView.setBackground(circleDrawable);
                this.addView(textView);
                return;
            case MarkStyle.BACKGROUND:
                matchParentParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CellConfig.cellHeight);
                this.setLayoutParams(matchParentParams);
                this.setOrientation(HORIZONTAL);
                textView.setTextColor(Color.WHITE);
                circleDrawable = new ShapeDrawable(new OvalShape());
                circleDrawable.getPaint().setColor(MarkStyle.color);
                this.setPadding(20, 20, 20, 20);
                textView.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0));
                textView.setBackground(circleDrawable);
                this.addView(textView);
                return;
            case MarkStyle.DOT:
                matchParentParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CellConfig.cellHeight);
                this.setLayoutParams(matchParentParams);
                this.setOrientation(VERTICAL);
                textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0));

                this.addView(new PlaceHolderVertical(getContext()));
                this.addView(textView);
                this.addView(new Dot(getContext()));
                return;
            case MarkStyle.RIGHTSIDEBAR:
                matchParentParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CellConfig.cellHeight);
                this.setLayoutParams(matchParentParams);
                this.setOrientation(HORIZONTAL);
                textView.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0));

                this.addView(new PlaceHolderHorizontal(getContext()));
                this.addView(new PlaceHolderHorizontal(getContext()));
                this.addView(textView);
                this.addView(new PlaceHolderHorizontal(getContext()));
                PlaceHolderHorizontal bar = new PlaceHolderHorizontal(getContext());
                bar.setBackgroundColor(MarkStyle.color);
                this.addView(bar);
                return;
            default:
                throw new IllegalArgumentException("Invalid Mark Style Configuration!");
        }
    }

    @Override
    public void setDisplayText(String text) {
        initLayout();
        textView.setText(text);
    }

    class PlaceHolderHorizontal extends View{

        LayoutParams params;
        public PlaceHolderHorizontal(Context context) {
            super(context);
            params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0);
            this.setLayoutParams(params);
        }

        public PlaceHolderHorizontal(Context context, AttributeSet attrs) {
            super(context, attrs);
            params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0);
            this.setLayoutParams(params);
            this.setLayoutParams(params);
        }
    }

    class PlaceHolderVertical extends View{

        LayoutParams params;
        public PlaceHolderVertical(Context context) {
            super(context);
            params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0);
            this.setLayoutParams(params);
        }

        public PlaceHolderVertical(Context context, AttributeSet attrs) {
            super(context, attrs);
            params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0);
            this.setLayoutParams(params);
            this.setLayoutParams(params);
        }
    }

    class Dot extends RelativeLayout{

        public Dot(Context context) {
            super(context);
            this.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0));
            View dotView = new View(getContext());
            LayoutParams lp = new RelativeLayout.LayoutParams(10, 10);
            lp.addRule(CENTER_IN_PARENT,TRUE);
            dotView.setLayoutParams(lp);
            ShapeDrawable dot = new ShapeDrawable(new OvalShape());

            dot.getPaint().setColor(MarkStyle.color);
            dotView.setBackground(dot);
            this.addView(dotView);
        }
    }
}
