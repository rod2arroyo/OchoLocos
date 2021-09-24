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
import android.app.Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnPasar = findViewById<Button>(R.id.terminarTurno)
        val btnImprimir = findViewById<Button>(R.id.imprimirCartas)
        val customLayout = findViewById<CartasJugador>(R.id.cartasTotales)
        val cartaMesa = findViewById<ImageView>(R.id.cartaMesa)
        val cartaJugada = findViewById<ImageView>(R.id.cartaJugada)
        val turnoActual = findViewById<TextView>(R.id.turnoActual)

        var params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(10,10,10,10)
        params.weight = 1F

        btnPasar.setOnClickListener{
            var cartactual : Carta = list[1]
            for(i in 0..51){
                if(list[i].id== cjugada){
                    cartactual=list[i]
                    println("entroooo y es carta actual:--->>" + cartactual.number + "   " + cartactual.palo  )
                }
            }
            var turnoantiguo = turno

            Toast.makeText(this,("Jugador  " + listjugadores[(turnoantiguo-1)].orden + " acaba de jugar" ),Toast.LENGTH_LONG).show()
            jugadaop(turno-1,cartactual,cmesaA,customLayout,listjugadores[(turno-1)],params,this,cartaMesa,cartaJugada,turnoActual)
            //siguienteturno(customLayout,listjugadores[(turno-1)],params,this,cartaMesa,cartaJugada)
            println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxjugador1: "+ listjugadores[0].mano.size+"jugador2: "+ listjugadores[1].mano.size+"jugador2: "+ listjugadores[2].mano.size)

            if (listjugadores[(turnoantiguo-1)].mano.size==1)
            {
                Toast.makeText(this,("Jugador " + listjugadores[(turnoantiguo-1)].orden + " va por una"),Toast.LENGTH_LONG).show()
            }

            if (listjugadores[(turnoantiguo-1)].mano.size==0)
            {
                Toast.makeText(this,("JUGADOR " + listjugadores[(turnoantiguo-1)].orden + " HA GANADO !!"),Toast.LENGTH_LONG).show()
            }
        }

        btnImprimir.setOnClickListener{
            pasarturno(customLayout,listjugadores[(turno-1)],params,this,cartaMesa,cartaJugada,turnoActual)

        }
        crearCartas()
        repartir()
        imprimir()
        imprimirmaso()
        cartaMesa.setImageResource(cards[cmesaA!!.id])

        imprimirCartas(jugador1,params,customLayout,this)
        darFuncionalidad(cartaJugada,this)
    }

}
// CLASES CARTA Y JUGADOR
class Carta(var number:String, var palo:String,var estado:Int, var id: Int){}
class Jugador(var nombre:String,var cantidadCartas:Int, var orden:Int,var mano: ArrayList<Carta>){}

// VARIABLES QUE SE UTILIZARAN
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
var jugador1  = Jugador("jugaor1",0,1,ArrayList<Carta>())
var jugador2  = Jugador("jugaor2",0,2,ArrayList<Carta>())
var jugador3  = Jugador("jugaor3",0,3,ArrayList<Carta>())
var listaXJugar = ArrayList<ImageView>()
var listjugadores = listOf<Jugador>(jugador1, jugador2, jugador3)
var list = ArrayList<Carta>()
var cmesaA : Carta? = null
var turno : Int = 1
var cartacastigo : Int =0

