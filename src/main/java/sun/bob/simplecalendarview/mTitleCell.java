package sun.bob.simplecalendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

/**
 * Created by sunkuan on 15/3/17.
 */

public class mTitleCell extends View {
    Paint textPaint;
    Paint rectPaint;
    String dateText;
    Point viewPos;
    int viewWidth;
    String titleTable[] = {
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };
    int backgroundColor;

    public mTitleCell(Context context, Point pos, int width, int day) {
        super(context);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        rectPaint = new Paint();
        rectPaint.setColor(Color.GRAY);
        dateText = "0";
        viewPos = pos;
        viewWidth = width;
        this.layout(viewPos.x, viewPos.y, viewPos.x + viewWidth, viewPos.y + viewWidth);
        dateText = titleTable[day];
        textPaint.setTextSize(this.getWidth() / 3);
        textPaint.setTextAlign(Paint.Align.CENTER);
        backgroundColor = Color.rgb(217,237,247);
    }

    @Override
    public void onDraw(Canvas canvas) {
        this.layout(viewPos.x, viewPos.y, viewPos.x + viewWidth, viewPos.y + viewWidth*2/3);
        canvas.drawColor(backgroundColor);
        canvas.drawText(dateText,
                this.getWidth()/2,
                2 * this.getHeight() / 3,
                textPaint);
        canvas.drawLine(0, 0, this.getWidth() - 1, 0, rectPaint);
        canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1, rectPaint);
        canvas.drawLine(this.getWidth() - 1, this.getHeight() - 1, 0, this.getHeight() - 1, rectPaint);
        canvas.drawLine(0, this.getHeight() - 1, 0, 0, rectPaint);
    }
}
