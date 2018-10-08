package de.wolfmontwe.blueprint.android.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import de.wolfmontwe.blueprint.android.ui.MainViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import de.wolfmontwe.blueprint.android.R

class HomeFragment : Fragment() {

    private var model: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        home_logout_button.setOnClickListener {
            context.let {
                Toast.makeText(it, "Clicked!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
