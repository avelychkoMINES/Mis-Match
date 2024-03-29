package com.csci448.avelychko.mis_match

import android.Manifest
import android.app.PendingIntent
import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.startActivity
import java.text.SimpleDateFormat
import java.util.*

class NotificationReceiver : BroadcastReceiver() {
    val LOG_TAG = "NotificationReceiver"

    companion object {
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
        //val alarmDelayInSeconds = 5
        //val alarmTimeInUTC = System.currentTimeMillis() + alarmDelayInSeconds * 1_000L
        
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
          }

        var alarmTimeInUTC = calendar.timeInMillis

        if (alarmTimeInUTC <= System.currentTimeMillis()) {
            // If the alarm time is in the past, add 1 day to set it for the next occurrence
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            alarmTimeInUTC = calendar.timeInMillis
        }

        
        // Set the repeating alarm
        
        Log.d(LOG_TAG, "Setting alarm for ${
            SimpleDateFormat("MM/dd/yyyy HH:mm:ss",
            Locale.US).format(Date(alarmTimeInUTC))}")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d(LOG_TAG, "running on Version S or newer, checking if can schedule exact alarms")
            if (alarmManager.canScheduleExactAlarms()) {
                Log.d(LOG_TAG, "can schedule exact alarms: ${pendingIntent}")
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    alarmTimeInUTC,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                    )
            } else {
                Log.d(LOG_TAG, "can’t schedule exact alarms, launching intent to bring up settings")
                val settingsIntent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(activity, settingsIntent, null)
            }
        } else {
            Log.d(LOG_TAG, "running on Version R or older, can set alarm directly")
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmTimeInUTC,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(LOG_TAG, "received alarm for action ${intent.action}")
        val channelName = "notificationreceiver"
        val channelDesc = "channel created to send alarm for mismatch"

        if (intent.action == ALARM_ACTION) {

            if (ActivityCompat
                    .checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
                Log.d(LOG_TAG, "have permission to post notifications")
                val notificationManager = NotificationManagerCompat.from(context)
                val channel =
                    NotificationChannel(
                        "0",
                        channelName,
                        NotificationManager.IMPORTANCE_DEFAULT
                    ).apply {
                        description = channelDesc
                    }
                notificationManager.createNotificationChannel(channel)

                val deepLinkPendingIntent = MainActivity.createPendingIntent(context)
                val notification = NotificationCompat.Builder(context, "0")
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentTitle("Mis-Match")
                    .setContentText("Time to create an outfit for the day!")
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
