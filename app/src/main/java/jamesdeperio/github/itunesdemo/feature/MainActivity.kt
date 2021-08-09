package jamesdeperio.github.itunesdemo.feature

import android.os.Bundle
import androidx.fragment.app.commit
import dagger.android.support.DaggerAppCompatActivity
import jamesdeperio.github.itunesdemo.R
import jamesdeperio.github.itunesdemo.feature.home.itunes.ItunesFragment

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayHomePage()
    }

    private fun displayHomePage() {
        supportFragmentManager.commit {
            this.replace(R.id.fragment_container,ItunesFragment.newInstance(),ItunesFragment::class.simpleName)
        }
    }
}