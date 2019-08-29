package jotaro.alphabearhack;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.io.FileNotFoundException;

/**
 * Foreground service. It creates a head view.
 * The pending intent allows to go back to the settings activity.
 */
public class HeadService extends Service {

    private final static int FOREGROUND_ID = 999;

    private HeadLayer headLayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);


        Notification notification = new Notification.Builder(this)
                .setContentTitle(getText(R.string.notificationTitle))
                .setContentText(getText(R.string.notificationText))
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();

        try {
            headLayer = new HeadLayer(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        startForeground(FOREGROUND_ID, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
        headLayer.destroy();
        stopForeground(true);
    }
}
