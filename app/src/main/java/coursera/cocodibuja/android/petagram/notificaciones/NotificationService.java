package coursera.cocodibuja.android.petagram.notificaciones;

/**
 * Created by nicopro on 4/10/16.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import coursera.cocodibuja.android.petagram.MainActivity;
import coursera.cocodibuja.android.petagram.R;
import coursera.cocodibuja.android.petagram.vista.fragment.PerfilFragment;

/**
 * Created by nicopro on 29/9/16.
 */

public class NotificationService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        Intent i = new Intent(this, PerfilFragment.class);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{i}, PendingIntent.FLAG_ONE_SHOT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent1 = PendingIntent.getActivities(getApplicationContext(),0,new Intent[]{intent1},PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_active)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent1)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificacion.build());


    }




}
