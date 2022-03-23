package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.screens.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object {
        const val EMAIL = "email"
    }

    private val sharedViewModel by viewModels<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        initializeData()

        if (savedInstanceState != null) {
            val savedEmail = savedInstanceState.getString(EMAIL)
            if (!savedEmail.isNullOrEmpty()) sharedViewModel.saveLoginDetails(savedEmail)
        }

        initializeStartDestination()
    }


    private fun initializeData() {
        addImages()
        sharedViewModel.initializeShoeList()
    }

    private fun initializeStartDestination() {
        val controller = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        val graph = controller.navInflater.inflate(R.navigation.navigation)
        graph.startDestination = when (sharedViewModel.isUserLoggedIn()) {
            true -> R.id.shoeListFragment
            false -> R.id.loginFragment
        }
        controller.graph = graph
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("Data is Saved")

        outState.putString(EMAIL, sharedViewModel.getEmail())
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
}
