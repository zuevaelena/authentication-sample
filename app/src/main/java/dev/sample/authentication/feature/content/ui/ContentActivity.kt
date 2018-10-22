package dev.sample.authentication.feature.content.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.sample.authentication.R
import dev.sample.authentication.feature.bottommenu.ui.BottomSheetMenuFragment
import kotlinx.android.synthetic.main.activity_content.bottom_app_bar

class ContentActivity : AppCompatActivity() {
    companion object {
        private const val FRAGMENT_CONTAINER = R.id.fragment_container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        setSupportActionBar(bottom_app_bar)

        supportFragmentManager
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, ContentFragment.newInstance())
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_bottomappbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.app_bar_search -> {
                Toast.makeText(this, "search to be implemented", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                val bottomMenuFragment = BottomSheetMenuFragment()
                bottomMenuFragment.show(supportFragmentManager, bottomMenuFragment.tag)
            }
        }
        return true
    }
}
