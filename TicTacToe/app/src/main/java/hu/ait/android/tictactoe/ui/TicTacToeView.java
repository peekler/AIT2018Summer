package hu.ait.android.tictactoe.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeView extends View {

    private Paint paintBackGround;
    private Paint paintLine;

    private PointF circle = null;

    private List<PointF> circles = new ArrayList<PointF>();


    public TicTacToeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBackGround = new Paint();
        paintBackGround.setColor(Color.BLACK);
        paintBackGround.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0,
                getWidth(), getHeight(), paintBackGround);

        canvas.drawLine(0, 0, getWidth(), getHeight(),
                paintLine);

        if (circle != null) {
            canvas.drawCircle(circle.x, circle.y,
                    70,
                    paintLine);
        }

        for (PointF circle : circles) {
            canvas.drawCircle(circle.x, circle.y,
                    50, paintLine);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            circle = new PointF(event.getX(), event.getY());
            invalidate(); // calls onDraw again
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            circles.add(new PointF(event.getX(), event.getY()));
            circle = null;
            invalidate();
        }


        return true;
    }

    public void clearCircles() {
        circles.clear();
        invalidate();
    }
}
