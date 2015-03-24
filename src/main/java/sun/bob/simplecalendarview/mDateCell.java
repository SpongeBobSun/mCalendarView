package sun.bob.simplecalendarview;

/**
 * Created by sunkuan on 15/3/17.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sunkuan on 14/12/28.
 */
public class mDateCell extends View {
    public static int CellBackgroundColor = 0;
    public static int CellBorder = 1;

    Paint textPaint;
    Paint rectPaint;
    Paint borderPaint;
    Paint circlePaint;
    String dateText;
    private int backgroundColor;
    private int selectColor;
    private int restoreBackgroundColor;
    private int borderColor;


    Context context;
    public mDateCell(Context contextArg) {
        super(contextArg);
        if(!this.isInEditMode()) {
            context = contextArg;
        }
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);

        rectPaint = new Paint();
        rectPaint.setColor(Color.GRAY);
        dateText = " ";
        backgroundColor = Color.WHITE;
        restoreBackgroundColor = backgroundColor;
        borderColor = backgroundColor;
        borderPaint = new Paint();
        borderPaint.setColor(borderColor);
//        selectColor = getResources().getColor(R.color.darkgray);

        textPaint.setTextSize(this.getWidth()/2);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.BLACK);

        circlePaint = new Paint();
//        circlePaint.setColor(getResources().getColor(R.color.backupColor));
    }


    public void setDate(int date){
        if(date == 0){
            dateText = " ";
        }else {
            dateText = String.format("%d", date);
        }
    }

    @Override
    public void onDraw(Canvas canvas){

        canvas.drawColor(backgroundColor);

        canvas.drawText(dateText,
                this.getWidth()/2,
                2*this.getHeight()/3,
                textPaint);
        canvas.drawLine(0,0,this.getWidth()-1,0,rectPaint);
        canvas.drawLine(this.getWidth()-1,0,this.getWidth()-1,this.getHeight()-1,rectPaint);
        canvas.drawLine(this.getWidth()-1,this.getHeight()-1,0,this.getHeight()-1,rectPaint);
        canvas.drawLine(0,this.getHeight()-1,0,0,rectPaint);
//        if(isMarked){
//            canvas.drawCircle(getWidth()/2f,(getHeight()*10f)/12f,(getHeight()/12f),circlePaint);
//        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_UP:
                emitClickBroadCast();
                return true;
            case MotionEvent.ACTION_DOWN:

                return true;
            case MotionEvent.ACTION_CANCEL:
                return false;
        }
        return false;
    }

    private void emitClickBroadCast(){
        int dateInt;
        try{
            dateInt = Integer.parseInt(dateText);
        }catch (NumberFormatException e){
            return;
        }

        Intent intent = new Intent("DateCellClick");
        intent.putExtra("CellIndex",dateInt);
        getContext().sendBroadcast(intent);
    }
    private void resizeFont(int size){
        textPaint.setTextSize(size);
    }

}