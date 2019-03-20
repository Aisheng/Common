package hanami.com.testdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;

import com.breeze.xqqrcode.R;

import hanami.com.testdemo.view.GestureWebView;

/**
 * @author lidaisheng
 * @date 2019/1/9
 */
public class NextActivity extends AppCompatActivity {

    private GestureWebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        mWebView = findViewById(R.id.v_web);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("https://www.baidu.com/");
            }
        });

    }


}
