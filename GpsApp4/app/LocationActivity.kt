import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding

import com.example.app.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o gerenciador de localização
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        // Cria um LocationListener para receber atualizações de localização
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // Atualiza as views com as novas coordenadas
                binding.tvLatitude.text = "Latitude: " + location.latitude.toString()
                binding.tvLongitude.text = "Longitude: " + location.longitude.toString()
            }
            ...
        }
    }

    override fun onStart() {
        super.onStart()

        // Verifica se a permissão de localização foi concedida
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicita a permissão de localização
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else {
            // Inicia a atualização de localização
            startLocationUpdates()
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        // Configura as atualizações de localização
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListener
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Verifica se a permissão de localização foi concedida
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Inicia a atualização de localização
            startLocationUpdates()
        }
    }

    override fun onStop() {
        super.onStop()

        // Para a atualização de localização
        locationManager.removeUpdates(locationListener)
    }
}