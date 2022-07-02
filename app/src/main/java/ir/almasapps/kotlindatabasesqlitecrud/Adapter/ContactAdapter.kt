package ir.almasapps.kotlindatabasesqlitecrud.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import ir.almasapps.kotlindatabasesqlitecrud.Database.Model.Contact
import ir.almasapps.kotlindatabasesqlitecrud.R
import ir.almasapps.kotlindatabasesqlitecrud.UpdateContactActivity

class ContactAdapter(private var context: Context, var list : List<Contact>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item,null)
        val txtName : TextView = view.findViewById(R.id.list_item_txtName)
        val txtPhone : TextView = view.findViewById(R.id.list_item_txtPhone)
        val txtEmail : TextView = view.findViewById(R.id.list_item_txtEmail)
        val img : ImageView = view.findViewById(R.id.list_item_img)

        txtName.text = list[position].name
        txtPhone.text = list[position].phone
        txtEmail.text = list[position].email

        img.setImageResource(list[position].image)

        view.setOnClickListener {
            val intent = Intent(context, UpdateContactActivity::class.java)
            intent.putExtra("contactID", list[position].id)
            context.startActivity(intent)
        }


        return view
    }
}