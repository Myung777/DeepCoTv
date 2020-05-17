package deepcoding.study.deepcotv

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView =
            findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_layout, HomeFragment(), "home")
        transaction.commit()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        when (p0.itemId) {
            R.id.home_tap -> {
                transaction.replace(R.id.main_layout, HomeFragment(), "home")
            }
            R.id.vod_tap -> {
                transaction.replace(R.id.main_layout, VodFragment(), "vod")
            }
            R.id.live_tap -> {
                transaction.replace(R.id.main_layout, LiveFragment(), "live")
            }
            R.id.setting_tap -> {
                transaction.replace(R.id.main_layout, SettingFragment(), "setting")
            }
        }
        transaction.addToBackStack(null)
        transaction.commit()
        return true
    }

    private fun updateBottomMenu(navigation: BottomNavigationView) {
        val homeTag: Fragment? = supportFragmentManager.findFragmentByTag("home")
        val vodTag: Fragment? = supportFragmentManager.findFragmentByTag("vod")
        val liveTag: Fragment? = supportFragmentManager.findFragmentByTag("live")
        val settingTag: Fragment? = supportFragmentManager.findFragmentByTag("setting")

        if (homeTag != null && homeTag.isVisible) navigation.menu.findItem(R.id.home_tap)
            .isChecked =
            true
        else if (vodTag != null && vodTag.isVisible) navigation.menu.findItem(R.id.vod_tap)
            .isChecked =
            true
        else if (liveTag != null && liveTag.isVisible) navigation.menu.findItem(R.id.live_tap)
            .isChecked =
            true
        else if (settingTag != null && settingTag.isVisible) navigation.menu.findItem(R.id.setting_tap)
            .isChecked =
            true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        updateBottomMenu(bottomNavigationView)
    }
}
