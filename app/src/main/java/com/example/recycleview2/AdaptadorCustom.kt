package com.example.recycleview2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import java.util.ArrayList

class AdaptadorCustom( items:ArrayList<Platillo>, var listener: ClickListener, var  longClickListener: LongClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {
    var items: ArrayList<Platillo>?= null
    var multiSeleccion= false

    init {
        this.items=items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_platillo,parent,false)
        val viewHolder = ViewHolder(vista,listener,longClickListener)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.foto?.setImageResource(item?.foto!!)
        holder.nombre?.text = item?.nombre
        holder.precio?.text = "$" + item?.precio.toString()
        holder.rating?.rating = item?.rating!!
    }

    fun iniciarActionMode(){
        multiSeleccion = true
    }
    fun destruirActionMode(){
        multiSeleccion = false
        notifyDataSetChanged()
    }
    fun terminarAccionMode(){
        multiSeleccion = false

    }


    class ViewHolder(vista:View, listener: ClickListener, longClickListener: LongClickListener): RecyclerView.ViewHolder(vista),View.OnClickListener, View.OnLongClickListener{



        var vista = vista
        var foto:ImageView? = null
        var nombre:TextView?= null
        var precio:TextView?= null
        var rating:RatingBar?= null
        var listener:ClickListener? = null
        var longListener:LongClickListener? = null

        init {
            foto = vista.findViewById(R.id.ivfoto)
            nombre = vista.findViewById(R.id.tvNombre)
            precio = vista.findViewById(R.id.tvPrecio)
            rating = vista.findViewById(R.id.tvRating)

            this.listener = listener
            this.longListener = longClickListener

            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClilck(v!!,adapterPosition)
            return true
        }
    }
}