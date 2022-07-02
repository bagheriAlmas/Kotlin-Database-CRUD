package ir.almasapps.kotlindatabasesqlitecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.almasapps.kotlindatabasesqlitecrud.Adapter.ContactAdapter
import ir.almasapps.kotlindatabasesqlitecrud.Database.View.CreateView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var createView : CreateView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createView = CreateView(this)

        listview.adapter =ContactAdapter(this,createView.getAllContacts())

        fab.setOnClickListener {
            startActivity(Intent(this, InsertContactActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        listview.adapter =ContactAdapter(this,createView.getAllContacts())
    }
}