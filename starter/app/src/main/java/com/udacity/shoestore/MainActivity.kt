package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    companion object {
        const val EMAIL = "email"
    }

    private val sharedViewModel by viewModels<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        initializeData()

        setSupportActionBar(binding?.toolbar)
        if (savedInstanceState != null) {
            val savedEmail = savedInstanceState.getString(EMAIL)
            if (!savedEmail.isNullOrEmpty()) sharedViewModel.email = savedEmail
        }

        initializeStartDestination()
    }

    private fun initializeData() {
        addImages()
        sharedViewModel.initializeShoeList()
    }

    private fun initializeStartDestination() {
        val controller =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        val graph = controller.navInflater.inflate(R.navigation.navigation)
        graph.startDestination = when (sharedViewModel.isUserLoggedIn()) {
            true -> R.id.shoeListFragment
            false -> R.id.loginFragment
        }
        controller.graph = graph
        val appBarConfig = AppBarConfiguration(controller.graph)
        binding?.toolbar?.setupWithNavController(controller, appBarConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("Data is Saved")

        outState.putString(EMAIL, sharedViewModel.email)
    }

    private fun addImages() {
        val imageList = mutableListOf(
            R.drawable.shoe_image_1,
            R.drawable.shoe_image_2,
            R.drawable.shoe_image_3,
            R.drawable.shoe_image_4,
            R.drawable.shoe_image_5
        )
        sharedViewModel.setImageList(imageList)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
