package sun.bob.mcalendarview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Created by bob.sun on 15/8/28.
 */
public class MarkStyle {
    public static final int BACKGROUND = 1;
    public static final int DOT = 2;
    public static final int LEFTSIDEBAR = 3;
    public static final int RIGHTSIDEBAR = 4;
    public static final int TEXT = 5;
    public static final int DEFAULT = -1;

    public static int color = Color.rgb(0, 148, 243);

    public static String text;
    public static int textColor;

    public static int current = DEFAULT;

    public static Drawable todayBackground = new Drawable() {
        private Paint paint;
        {
            paint = new Paint();
            paint.setColor(Color.rgb(63, 81, 200));
        }
        @Override
        public void draw(Canvas canvas) {
            canvas.drawCircle(canvas.getWidth() / 2,
                    canvas.getHeight() / 2,
                    canvas.getHeight() / 3,
                    paint);
        }

        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return 0;
        }
    };
}
