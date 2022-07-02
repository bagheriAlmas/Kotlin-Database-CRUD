package ir.almasapps.kotlindatabasesqlitecrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.almasapps.kotlindatabasesqlitecrud.Database.View.CreateView
import kotlinx.android.synthetic.main.activity_insert_contact.*

class InsertContactActivity : AppCompatActivity() {

    private lateinit var createView : CreateView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_contact)

        createView = CreateView(this)

        btnInsert.setOnClickListener {
            createView.insertContact(txtName.text.toString(),txtPhone.text.toString(),txtEmail.text.toString(),R.drawable.ic_baseline_account_circle_24)
            finish()
        }
    }


}