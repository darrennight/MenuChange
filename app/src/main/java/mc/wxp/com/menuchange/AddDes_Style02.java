package mc.wxp.com.menuchange;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import mc.wxp.com.menuchange.OnStatChangeListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 加变减，动画效果2
 * Created by whisper on 14-11-29.
 */
public class AddDes_Style02 extends View implements View.OnLongClickListener, View.OnClickListener {

    private int PERIOD = 20;
    private int mWidth = 0;
    private int mHeight = 0;

    private Paint mPaintH;
    private Paint mPaintV;

    private double mSqrt;

    private CusLine mHor;
    private CusLine mVer;
    private CusLine mQua1;
    private CusLine mQua2;

    private Context mContext;
    private Timer mTimer1;
    private TimerTask mTimerTask1;

    private Timer mTimer2;
    private TimerTask mTimerTask2;

    private int mDegree1 = 0;
    private int mDegree2 = 0;

    private int mBgColor = R.color.bg_color;
    private int mLineColor = R.color.line_color;
    /*
     *动画分两步进行，true为第一步，false为第二步
     */
    boolean flag = true;
    //boolean mConvertToAdd = false;

    /*
     *当前的状态 true:加号；false：减号
     */
    boolean mCurrentState = true;

    public AddDes_Style02(Context context) {
        super(context);
        init(context,null,0);
    }

    public AddDes_Style02(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public AddDes_Style02(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public void init(Context context,AttributeSet attrs, int defStyleAttr) {
        mContext = context;

        TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.AddDes_Style01, defStyleAttr, 0);

        mBgColor = attrsArray.getInt(R.styleable.AddDes_Style01_backgroundcolor,R.color.bg_color);
        mLineColor = attrsArray.getInt(R.styleable.AddDes_Style01_linecolor,R.color.line_color);
        attrsArray.recycle();

        mPaintH = new Paint();
        mPaintH.setAntiAlias(true);
        mPaintH.setColor(mLineColor);
        mPaintH.setDither(true);
        mPaintH.setStyle(Paint.Style.STROKE);
        mPaintH.setStrokeWidth(10f);

        mPaintV = new Paint();
        mPaintV.setAntiAlias(true);
        mPaintV.setColor(mBgColor);
        mPaintV.setDither(true);
        mPaintV.setStyle(Paint.Style.FILL);
        mPaintV.setStrokeWidth(10f);

        mSqrt = Math.sqrt((double) 2);
        mHor = new CusLine();
        mVer = new CusLine();
        mQua1 = new CusLine();
        mQua2 = new CusLine();

        setOnLongClickListener(this);
        setOnClickListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        mWidth = widthSize;

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        mHeight = heightSize;

        float EDG = (float) mWidth;

        mHor.startX = (float) (EDG / 2 - EDG / 2 / mSqrt);
        mHor.startY = EDG / 2;
        mHor.endX = (float) (EDG / 2 + EDG / 2 / mSqrt);
        mHor.endY = EDG / 2;

        mVer.startX = EDG / 2;
        mVer.startY = (float) (EDG / 2 - EDG / 2 / mSqrt);
        mVer.endX = EDG / 2;
        mVer.endY = (float) (EDG / 2 + EDG / 2 / mSqrt);

        mQua1.startX = EDG/4;
        mQua1.startY = EDG/4;
        mQua1.endX = 3*EDG/4;
        mQua1.endY = 3*EDG/4;

        mQua2.startX = 3*EDG/4;
        mQua2.startY = EDG/4;
        mQua2.endX = EDG/4;
        mQua2.endY = 3*EDG/4;

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/2,mPaintV);
        if (mCurrentState == true) {
            if (flag == true) {
                canvas.save();
                canvas.rotate(mDegree1, mWidth / 2, mHeight / 2);
                canvas.drawLine(mHor.startX, mHor.startY, mHor.endX, mHor.endY, mPaintH);
                canvas.drawLine(mVer.startX, mVer.startY, mVer.endX, mVer.endY, mPaintH);
                canvas.restore();
            } else {
                canvas.save();
                canvas.rotate(-mDegree2, mWidth / 2, mHeight / 2);
                canvas.drawLine(mQua1.startX, mQua1.startY, mQua1.endX, mQua1.endY, mPaintH);
                canvas.restore();
                canvas.save();
                canvas.rotate(mDegree2, mWidth / 2, mHeight / 2);
                canvas.drawLine(mQua2.startX, mQua2.startY, mQua2.endX, mQua2.endY, mPaintH);
                canvas.restore();
            }
        } else {
            if (flag == true) {
                canvas.save();
                canvas.rotate(-mDegree1, mWidth / 2, mHeight / 2);
                canvas.drawLine(mHor.startX, mHor.startY, mHor.endX, mHor.endY, mPaintH);
                canvas.restore();
                canvas.save();
                canvas.rotate(mDegree1, mWidth / 2, mHeight / 2);
                canvas.drawLine(mHor.startX, mHor.startY, mHor.endX, mHor.endY, mPaintH);
                canvas.restore();

            } else {
                canvas.save();
                canvas.rotate(-mDegree2, mWidth / 2, mHeight / 2);
                canvas.drawLine(mQua1.startX, mQua1.startY, mQua1.endX, mQua1.endY, mPaintH);
                canvas.drawLine(mQua2.startX, mQua2.startY, mQua2.endX, mQua2.endY, mPaintH);
                canvas.restore();
            }

        }

    }

    @Override
    public boolean onLongClick(View v) {
        //Toast.makeText(mContext, "LONG", Toast.LENGTH_SHORT).show();
        startStep1();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (mOnStateChangeListener!=null){
            mOnStateChangeListener.changeState(mCurrentState);
        }
    }

    OnStatChangeListener mOnStateChangeListener;

    public void setOnStateChange(OnStatChangeListener listener){
        mOnStateChangeListener = listener;
    }

    public void startStep1() {
        mDegree1 = 0;
        mDegree2 = 0;
        flag = true;
        mTimer1 = new Timer();
        mTimerTask1 = new TimerTask() {
            @Override
            public void run() {
                convertVer();
            }
        };

        mTimer1.schedule(mTimerTask1, PERIOD, PERIOD);
    }

    public void startStep2() {
        mTimer2 = new Timer();
        mTimerTask2 = new TimerTask() {
            @Override
            public void run() {
                convertHor();
            }
        };

        mTimer2.schedule(mTimerTask2, PERIOD, PERIOD);
    }

    public void convertVer() {
        mDegree1 += 3;
        mHandler.sendEmptyMessage(0);
        if (mDegree1 >= 45) {
            mTimer1.cancel();
            mHandler.sendEmptyMessage(1);
        }
    }

    public void convertHor() {
        mDegree2 += 3;
        mHandler.sendEmptyMessage(0);
        if (mDegree2 >= 45) {
            mTimer2.cancel();
            mHandler.sendEmptyMessage(2);
        }

    }

    private class CusLine {
        float startX;
        float startY;
        float endX;
        float endY;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                //Log.e("wxp","000");
                invalidate();
            }
            if (msg.what == 1) {
                //Log.e("wxp","111");
                flag = false;
                mDegree1 = 0;
                startStep2();
            }
            if (msg.what == 2) {
                //Log.e("wxp","222");
                //mDegree2 = 0;
                flag = true;
                if (mCurrentState == true)
                    mCurrentState = false;
                else mCurrentState = true;
            }
        }
    };


}
