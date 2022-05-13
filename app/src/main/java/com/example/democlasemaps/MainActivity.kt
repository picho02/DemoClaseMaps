package com.example.democlasemaps

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map:GoogleMap

    companion object{
        const val REQUEST_CODE_LOCATION=0

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment()
    }

    private fun enableLocation(){
        if(!::map.isInitialized)return
        if(isLocationPermissionGranted())
        {
            map.isMyLocationEnabled=true
        }else{
            requestLocationPermission()
        }
    }


    private fun  createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap) {
        //Este codigo se ejecutara cuando el fregment termine de cargarse

        map = googleMap
        findViewById<Button>(R.id.btnRestaurante1).setOnClickListener {
            val coordinates = LatLng(19.310712835843994, -99.15401511669833)
            val marker=MarkerOptions().position(coordinates).title("La quema del burro")
            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordinates,18f),
                4000,null
            )
        }
        findViewById<Button>(R.id.btnRestaurante2).setOnClickListener {
            val coordinates = LatLng(19.306427477084235, -99.16041165622822)
            val marker=MarkerOptions().position(coordinates).title("El carboncito")
            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordinates,18f),
                4000,null
            )
        }
        findViewById<Button>(R.id.btnRestaurante3).setOnClickListener {
            val coordinates = LatLng(19.302835223171275, -99.16753344987059)
            val marker=MarkerOptions().position(coordinates).title("El Remolkito del Sirloin")
            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordinates,18f),
                4000,null
            )
        }
        findViewById<Button>(R.id.btnRestaurante4).setOnClickListener {
            val coordinates = LatLng(19.299987908824004, -99.10932359389902)
            val marker=MarkerOptions().position(coordinates).title("La esperanza")
            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordinates,18f),
                4000,null
            )
        }
        //tratar de acceder a la ubicacion del GPS
        enableLocation()


    }

    private fun crearMarker(){
        val coordenadas = LatLng(19.302516, -99.150592)
        val marker = MarkerOptions().position(coordenadas).title("Este es el estadio azteca")
        map.addMarker(marker)
    }


    private fun isLocationPermissionGranted()=
        ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            //Mostrar la ventan de permiso        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode){
            REQUEST_CODE_LOCATION->if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled=true
            }
            else{
                Toast.makeText(this,"Activa tus servicios manualmente",Toast.LENGTH_LONG).show()
            }
            else->{}

        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    private fun createMarker() {
        val coordinates = LatLng(19.300734, -99.148618)
        val marker=MarkerOptions().position(coordinates).title("Estadio Azteca")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates,18f),
            4000,null
        )

    }

}