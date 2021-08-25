package com.example.edittextlineheighttest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.util.AttributeSet;


public class LinedEditText extends androidx.appcompat.widget.AppCompatEditText {
    private int totalLineCount;
    private int lineSpacing;
    private int viewHeight;
    private int lineHeight;
    private Layout layout;
    private int left;
    private int right;
    private int paddingLeft;
    private int paddingRight;
    private int baseline;
    private int bottom;
    private Paint mPaint;
    private int firstBottom;

    // we need this constructor for LayoutInflater
    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);

        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        baseline = 0;
        bottom = 0;


    }

    @Override
    protected void onDraw(Canvas canvas) {
     //작동하는 코드
        layout = getLayout();
        left = getLeft();
        right = getRight();
        lineSpacing = (int) getLineSpacingExtra();


        // 1. Draw line for existing rows
        for (int i = 0; i < getLineCount(); i++) {
            //int baseline = layout.getLineBaseline(i);
            bottom = layout.getLineBottom(i);
            baseline = bottom;
           // baseline = bottom + 15;

            //line spacing을 사용하면 이상한 듯
            //baseline = baseline + lineSpacing;

            if (i==0) {
                firstBottom = bottom;
                //상단 오렌지색 사각형 그림
                mPaint.setStrokeWidth(0);
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(Color.parseColor("#f0dab5"));
                canvas.drawRect(left, 0, right, baseline, mPaint);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(0x800000FF);
            }

//            if (i != 0) {
//                baseline = baseline + lineSpacing;
//            }

            canvas.drawLine(
                    left + paddingLeft,
                    baseline,
                    right - paddingRight,
                    baseline,
                    mPaint
            );


        }


        viewHeight = getHeight();
        lineHeight = getLineHeight();

        totalLineCount = viewHeight / lineHeight;


        for (int i = getLineCount() + 1; i < totalLineCount; i++) {

            canvas.drawLine(left + paddingLeft, baseline,right - paddingRight, baseline, mPaint);
            baseline += firstBottom;

//            if (i != 0) {
//                baseline = baseline + lineSpacing;
//            }

        }

        super.onDraw(canvas);


    }


}
