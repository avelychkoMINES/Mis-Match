package com.csci448.geolocatr.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.IOException

class CameraUtility(context: Context) {
    private val mCurrentLocationStateFlow: MutableStateFlow<Location?>
            = MutableStateFlow(null)
    val currentLocationStateFlow: StateFlow<Location?>
        get() = mCurrentLocationStateFlow.asStateFlow()

    private val mCurrentAddressStateFlow: MutableStateFlow<String>
            = MutableStateFlow("")
    val currentAddressStateFlow: StateFlow<String>
        get() = mCurrentAddressStateFlow.asStateFlow()

    private val mIsLocationAvailableStateFlow: MutableStateFlow<Boolean>
            = MutableStateFlow(false)
    val currentIsLocationAvailableStateFlow: StateFlow<Boolean>
        get() = mIsLocationAvailableStateFlow.asStateFlow()

    val locationRequest = LocationRequest
        .Builder(Priority.PRIORITY_HIGH_ACCURACY, 0L)
        .setMaxUpdates(1)
        .build()

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val location = locationResult.lastLocation
            mCurrentLocationStateFlow.value = location
        }
    }

    val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val geocoder = Geocoder(context)



    fun verifyLocationSettingsStates(states: LocationSettingsStates?) {
        mIsLocationAvailableStateFlow.update { states?.isLocationUsable ?: false }
    }

    fun checkPermissionAndGetLocation(activity: Activity,
                                      permissionLauncher: ActivityResultLauncher<Array<String>>) {
        // check if permissions are granted
        if (activity.checkSelfPermission( ACCESS_FINE_LOCATION ) == PERMISSION_GRANTED
                || activity.checkSelfPermission( ACCESS_COARSE_LOCATION ) == PERMISSION_GRANTED) {
            Log.d("LocationUtility", "have permission")

            fusedLocationProviderClient
                    .requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper())
        }

        // Section 1
        // permission has been granted to do what we need
        //Log that we have permission
        else {
            // permission is currently not granted
            // check if we should ask for permission or not
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_FINE_LOCATION)
                    || ActivityCompat.shouldShowRequestPermissionRationale(activity, ACCESS_COARSE_LOCATION)) {
                Log.d("LocationUtility", "permission denied")
                //val currentContext = LocalContext.current
                Toast
                    .makeText(
                        activity,
                        "We must access your location to plot where you are",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            } else {

                Log.d("LocationUtility", "asking for permission")
                permissionLauncher.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
            }
        }

        // Section 3
        //User hasn’t previously declined, ask them
        //Log asking for permission
        //Launch permissionLauncher for ACCESS_FINE_LOCATION and ACCESS_COARSE_LOCATION
    }

    fun removeLocationRequest() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    suspend fun getAddress(location: Location?) {
        val addressTextBuilder = StringBuilder()
        if (location != null) {
            try {
                val addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )
                if (addresses != null && addresses.isNotEmpty()) {
                    val address = addresses[0]
                    for (i in 0..address.maxAddressLineIndex) {
                        if (i > 0) {
                            addressTextBuilder.append("\n")
                        }
                        addressTextBuilder.append(address.getAddressLine(i))
                    }
                }
            } catch (e: IOException) {
                Log.e("location utility", "Error getting address", e)
            }
        }
        mCurrentAddressStateFlow.update { addressTextBuilder.toString() }
    }

    fun checkIfLocationCanBeRetrieved(
        activity: Activity,
        locationLauncher: ActivityResultLauncher<IntentSenderRequest>
    ) {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(activity)
        client.checkLocationSettings(builder.build()).apply {
            addOnSuccessListener { response ->
                verifyLocationSettingsStates(response.locationSettingsStates)
            }
            addOnFailureListener { exc ->
                mIsLocationAvailableStateFlow.update { false }
                if (exc is ResolvableApiException) {
                    locationLauncher
                        .launch(IntentSenderRequest.Builder(exc.resolution).build())
                }
            }
        }
    }
}
