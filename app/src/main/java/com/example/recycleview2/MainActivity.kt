package com.example.recycleview2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var lista:RecyclerView? = null
    var adaptador:AdaptadorCustom? = null
    var layoutManager:RecyclerView.LayoutManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val platillos = ArrayList<Platillo>()


        platillos.add(Platillo("Platillo 1", 250.0, 3.0F, R.drawable.im1  ))
        platillos.add(Platillo("Platillo 2", 200.0, 4.0F, R.drawable.im2  ))
        platillos.add(Platillo("Platillo 3", 150.0, 5.0F,R.drawable.im3  ))
        platillos.add(Platillo("Platillo 4", 50.0, 2.5F, R.drawable.im4  ))


        lista = findViewById(R.id.lista)
        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        adaptador = AdaptadorCustom(platillos, object:ClickListener{
            override fun onClick(vista: View, index: Int) {
               Toast.makeText(applicationContext, platillos.get(index).nombre,  Toast.LENGTH_SHORT).show()
            }

        })
       lista?.adapter = adaptador

        val swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeToRefresh.setOnRefreshListener {
           for(i in 1..300000000){
           }
            swipeToRefresh.isRefreshing=false
            platillos.add(Platillo("Platillo 5", 750.0, 1.5F,R.drawable.im5  ))
            platillos.add(Platillo("Platillo 6", 250.0, 3.0F, R.drawable.im6  ))
            platillos.add(Platillo("Platillo 7", 200.0, 4.0F, R.drawable.im7  ))
            platillos.add(Platillo("Platillo 8", 150.0, 5.0F,R.drawable.im8  ))
            platillos.add(Platillo("Platillo 9", 50.0, 2.5F, R.drawable.im9  ))
            platillos.add(Platillo("Platillo 10", 750.0, 1.5F,R.drawable.im10  ))
            adaptador?.notifyDataSetChanged()
        }
    }
}
