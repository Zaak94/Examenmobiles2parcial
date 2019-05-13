package com.example.recycleview2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import java.util.ArrayList
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    var lista:RecyclerView? = null
    var adaptador:AdaptadorCustom? = null
    var layoutManager:RecyclerView.LayoutManager? = null

    var isActionMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val platillos = ArrayList<Platillo>()

        platillos.add(Platillo("Imagen 1", 250.0, 3.0F, R.drawable.im1  ))
        platillos.add(Platillo("Imagen 2", 200.0, 4.0F, R.drawable.im2  ))
        platillos.add(Platillo("Imagen 3", 150.0, 5.0F,R.drawable.im3  ))
        platillos.add(Platillo("Imagen 4", 50.0, 2.5F, R.drawable.im4  ))

        lista = findViewById(R.id.lista)
        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        val call = object : ActionMode.Callback{
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                adaptador?.terminarAccionMode()
                mode?.finish()
                isActionMode = false
                return true
            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                // inicializa action mode
                adaptador?.iniciarActionMode()
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                adaptador?.destruirActionMode()
                isActionMode = false
            }

        }

        adaptador = AdaptadorCustom(platillos, object:ClickListener{
            override fun onClick(vista: View, index: Int) {
               Toast.makeText(applicationContext, "Se presiono la "+platillos.get(index).nombre,  Toast.LENGTH_SHORT).show()
            }

        }, object: LongClickListener{
            override fun longClilck(vista: View, index: Int) {
               // Toast.makeText(applicationContext, "Se dejo presionado la "+platillos.get(index).nombre,  Toast.LENGTH_SHORT).show()
                if(!isActionMode){
                    startActionMode(call)
                    isActionMode=true
                    adaptador?.seleccionarItem(index)
                }else{
                    //hacer sellecciones
                    adaptador?.seleccionarItem(index)
                }
            }

        })
       lista?.adapter = adaptador





        val swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeToRefresh.setOnRefreshListener {
           for(i in 1..300000000){
           }
            swipeToRefresh.isRefreshing=false
            platillos.add(Platillo("Imagen 5", 750.0, 1.5F,R.drawable.im5  ))
            platillos.add(Platillo("Imagen 6", 250.0, 3.0F, R.drawable.im6  ))
            platillos.add(Platillo("Imagen 7", 200.0, 4.0F, R.drawable.im7  ))
            platillos.add(Platillo("Imagen 8", 150.0, 5.0F,R.drawable.im8  ))
            platillos.add(Platillo("Imagen 9", 50.0, 2.5F, R.drawable.im9  ))
            platillos.add(Platillo("Imagen 10", 750.0, 1.5F,R.drawable.im10  ))
            adaptador?.notifyDataSetChanged()
        }
    }
}
