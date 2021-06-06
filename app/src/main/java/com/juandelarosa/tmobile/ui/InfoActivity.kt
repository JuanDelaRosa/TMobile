package com.juandelarosa.tmobile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.juandelarosa.tmobile.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    private val binding: ActivityInfoBinding by lazy { ActivityInfoBinding.inflate(layoutInflater) }
    private val vm = InfoActivityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        val url = intent.extras?.get("url") as String
        val title = intent.extras?.get("title") as String
        vm.prepateUI(url,title)
        binding.vm = vm
        binding.lifecycleOwner = this
    }
}