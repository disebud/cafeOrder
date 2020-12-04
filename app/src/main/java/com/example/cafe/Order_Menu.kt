package com.example.cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cafe.activity.MainActivity
import com.example.cafe.model.Order_Menu_Model
import kotlinx.android.synthetic.main.activity_order__menu.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Order_Menu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order__menu)

        val tglSystem = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

        txtTgl.text = tglSystem.toString()
        // txtTgl.setText(tglSystem)
        val jamSystem = LocalDateTime.now()
        val formatted = jamSystem.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        txtJam.setText(formatted)

        val intent = intent
        val receivedName = intent.getStringExtra("nama_menu")
        val receivedDesc = intent.getStringExtra("desc_menu")
        val receivedHarga = intent.getStringExtra("harga_menu")
        var accImg = intent.getStringExtra("img_menu")

        if(accImg != null){
            imageView.setImageResource(accImg.toInt())
        }

       // imageView

        txtNamaMenu.text = receivedName
        txtRHarga.text = receivedHarga
        txtDetail.text = receivedDesc

        btnSaveOrder.setOnClickListener {
            if (edtNoBangku.text.isEmpty()) {
                Toast.makeText(
                    this, "Enter No Bangku Order",
                    Toast.LENGTH_SHORT
                ).show()
                edtNoBangku.requestFocus()
            } else {
                val Ord_Model = Order_Menu_Model()
                Ord_Model.str_Tgl = txtTgl.text.toString()
                Ord_Model.str_Jam = txtJam.text.toString()
                Ord_Model.str_nmMenu = txtNamaMenu.text.toString()
                Ord_Model.str_noBangku = edtNoBangku.text.toString().toInt()
                Ord_Model.str_hrgMenu = txtRHarga.text.toString().toInt()

                MainActivity.dbHandler.addOrder(this, Ord_Model)
            }
        }

        btnOrderAgain.setOnClickListener {
            val moveHome = Intent(this,MainActivity::class.java)
            startActivity(moveHome)
        }
    }
}
