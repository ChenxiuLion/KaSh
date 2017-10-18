package chenxiu.sh.com.ka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Chenxiu on 2016/5/21.
 */
public class KaReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = context.getPackageManager().getLaunchIntentForPackage("chenxiu.sh.com.ka");
        context.startActivity(intent1 );
    }
}
