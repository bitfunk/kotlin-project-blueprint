package de.wolfmontwe.blueprint.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import de.wolfmontwe.blueprint.android.R

/**
 * An activity that inflates a layout that has a NavHostFragment.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.navigation_host_fragment).navigateUp()

}
