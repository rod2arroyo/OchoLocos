package com.example.ocholocoprueba

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random
import android.widget.LinearLayout.LayoutParams;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val btnRobar = findViewById<Button>(R.id.robarCartas)
        //val btnPasar = findViewById<Button>(R.id.terminarTurno)
        val btnImprimir = findViewById<Button>(R.id.imprimirCartas)
        val customLayout = findViewById<CartasJugador>(R.id.cartasTotales)
        val cartaMesa = findViewById<ImageView>(R.id.cartaMesa)
        val cartaJugada = findViewById<ImageView>(R.id.cartaJugada)



        var cards = arrayOf(R.drawable.id00,
            R.drawable.id01, R.drawable.id02, R.drawable.id03,
            R.drawable.id04,R.drawable.id05,R.drawable.id06,
            R.drawable.id07,R.drawable.id08,R.drawable.id09,
            R.drawable.id10,R.drawable.id11,R.drawable.id12,
            R.drawable.id13,R.drawable.id14,R.drawable.id15,
            R.drawable.id16,R.drawable.id17,R.drawable.id18,
            R.drawable.id19,R.drawable.id20,R.drawable.id21,
            R.drawable.id22,R.drawable.id23,R.drawable.id24,
            R.drawable.id25,R.drawable.id26,R.drawable.id27,
            R.drawable.id28,R.drawable.id29,R.drawable.id30,
            R.drawable.id31,R.drawable.id32,R.drawable.id33,
            R.drawable.id34,R.drawable.id35,R.drawable.id36,
            R.drawable.id37,R.drawable.id38,R.drawable.id39,
            R.drawable.id40,R.drawable.id41,R.drawable.id42,
            R.drawable.id43,R.drawable.id44,R.drawable.id45,
            R.drawable.id46,R.drawable.id47,R.drawable.id48,
            R.drawable.id49,R.drawable.id50,R.drawable.id51,
            R.drawable.joker1,R.drawable.joker2
        )

        /*btnRobar.setOnClickListener {
            Toast.makeText(this,cmesaA?.number,Toast.LENGTH_SHORT).show()
        }

        btnPasar.setOnClickListener {
            Toast.makeText(this,"Clickeaste el boton de pasar",Toast.LENGTH_SHORT).show()
        }*/

        btnImprimir.setOnClickListener {
            //imprimir()
            customLayout.removeAllViews();
        }

        crearCartas()
        repartir()
        imprimir()
        jugada(0,jugador1.mano[3],cmesaA)

        cartaMesa.setImageResource(cards[cmesaA!!.id])

        var listaXJugar = ArrayList<ImageView>()

        //imagenA.requestLayout()
        //imagenA.layoutParams.height    = 50
        //imagenA.layoutParams.width = 50
        //println(jugador1.mano.size)

        var params: LayoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10,10,10,10)
        params.weight = 1F


        for(i in 0..(jugador1.mano.size-1)){
            var imagenA = ImageView(this)
            imagenA.setImageResource(cards[jugador1.mano[i].id])
            //imagenA.setPadding(20,20,20,20)
            imagenA.id = jugador1.mano[i].id
            listaXJugar.add(imagenA)
            imagenA.layoutParams = params
            customLayout.addView(imagenA)
        }

        /*for(i in 0..(jugador2.mano.size-1)){
            val imagenA = ImageView(this)
            imagenA.setImageResource(cards[jugador2.mano[i].id])
            listaXJugar.add(imagenA)
            customLayout.addView(imagenA)
        }

        for(i in 0..(jugador3.mano.size-1)){
            val imagenA = ImageView(this)
            imagenA.setImageResource(cards[jugador3.mano[i].id])
            listaXJugar.add(imagenA)
            customLayout.addView(imagenA)
        }*/

        for(i in 0..(listaXJugar.size-1)){
            listaXJugar[i].setOnClickListener{
                cartaJugada.setImageResource(cards[listaXJugar[i].id])
                Toast.makeText(this," " + list[listaXJugar[i].id].number + " " +list[listaXJugar[i].id].palo ,Toast.LENGTH_SHORT).show()
            }
        }


        /*val imagen = ImageView(this)
        imagen.setImageResource(cards[jugador1.mano[0].id])
        customLayout.addView(imagen)

        val imagen2 = ImageView(this)
        imagen2.setImageResource(cards[1])
        customLayout.addView(imagen2)

        val imagen3 = ImageView(this)
        imagen3.setImageResource(cards[2])
        customLayout.addView(imagen3)*/
    }
}

class Carta(var number:String, var palo:String,var estado:Int, var id: Int){}

class Jugador(var nombre:String,var cantidadCartas:Int, var orden:Int,var mano: ArrayList<Carta>){}

var jugador1  = Jugador("jugaor1",0,1,ArrayList<Carta>())
var jugador2  = Jugador("jugaor2",0,2,ArrayList<Carta>())
var jugador3  = Jugador("jugaor3",0,3,ArrayList<Carta>())


//LISTA DE JUGADORES
var listjugadores = listOf<Jugador>(jugador1, jugador2, jugador3)
// LISTA DE CARTAS (BARAJA)
var list = ArrayList<Carta>()

fun crearCartas(){
    list = ArrayList<Carta>()
    val palos = listOf("Picas","Diamantes","Treboles","Corazones")
    val numeros = listOf("A","2","3","4","5","6","7","8","9","10","J","Q","K")

    for(i in 0..3){
        for(j in 0..12){
            var carta = Carta(numeros[j],palos[i],1,0)
            list.add(carta)
            //println(numeros[j] + " carta" + palos[i])
        }
    }

    for(i in 0..51){
        list[i].id = i
    }
}
var cmesaA : Carta? = null

fun repartir(){

    repartirmesa()
    for(i in 0..2){
        for(j in 0..7){
            //añadirla carta
            agarrarCarta(listjugadores[i],1)
        }
        //println(randomValue)
    }
}

fun agarrarCarta(jugador : Jugador, n :Int ){
    var random = Random.nextInt(0,51)
    if(list[random].estado==1){
        jugador.mano.add(list[random])
        list[random].estado=0
    }else{
        agarrarCarta(jugador,n)
    }
}

fun jugada(orden : Int ,carta :Carta,cmesa : Carta? ){
    var posi : Int = 0
    var cant : Int = 0

    if(carta.number == cmesa?.number || carta.palo == cmesa?.palo){
        println("Coincidio la carta con la mesa")
        for (i in 0..((listjugadores[orden].mano.size)-1)){
            if(listjugadores[orden].mano[i].number==carta.number && listjugadores[orden].mano[i].palo==carta.palo){
                posi=i
                cant = cant + 1
            }
        }
        cmesaA=listjugadores[orden].mano[posi]
        listjugadores[orden].mano.remove(listjugadores[orden].mano[posi])
    }else{
        println("Carta no valida")
    }
}

fun repartirmesa(){
    var ran = Random.nextInt(0,51)
    if(list[ran].estado==1){
        cmesaA = list[ran]
        list[ran].estado = 0
    }else{
        repartirmesa()
    }
}

fun imprimir(){
    for(i in 0..2){
        println("JUGADOR NUMERO " + i + " ")
        for(j in 0..((listjugadores[i].mano.size)-1)){
            println(listjugadores[i].mano[j].number + " " + listjugadores[i].mano[j].palo+ " " + listjugadores[i].mano[j].id)
        }
        println("")
    }
    println("Mesa: " + cmesaA?.number + " " + cmesaA?.palo)

}