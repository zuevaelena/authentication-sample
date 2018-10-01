package dev.sample.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        private const val FRAGMENT_CONTAINER = R.id.fragment_container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO get user id from a proper place
        val currentUserId: String? = null
        supportFragmentManager
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, ContentFragment.newInstance(currentUserId))
                .commit()
    }
}
