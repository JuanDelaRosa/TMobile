package com.juandelarosa.tmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        vm.homeFeeds.observe(this, {cards ->
            cards?.let {
                vm.saveBackup(cards)
            }
        })

        vm.isNotInternet.observe(this, {
            if(it==true){
                vm.loadBackup()
            }
        })

        vm.error.observe(this, {
            Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
        })
    }
}