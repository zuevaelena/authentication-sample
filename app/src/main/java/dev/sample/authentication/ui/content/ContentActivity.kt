package dev.sample.authentication.ui.content

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.sample.authentication.R

class ContentActivity : AppCompatActivity() {
    companion object {
        private const val FRAGMENT_CONTAINER = R.id.fragment_container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, ContentFragment.newInstance())
                .commit()
    }
}
