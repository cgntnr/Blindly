package ch.epfl.sdp.blindly.location

import android.location.Location
import android.content.Context
import android.location.LocationManager
import android.util.Log
import ch.epfl.sdp.blindly.MapsActivity

class AndroidLocationService(private var context: Context) : LocationService {

    private val MIN_TIME_FOR_UPDATE = 1L
    private val MIN_DISTANCE_FOR_UPDTAE = 1F

    private var isGPSEnable = false
    private var isNetworkEnable = false
    private var canGetLocation = false
    private var location: Location? = null

    private lateinit var locationManager: LocationManager
    private lateinit var locationPermission: LocationPermission

    init {
        getCurrentLocation()
    }

    override fun getCurrentLocation(): Location? {
        try {
            locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            //check for GPS
            isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            Log.v("isGPSEnable", "=$isGPSEnable")

            //check for network
            isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            Log.v("isNetworkEnable", "=$isNetworkEnable")

            if((isGPSEnable && locationPermission.fineGrainPermission) || (isNetworkEnable && locationPermission.coarseGrainPermission)) {
                canGetLocation = true
                location = if(isGPSEnable && locationPermission.fineGrainPermission) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDTAE, this)
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                } else {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_FOR_UPDATE,  MIN_DISTANCE_FOR_UPDTAE, this)
                    locationManager.getLastKnownLocation((LocationManager.NETWORK_PROVIDER))
                }

            }

        } catch(e: SecurityException) {
            throw e
        }
        return location
    }

    override fun onLocationChanged(loc: Location) {
        location = getCurrentLocation()
    }
}