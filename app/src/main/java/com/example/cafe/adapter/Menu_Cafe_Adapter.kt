package com.example.cafe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.cafe.model.Menu_Cafe_Model
import com.example.cafe.R

// Komponen View yang digunakan ListView
class Menu_Cafe_Adapter(context: Context, DaftarMenu: ArrayList<Menu_Cafe_Model>) : BaseAdapter() {

    private val DaftarMenu: ArrayList<Menu_Cafe_Model>
    //konversi xml (user interface) menjadi objek
    private val mInflater: LayoutInflater

    init {
        this.mInflater = LayoutInflater.from(context)
        this.DaftarMenu = DaftarMenu // pemberian seluruh nilai namamakanan,deskripsi,harga dan photo ke property bernama DaftarMenu
    }

    //calculate file DaftarMenu yang berasal dari Menu_Cafe_Model
    override fun getCount(): Int {
        return DaftarMenu.size
    }


    //mendapatkan value yang ada di Menu_Cafe_Model seperti nama,harga,desk dan photo
    override fun getItem(position: Int): Any {
        return DaftarMenu.get(position)
    }

    //mendapatkan value berdasarkan Id dari masing masing posisi yang bertipe bilangan bulat
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    //menampilkan data ke view
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ListDaftarMenu
        //cek kondisi xml yg telah di convert view kedalam objek
        if (convertView == null){
            view = this.mInflater.inflate(R.layout.list_item_menu,parent,false)//pemberian layout kepada objek layout
            viewHolder =
                ListDaftarMenu(view) // pemberian nilai ke ListDaftarMenu dengan objek xml dari layout list_item_menu dan value di tampung di viewHolder
            view.tag = viewHolder // jadi si objek bernama view sudah memiliki tampilan utuh yg masuk ke convertView alias converView tidak null lagi
        }
        else {
            view = convertView // seluruh isi xml alias views pada layout list_item_menu sudah dimiliki convertView dan memasuukkan ke objek bernama view
            viewHolder = view.tag as ListDaftarMenu
        }

        viewHolder.mnNama.text = DaftarMenu.get(position).str_nama
        viewHolder.mnDesk.text = DaftarMenu.get(position).str_des
        viewHolder.mnHarga.text = DaftarMenu.get(position).int_harga.toString()
        viewHolder.mnImage.setImageResource(DaftarMenu.get(position).int_image)
        return view
    }

}

private class ListDaftarMenu(row: View?) {
     val mnNama: TextView
     val mnDesk: TextView
     val mnHarga: TextView
     val mnImage: ImageView

    init {
        this.mnNama = row?.findViewById(R.id.txtNamaMenu) as TextView
        this.mnDesk = row?.findViewById(R.id.txtDeskripsi) as TextView
        this.mnHarga = row?.findViewById(R.id.txtRHarga) as TextView
        this.mnImage = row?.findViewById(R.id.imgMenu) as ImageView
    }

}