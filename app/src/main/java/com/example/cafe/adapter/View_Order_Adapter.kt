package com.example.cafe.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cafe.R
import com.example.cafe.activity.MainActivity
import com.example.cafe.model.Order_Menu_Model
import kotlinx.android.synthetic.main.list_item_view_order.view.*
import kotlinx.android.synthetic.main.list_item_view_order.view.txtRHarga

class View_Order_Adapter(
    mCtx: Context,
    val VwOrder: ArrayList<Order_Menu_Model>
) : RecyclerView.Adapter<View_Order_Adapter.ViewHolderI>() {

    val mCtx: Context = mCtx

    class ViewHolderI(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NMenu = itemView.txtNMenuR
        val Harga = itemView.txtRHarga
        val JmlOrder = itemView.txtRJmlOrder
        val TotHarga = itemView.txtRTotHarga
        val Hapus = itemView.imgDelete
    }

    //
    override fun getItemCount(): Int {
        return VwOrder.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderI {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_view_order, parent, false)
        return ViewHolderI(v)
    }

    override fun onBindViewHolder(p0: ViewHolderI, posisi: Int) {
        val Orderan = VwOrder[posisi]

        p0.NMenu.text = Orderan.str_nmMenu
        p0.Harga.text = Orderan.str_hrgMenu.toString()
        p0.JmlOrder.text = Orderan.str_noBangku.toString()
        p0.TotHarga.text = Orderan.str_totalharga.toString()
        p0.Hapus.setImageResource(R.drawable.ic_baseline_delete_24)

        p0.Hapus.setOnClickListener {
            val menunama = Orderan.str_nmMenu
            var alertDialog = AlertDialog.Builder(mCtx)
                .setTitle("Delete")
                .setMessage("Yakin dihapus data : $menunama ? ")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    if (MainActivity.dbHandler.deleteOrder(Orderan.str_id)) {
                        VwOrder.removeAt(posisi)
                        notifyItemRemoved(posisi)
                        notifyItemRangeChanged(posisi, VwOrder.size)
                        Toast.makeText(mCtx, "Barang $menunama dihapus", Toast.LENGTH_SHORT).show()
                    }else
                        Toast.makeText(mCtx,"Terjadi kesalahan penghapusan",
                        Toast.LENGTH_SHORT).show()

                })
                .setNegativeButton("No",DialogInterface.OnClickListener {
                        dialog, which ->  })
                .setIcon(R.drawable.ic_baseline_warning_24)
                .show()
        }
    }


}