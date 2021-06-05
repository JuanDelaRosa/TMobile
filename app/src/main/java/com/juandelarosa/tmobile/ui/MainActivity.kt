package com.juandelarosa.tmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        vm.getHomeFeeds()
    }
}