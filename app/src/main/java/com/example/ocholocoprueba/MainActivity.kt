package com.example.ocholocoprueba

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnRobar = findViewById<Button>(R.id.robarCartas)
        val btnPasar = findViewById<Button>(R.id.terminarTurno)
        val btnImprimir = findViewById<Button>(R.id.imprimirCartas)

        btnRobar.setOnClickListener {
            Toast.makeText(this,cmesaA?.number,Toast.LENGTH_SHORT).show()
        }

        btnPasar.setOnClickListener {
            Toast.makeText(this,"Clickeaste el boton de pasar",Toast.LENGTH_SHORT).show()
        }
        btnImprimir.setOnClickListener {
            imprimir()
        }


        crearCartas()
        repartir()
        imprimir()
        jugada(0,jugador1.mano[3],cmesaA)
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
    val palos = listOf("Picas","Diamantes","Corazones","Treboles")
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