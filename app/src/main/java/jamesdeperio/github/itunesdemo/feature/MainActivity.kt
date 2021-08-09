package jamesdeperio.github.itunesdemo.feature

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import dagger.android.support.DaggerAppCompatActivity
import jamesdeperio.github.itunesdemo.R
import jamesdeperio.github.itunesdemo.base.setOnThrottleClickListener
import jamesdeperio.github.itunesdemo.databinding.ActivityMainBinding
import jamesdeperio.github.itunesdemo.feature.home.itunes.ItunesFragment

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayHomePage()
        binding.ivBack.setOnThrottleClickListener {
            super.onBackPressed()
        }
    }

    private fun displayHomePage() {
        supportFragmentManager.commit {
            this.replace(R.id.fragment_container,ItunesFragment.newInstance(),ItunesFragment::class.simpleName)
        }
    }

    fun hideBackButton() {
        binding.ivBack.visibility = View.GONE
    }

    fun showBackButton() {
        binding.ivBack.visibility = View.VISIBLE
    }

}