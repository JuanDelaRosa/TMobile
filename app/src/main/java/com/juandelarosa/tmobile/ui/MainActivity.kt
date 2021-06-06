package com.juandelarosa.tmobile.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.juandelarosa.tmobile.app.LayoutUtils
import com.juandelarosa.tmobile.app.TMobileApp
import com.juandelarosa.tmobile.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val vm: MainActivityViewModel by lazy { MainActivityViewModel.MainActivityViewModelFactory((application as TMobileApp)).create(MainActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.feeds.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.feeds.adapter = CardAdapter()
        vm.getHomeFeeds()
        vm.homeFeeds.observe(this, {cards ->
            cards?.let {
                LayoutUtils.removeSplashScreen(binding.logo)
                (binding.feeds.adapter as CardAdapter).setData(it)
                vm.saveBackup(cards)
            }
        })

        vm.isNotInternet.observe(this, {
            if(it==true){
                vm.loadBackup()
            }
        })

        vm.error.observe(this, {
            LayoutUtils.showSnack(binding.root, it)
        })
    }
}