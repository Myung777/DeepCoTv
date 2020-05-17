package deepcoding.study.deepcotv

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_layout, HomeFragment(),"home")
        transaction.commit()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val transaction = supportFragmentManager.beginTransaction()

        when (p0.itemId) {
            R.id.home_tap -> {
                transaction.replace(R.id.main_layout, HomeFragment(),"home")
            }
            R.id.vod_tap -> {
                transaction.replace(R.id.main_layout, VodFragment(),"vod")
            }
            R.id.live_tap -> {
                transaction.replace(R.id.main_layout, LiveFragment(),"live")
            }
            R.id.setting_tap -> {
                transaction.replace(R.id.main_layout, SettingFragment(),"setting")
            }
        }
        transaction.addToBackStack(null)
        transaction.commit()
        return true
    }

    private fun updateBottomMenu(navigation: BottomNavigationView) {
        val tag1: Fragment? = supportFragmentManager.findFragmentByTag("home")
        val tag2: Fragment? = supportFragmentManager.findFragmentByTag("vod")
        val tag3: Fragment? = supportFragmentManager.findFragmentByTag("live")
        val tag4: Fragment? = supportFragmentManager.findFragmentByTag("setting")

        if(tag1 != null && tag1.isVisible()) navigation.getMenu().findItem(R.id.home_tap).setChecked(true)
        else if(tag2 != null && tag2.isVisible()) navigation.getMenu().findItem(R.id.vod_tap).setChecked(true)
        else if(tag3 != null && tag3.isVisible()) navigation.getMenu().findItem(R.id.live_tap).setChecked(true)
        else if(tag4 != null && tag4.isVisible()) navigation.getMenu().findItem(R.id.setting_tap).setChecked(true)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        updateBottomMenu(bottomNavigationView)
    }
}
