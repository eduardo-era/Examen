package com.example.examen.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.animation.AccelerateDecelerateInterpolator
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.examen.R
import com.example.examen.handlers.DataBaseHandler
import com.example.examen.utils.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity:BaseActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setDataUser()
        setAnimation()
    }

    private fun setDataUser(){
        val name = intent.getStringExtra("name")
        val mail = intent.getStringExtra("mail")
        val tel = intent.getStringExtra("phone")
        val cell = intent.getStringExtra("cel")
        val imageUrl = intent.getStringExtra("image")
        val city = intent.getStringExtra("city")
        val state = intent.getStringExtra("state")
        val country = intent.getStringExtra("country")

        user_detail_name.text = name
        user_detail_mail.text = mail
        user_detail_tel.text = tel
        user_detail_cel.text = cell
        user_detail_city.text = city
        user_detail_state.text = state
        user_detail_country.text = country

        Glide.with(this).load(imageUrl)
            .centerCrop()
            .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(user_image_details)
    }

    @SuppressLint("RtlHardcoded")
    fun setAnimation() {
        val slide = Slide()
        slide.slideEdge = Gravity.LEFT
        slide.duration = 400
        slide.interpolator = AccelerateDecelerateInterpolator()
        window.exitTransition = slide
        window.enterTransition = slide
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val  lat = intent.getStringExtra("lat")?.toDouble()
        val  lang = intent.getStringExtra("lang")?.toDouble()
        mMap = googleMap!!
        val userLocation = LatLng(lat!!, lang!!)
        mMap.addMarker(MarkerOptions().position(userLocation).title("Tu Ubicacion"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,4f))
    }

}