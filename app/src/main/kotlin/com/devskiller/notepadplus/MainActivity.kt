package com.devskiller.notepadplus

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.devskiller.notepadplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).root)

        // START YOUR CHANGE

        var fragment: Fragment = WelcomeFragment.newInstance()

        if(NoteLab.notes.isNotEmpty()){
            fragment= NoteListFragment.newInstance()
        }

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fl_fragment_container, fragment).commit()

        // END YOUR CHANGE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.create_note -> {
            // START YOUR CHANGE
            val intent = Intent(this@MainActivity, ChangeNoteActivity::class.java)
            startActivity(intent)
            finish()
            // END YOUR CHANGE
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
