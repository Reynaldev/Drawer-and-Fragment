package com.reyndev.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1 = Fragment1()
        val fragment2 = Fragment2()
        val fragment3 = Fragment3()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment1)
            addToBackStack(null)
            commit()
        }

        toggle = ActionBarDrawerToggle(this, layoutDrawer, R.string.drawer_open, R.string.drawer_close)
        layoutDrawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            val fragmentToShow = when(it.itemId) {
                R.id.navMenu1 -> fragment1
                R.id.navMenu2 -> fragment2
                else -> fragment3
            }

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragmentToShow)
                addToBackStack(null)
                commit()
            }

//            Toast.makeText(applicationContext, textToShow, Toast.LENGTH_SHORT).show()
            layoutDrawer.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}