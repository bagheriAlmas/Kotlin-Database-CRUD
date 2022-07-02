package ir.almasapps.kotlindatabasesqlitecrud.Database.View

import android.content.Context
import ir.almasapps.kotlindatabasesqlitecrud.Database.Controller.MyDB
import ir.almasapps.kotlindatabasesqlitecrud.Database.Model.Contact

class CreateView(context: Context) {
    private var myDB = MyDB(context)

    //Insert Contact
    fun insertContact(name: String, phone: String, email: String, image: Int): Long {
        return myDB.insertContacrt(Contact(9,name, phone, email, image))
    }


    // get All Contacts
    fun getAllContacts(): List<Contact> {
        return myDB.getAllContacts()
    }

    // GET CONTACT BY ID
    fun getContactByID(id: Int): Contact {
        return myDB.getContactByID(id)
    }




    //UPDATE CONTACT
    fun updateContact(id: Int, name: String?, phone: String?, email: String?, image: Int): Long {
        return myDB.updateContact(Contact(id, name!!, phone!!, email!!, image)).toLong()
    }

    // DELETE CONTACT BY ID
    fun deleteContactByID(id: Int): Int {
        return myDB.deleteContact(id)
    }

    // CONTACT COUNT
    fun getContactCount(): Int {
        return myDB.getContactsCount()
    }
}