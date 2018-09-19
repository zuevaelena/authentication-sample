package dev.sample.authentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    companion object {
        private const val FRAGMENT_CONTAINER = R.id.fragment_container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, LoginFragment.newInstance("tmp", "tmp"))
                .commit()
    }
}
