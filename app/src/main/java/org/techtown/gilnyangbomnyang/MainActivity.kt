package org.techtown.gilnyangbomnyang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(supportFragmentManager.beginTransaction()){
            val fragment1 = FragmentA()
            replace(R.id.container, fragment1)
            commit()
        }
        bottom_navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.tab1 -> {
                    with(supportFragmentManager.beginTransaction()){
                        val fragment1 = FragmentA()
                        replace(R.id.container, fragment1)
                        commit()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab2 -> {
                    with(supportFragmentManager.beginTransaction()){
                        val fragment2 = FragmentB()
                        replace(R.id.container, fragment2)
                        commit()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab3 -> {
                    with(supportFragmentManager.beginTransaction()){
                        val fragment3 = FragmentC()
                        replace(R.id.container, fragment3)
                        commit()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }
}