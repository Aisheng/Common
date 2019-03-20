package hanami.com.testdemo.view;

import android.content.Context;
import android.util.AttributeSet;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * @author lidaisheng
 * @date 2019/3/19
 */
public class GestureWebView extends WebView {

    private static final String TAG = "GestureWebView";

    private GestureDetector gestureDetector;

    public GestureWebView(Context context) {
        super(context);
        gestureDetector = new GestureDetector(getContext(), onGestureListener);
    }

    public GestureWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(getContext(), onGestureListener);
    }

    public GestureWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gestureDetector = new GestureDetector(getContext(), onGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TAG, "onFling x-->" + e1.getX() + " y-->" + e1.getY());
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            if (x > 100) {
                GestureWebView.this.goBack();
            } else if (x < -100) {
                // 左滑事件
                GestureWebView.this.goForward();
            }
            return true;
        }
    };

}
