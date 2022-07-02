package ir.almasapps.kotlindatabasesqlitecrud.Database.Controller

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ir.almasapps.kotlindatabasesqlitecrud.Database.Model.Contact

class MyDB (context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION){
    companion object{
        const val DB_NAME = "db_contacts"
        const val TABLE_NAME = "tbl_contact"
        const val DB_VERSION = 1
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
        const val KEY_PHONE = "phone"
        const val KEY_Email = "email"
        const val KEY_IMAGE = "image"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ('$KEY_ID' INTEGER PRIMARY KEY ," +
                " '$KEY_NAME' TEXT , '$KEY_PHONE' TEXT, '$KEY_Email' TEXT, '$KEY_IMAGE' INTEGER)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS '$TABLE_NAME' "
        db!!.execSQL(query)
    }

    // INSERT ITEM
    fun insertContacrt (contact: Contact) : Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME,contact.name)
        values.put(KEY_PHONE,contact.phone)
        values.put(KEY_Email,contact.email)
        values.put(KEY_IMAGE,contact.image)

        val result = db.insert(TABLE_NAME,null,values)
        db.close()
        return result
    }

    // GET ALL ITEMS
    @SuppressLint("Range", "Recycle")
    fun getAllContacts():List<Contact>{
        val list:ArrayList<Contact> = ArrayList()
        val query = "SELECT * FROM '$TABLE_NAME'"
        val db = this.readableDatabase

        val cursor : Cursor? = db.rawQuery(query,null)

        if (cursor != null) {
            if(cursor.moveToFirst()){
                do{
                    val tmp = Contact(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("phone")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getInt(cursor.getColumnIndex("image")))


                    list.add(tmp)


                }while (cursor.moveToNext())
            }
        }
        return list
    }


    // GET ITEM BY ID
    @SuppressLint("Range", "Recycle")
    fun getContactByID(id:Int):Contact{

        val query = "SELECT * FROM '$TABLE_NAME' WHERE id = $id"
        val db = this.readableDatabase
        val cursor : Cursor? = db.rawQuery(query,null)

        lateinit var contact :Contact

        if (cursor != null) {
            if(cursor.moveToFirst()){
                contact = Contact(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    cursor.getInt(cursor.getColumnIndex("image")))
            }
        }
        return contact
    }


    //UPDATE ITEM
    fun updateContact(contact: Contact):Int{
        val db = writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME , contact.name)
        values.put(KEY_PHONE , contact.phone)
        values.put(KEY_Email , contact.email)
        values.put(KEY_IMAGE , contact.image)

        val result = db.update(TABLE_NAME,values, "$KEY_ID = ${contact.id}" , null)
        db.close()
        return result
    }

    // DELETE ITEM
    fun deleteContact(id:Int):Int{
        val db = writableDatabase
        val result = db.delete(TABLE_NAME,"$KEY_ID = $id",null)
        db.close()
        return result
    }


    // TABLE COUNT
    @SuppressLint("Recycle")
    fun getContactsCount(): Int {
        var result = 0
        val db = this.readableDatabase
        val strSQL = "select * from $TABLE_NAME"
        val cursor = db.rawQuery(strSQL, null)
        if (cursor != null) {
            result = cursor.count
        }
        cursor!!.close()
        db.close()
        return result
    }
}