// Imprime las cartas del jugador que le toca el turno
fun imprimirCartas(jugador: Jugador,params:LayoutParams,customLayout:CartasJugador,activity : Activity){
    for (i in 0..((jugador.mano.size)-1)){
        var imagenA = ImageView(activity)
        imagenA.setImageResource(cards[jugador.mano[i].id])
        imagenA.id = jugador.mano[i].id
        listaXJugar.add(imagenA)
        imagenA.layoutParams = params
        customLayout.addView(imagenA)
    }
}
// Cambia de turno al siguiente Jugador y limpia toda la custom view
fun siguienteturno(customLayout:CartasJugador,jugador: Jugador,params:LayoutParams,activity : Activity,cartaMesa : ImageView, cartaJugada: ImageView,turnoActual:TextView){

    if(jugador.mano.size==1){
       // Toast.makeText(this,"Error de login",Toast.LENGTH_LONG).show()

    }
    customLayout.removeAllViews();

    turno+=1
    if(turno==4){
        turno=1
    }

    turnoActual.text = "Turno del Jugador: $turno"

    println("sigue----------xxxxxxxxxxxx===tunro----------------------------xxxxxxxxxxxxxxxx    " + turno)
    imprimirCartas(listjugadores[(turno-1)],params,customLayout,activity)
    cartaJugada.setImageResource(R.drawable.joker1)
    cartaMesa.setImageResource(cards[cmesaA!!.id])
    darFuncionalidad(cartaJugada,activity)
}
var cjugada : Int = 0
// Le da funcionalidad a las cartas del jugador actual
fun darFuncionalidad(cartaJugada: ImageView, activity:Activity){
    for(i in 0..(listaXJugar.size-1)){
        listaXJugar[i].setOnClickListener{
            cartaJugada.setImageResource(cards[listaXJugar[i].id])
            println("cjugada antesss:------>>" + cjugada )
            cjugada=listaXJugar[i].id
            println("mesaaaaaa--->>>>>-->>>"+cmesaA?.palo + "  " + cmesaA?.number)
            Int
        }
    }
}
// Se crean las cartas y se meten en la baraja
fun crearCartas(){
    list = ArrayList<Carta>()
    val palos = listOf("Picas","Diamantes","Treboles","Corazones")
    val numeros = listOf("A","2","3","4","5","6","7","8","9","10","J","Q","K")

    for(i in 0..3){
        for(j in 0..12){
            var carta = Carta(numeros[j],palos[i],1,0)
            list.add(carta)
        }
    }

    for(i in 0..51){
        list[i].id = i
    }
}
// Reparte a los 3 jugadores las cartas correspondientes, 8 para cada uno
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
// Roba una carta
fun agarrarCarta(jugador : Jugador, n :Int ){
    var random = Random.nextInt(0,51)
    if(list[random].estado==1){
        jugador.mano.add(list[random])
        list[random].estado=0
    }else{
        agarrarCarta(jugador,n)
    }
}
//pasar de turno
fun pasarturno(customLayout:CartasJugador,jugador: Jugador,params:LayoutParams,activity : Activity,cartaMesa : ImageView, cartaJugada: ImageView,turnoActual: TextView){
    agarrarCarta(jugador,1)
    siguienteturno(customLayout,listjugadores[(turno-1)],params,activity,cartaMesa,cartaJugada,turnoActual)
}
// Procede a realizar la jugada
fun jugada(orden : Int ,carta :Carta,cmesa : Carta? ){
    var posi = 0
    var cant = 0

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
// Le da la carta a la mesa
fun repartirmesa(){
    var ran = Random.nextInt(0,51)
    if(list[ran].estado==1){
        cmesaA = list[ran]
        list[ran].estado = 0
    }else{
        repartirmesa()
    }
}
// Funcion que ayuda a debug, no se utiliza.
fun imprimir(){
    for(i in 0..2){
        println("JUGADOR NUMERO $i ")
        for(j in 0 until (listjugadores[i].mano.size)){
            print(listjugadores[i].mano[j].number + " " + listjugadores[i].mano[j].palo+ " " + listjugadores[i].mano[j].id + "/ ")
        }
        println("")
    }
    println("Mesa: " + cmesaA?.number + " " + cmesaA?.palo)

}
// Imprimir el maso
fun imprimirmaso(){
    for(i in 0..2){
        println("JUGADOR NUMERO " + i + " ")
        for(j in 0..((listjugadores[i].mano.size)-1)){
            println(listjugadores[i].mano[j].number + " " + listjugadores[i].mano[j].palo+ " " + listjugadores[i].mano[j].id)
        }
        println("")
    }
    println("Mesa: " + cmesaA?.number + " " + cmesaA?.palo)

}
//logica op jugada
fun jugadaop(orden : Int, carta : Carta, cmesa : Carta? ,customLayout:CartasJugador,jugador: Jugador,params:LayoutParams,activity : Activity,cartaMesa : ImageView, cartaJugada: ImageView,turnoActual: TextView){
   // println("ordenn iniciall +++++++++++++>>>>" + orden)

    println("carta---------------------------------------------------------------->>>"+carta.number  + "--- " + carta.palo)
    println("cartamesa--------------------------------------------------------------->>>"+cmesa?.number+ "--- " + cmesa?.palo )
    var posi : Int = 0
    var cant : Int = 0
    var letoca : Int = 1
    var tienek : Int =0
    var tienek2 : Int =0
    var tienek3 : Int =0
    var turnoporjugada : Int = turno

    if(carta.number=="K" && (carta.palo==cmesa?.palo || carta.number == cmesa?.number )){
        print("entro con k --------------------------------------------------")
        //if(cmesa?.number==carta.number || carta.palo==cmesa?.palo){
        //------------------------------------------------------------------------------------------------------------------------------

        cartacastigo=cartacastigo+3

        if(turnoporjugada+1==4){
            letoca=1
            println("-------------------------------------------->>>>>letocaa1--->>>>  " + letoca)
        }else{
            letoca = turnoporjugada+1
            println("-------------------------------------------->>>>>letocaa1--->>>>  " + letoca)
        }

        for (i in 0..((listjugadores[letoca-1].mano.size)-1)){
            if(listjugadores[letoca-1].mano[i].number=="K"){
                tienek = 1
                println("el siguiente jugador 2 tiene k")
            }
        }
        if(tienek==1){
            cartacastigo=cartacastigo+3
            println("ya entro el 2")
            if(letoca+1==4){
                letoca=1
                println("-------------------------------------------->>>>>letocaa2--->>>>  " + letoca)
            }else{
                letoca = letoca+1
                println("-------------------------------------------->>>>>letocaa2--->>>>  " + letoca)
            }

            for (i in 0..((listjugadores[letoca-1].mano.size)-1)){
                if(listjugadores[letoca-1].mano[i].number=="K"){
                    tienek2 = 1
                    println("el siguiente jugador 3 tiene k")
                }
            }
            if(tienek2==1){
                if(letoca+1==4){
                    letoca=1
                    println("-------------------------------------------->>>>>letocaa3--->>>>  " + letoca)
                }else{
                    letoca = letoca+1
                    println("-------------------------------------------->>>>>letocaa3--->>>>" + letoca)
                }

                for (i in 0..((listjugadores[letoca-1].mano.size)-1)){
                    if(listjugadores[letoca-1].mano[i].number=="K"){
                        tienek3 = 1
                        println("el siguiente jugador 1 tiene k otra vez")


                    }
                }

                if (tienek3==1){


                    if(letoca+1==4){
                        letoca=1
                        println("-------------------------------------------->>>>>letocaa4--->>>>  " + letoca)
                    }else{
                        letoca = letoca+1
                        println("-------------------------------------------->>>>>letocaa4--->>>>  " + letoca)
                    }
                    println("+++++++++++letoca++++" + letoca+"    " +12)
                    for(j in 0..11){
                        //añadirla carta
                        agarrarCarta(listjugadores[letoca-1],1)
                    }
                }else{
                    println("+++++++++++letoca++++" + letoca+"    " +9)
                    for(j in 0..8){
                        //añadirla carta
                        agarrarCarta(listjugadores[letoca-1],1)
                    }
                }

            }else{
                println("+++++++++++letoca++++" + letoca+"    " +6)
                for(j in 0..5){
                    //añadirla carta
                    agarrarCarta(listjugadores[letoca-1],1)
                }
            }
        }else{
            println("+++++++++++letoca++++" + letoca +"    " +3)
            for(j in 0..2){
                //añadirla carta
                agarrarCarta(listjugadores[letoca-1],1)
                println("solo doy 3 cartas")
            }
        }
        //------------------------------------------------------------------------------------------------------------------------------



        println("entro con EL NUMERO O PALO  Y SE SUMO -------------------------------------------------->" + cartacastigo)

        println("Coincidio la carta con la mesa")
        //desde aqui
        for (i in 0..((listjugadores[orden].mano.size)-1)){
            if(listjugadores[orden].mano[i].number==carta.number && listjugadores[orden].mano[i].palo==carta.palo){

                posi=i

            }
            if(listjugadores[orden].mano[i].number==carta.number ){
                cant = cant + 1
                println("=jugador=" +listjugadores[orden].mano[i].number +"=carta="+carta.number +"=cantidad="+cant )

            }
        }
        println("cantiad----------->" + cant)
        if (cant==1){
            cmesaA=listjugadores[orden].mano[posi]
            listjugadores[orden].mano.remove(listjugadores[orden].mano[posi])
            siguienteturno(customLayout,listjugadores[(turno-1)],params,activity,cartaMesa,cartaJugada,turnoActual)

        }else{
            cmesaA=listjugadores[orden].mano[posi]
            listjugadores[orden].mano.remove(listjugadores[orden].mano[posi])

            customLayout.removeAllViews();
            imprimirCartas(listjugadores[(turno-1)],params,customLayout,activity)
            cartaJugada.setImageResource(R.drawable.joker1)
            cartaMesa.setImageResource(cards[cjugada])
            darFuncionalidad(cartaJugada,activity)

        }


        //hasta aqui lonomral luego +3


        //}
    }else{

        if(carta.number == cmesa?.number || carta.palo == cmesa?.palo){
            println("Coincidio la carta con la mesa")
            for (i in 0..((listjugadores[orden].mano.size)-1)){
                if(listjugadores[orden].mano[i].number==carta.number && listjugadores[orden].mano[i].palo==carta.palo){

                    posi=i

                }
              //  println("jugador: " + listjugadores[orden].orden)
               // println("=jugador compararr=" +listjugadores[orden].mano[i].number +"=carta="+carta.number +"=cantidad="+cant )
                if(listjugadores[orden].mano[i].number==carta.number ){
                    cant = cant + 1
                //    println("=jugadorxxxxxxx=" +listjugadores[orden].mano[i].number +"=carta="+carta.number +"=cantidad="+cant )

                }
            }
          //  println("cantiad----------->" + cant)
            if (cant==1){
                cmesaA=listjugadores[orden].mano[posi]
                listjugadores[orden].mano.remove(listjugadores[orden].mano[posi])
                //println("entrare  en sigueinte turno este ////")
                siguienteturno(customLayout,listjugadores[(turno-1)],params,activity,cartaMesa,cartaJugada,turnoActual)
                if (cmesaA!!.number=="J"){
                    siguienteturno(customLayout,listjugadores[(turno-1)],params,activity,cartaMesa,cartaJugada,turnoActual)
                }
            }else{
                cmesaA=listjugadores[orden].mano[posi]
                listjugadores[orden].mano.remove(listjugadores[orden].mano[posi])

                customLayout.removeAllViews();
                imprimirCartas(listjugadores[(turno-1)],params,customLayout,activity)
                cartaJugada.setImageResource(R.drawable.joker1)
                cartaMesa.setImageResource(cards[cjugada])

                darFuncionalidad(cartaJugada,activity)


            }


        }else{
            println("Carta no valida")
            println("carta----->>>"+carta.number  + "--- " + carta.palo)
            println("cartamesa----->>>"+cmesa?.number+ "--- " + cmesa?.palo )
//
//            println("jugadororden----->>>"+ listjugadores[turno-1].orden+ "--- "  )
//
//            for(i in  0..((listjugadores[orden].mano.size)-1) )
//            {
//               // println("carta----->>>"+i+ "--- " + listjugadores[orden].mano[i].number + " -----" +  listjugadores[orden].mano[i].palo  )
//            }
//
//            for(i in 0..2){
//                println("JUGADOR NUMERO " + i + "  --------------------")
//                for(j in 0..((listjugadores[i].mano.size)-1)){
//                    println(listjugadores[i].mano[j].number + " " + listjugadores[i].mano[j].palo+ " " + listjugadores[i].mano[j].id)
//                }
//                println("")
//            }
//            println("Mesa: " + cmesaA?.number + " " + cmesaA?.palo)



        }
    }


}




