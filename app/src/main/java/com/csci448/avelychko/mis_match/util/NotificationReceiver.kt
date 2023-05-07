package com.csci448.avelychko.mis_match.util

import android.Manifest
import android.app.PendingIntent
import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.startActivity
import com.csci448.avelychko.mis_match.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class NotificationReceiver : BroadcastReceiver() {
    var lastLocation: Location? = null
    val LOG_TAG = "LocationAlarmReceiver"

    companion object locAlarm{
        private const val ALARM_ACTION = "448_ALARM_ACTION"
        private fun createIntent(context: Context): Intent {
            val intent = Intent(context, NotificationReceiver::class.java).apply {
                action = ALARM_ACTION
            }
            return intent
        }

    }
    @SuppressLint("ServiceCast")
    private fun scheduleAlarm(activity: Activity) {
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = createIntent(activity)
        val pendingIntent = PendingIntent.getBroadcast(
            activity,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val alarmDelayInSeconds = 10
        val alarmTimeInUTC = System.currentTimeMillis() + alarmDelayInSeconds * 1_000L
        Log.d("LocationAlarmReceiver", "Setting alarm for ${
            SimpleDateFormat("MM/dd/yyyy HH:mm:ss",
            Locale.US).format(Date(alarmTimeInUTC))}")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d(LOG_TAG, "running on Version S or newer, checking if can schedule exact alarms")
            if (alarmManager.canScheduleExactAlarms()) {
                Log.d(LOG_TAG, "can schedule exact alarms")
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                    alarmTimeInUTC,
                    pendingIntent)
            } else {
                Log.d(LOG_TAG, "can’t schedule exact alarms, launching intent to bring up settings")
                val settingsIntent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(activity, settingsIntent, null)
            }
        } else {
            Log.d(LOG_TAG, "running on Version R or older, can set alarm directly")
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                alarmTimeInUTC,
                pendingIntent)
        }

    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(LOG_TAG, "received alarm for action ${intent.action}")
        val channelName = "locationalarmreceiver"
        val channelDesc = "channel created to send alarm for geolocatr"

        if (intent.action == ALARM_ACTION) {

            if (ActivityCompat
                    .checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
                Log.d(LOG_TAG, "have permission to post notifications")
                val notificationManager = NotificationManagerCompat.from(context)
                val channel =
                    NotificationChannel(
                        "channelId",
                        channelName,
                        NotificationManager.IMPORTANCE_DEFAULT
                    ).apply {
                        description = channelDesc
                    }
                notificationManager.createNotificationChannel(channel)

                val deepLinkPendingIntent = MainActivity.createPendingIntent(context)
                val notification = NotificationCompat.Builder(context, "channelId")
                    .setSmallIcon(android.R.drawable.ic_dialog_map)
                    .setContentTitle("Time to create an outfit for the day!")
                    .setContentIntent(deepLinkPendingIntent)
                    .setAutoCancel(true)
                    .build()
                notificationManager.notify(0, notification)

            }
        }
    }
    fun checkPermissionAndScheduleAlarm(
        activity: Activity,
        permissionLauncher: ActivityResultLauncher<Array<String>>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.d(LOG_TAG, "running on Version Tiramisu or newer, need permission")
            if (activity.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
                Log.d(LOG_TAG, "have notification permission")
                scheduleAlarm(activity)
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.POST_NOTIFICATIONS)) {
                            Log.d(LOG_TAG, "previously denied notification permission")
                            // display toast with reason
                        } else {
                    Log.d(LOG_TAG, "request notification permission")
                    permissionLauncher.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
                }
            }
        } else {
            Log.d(LOG_TAG, "running on Version S or older, post away")
            scheduleAlarm(activity)
        }
    }

}
