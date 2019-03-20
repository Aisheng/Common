package hanami.com.testdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author lidaisheng
 * @date 2019/1/25
 */
public class AntiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("receiver","lidaisheng");
    }
}
