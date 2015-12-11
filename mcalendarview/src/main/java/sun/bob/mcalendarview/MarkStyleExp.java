package sun.bob.mcalendarview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Created by 明明大美女 on 2015/12/10.
 */
public class MarkStyleExp {

    public static final int chooseColor = Color.rgb(105, 75, 125);
    public static final int lightGrayColor = Color.rgb(245, 245, 245);

    public static Drawable choose = new Drawable() {
        private Paint paint;

        {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(chooseColor);
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

    public static Drawable today = new Drawable() {
        private Paint paint;

        {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(lightGrayColor);
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