package ir.almasapps.kotlindatabasesqlitecrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.almasapps.kotlindatabasesqlitecrud.Database.View.CreateView
import kotlinx.android.synthetic.main.activity_update_contact.*

class UpdateContactActivity : AppCompatActivity() {
    private lateinit var createView: CreateView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_contact)
        createView = CreateView(this)

        val itemID = intent.getIntExtra("contactID",0)
        val contact = createView.getContactByID(itemID)
        update_txtName.setText(contact.name)
        update_txtPhone.setText(contact.phone)
        update_txtEmail.setText(contact.email)


        update_btnUpdate.setOnClickListener {
            createView.updateContact(itemID,update_txtName.text.toString(),update_txtPhone.text.toString(),update_txtEmail.text.toString(),R.drawable.ic_baseline_account_circle_24)
            finish()
        }

        update_btnDelete.setOnClickListener {
            createView.deleteContactByID(itemID)
            finish()
        }
    }
}