package rf.digitworld.ilikepizza;

/**
 * Created by Дмитрий on 01.04.2015.
 */

import android.content.Context;
        import android.content.Intent;
        import android.os.SystemClock;
        import android.support.v4.content.WakefulBroadcastReceiver;
        import android.util.Log;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // This is the Intent to deliver to our service.
        Intent service = new Intent(context, GcmBroadcastReceiver.class);

        // Start the service, keeping the device awake while it is launching.
        Log.i("SimpleWakefulReceiver", "Starting service @ " + SystemClock.elapsedRealtime());
        startWakefulService(context, service);
    }
}