package org.example

import java.util.Arrays

//EJERCICIO 1
fun variables_numericos() {

    val nombre: String = "Jose" //val es para algo inmutable
    var apellido: String = "Fuentes" //es para algo mutable (se le puede cambiar el valor)

    val numero1: Int = 2
    val numero2: Int = 3

    var resultadoSuma = numero1 + numero2
    println(resultadoSuma)
    println("El resultado de la resta: ${numero1 - numero2}")
    var resultadoMultiplicacion = numero1 * numero2
    println(resultadoMultiplicacion)
    println("El resultado de la division: ${numero1 / numero2}")
}

//EJERCICIO 2
fun conversion_tipos () {

    var variableDouble: Double = 2.334

    var deDoubleaInteger = variableDouble.toInt()

    println("Este era el numero en double: $variableDouble ")
    println("Este es el numero una vez pasado a int: $deDoubleaInteger")

}

//EJERCICIO 3
fun numeros_legibles () {

    var numeroGrande: Int = 1_000_000

    println("Este es el numero con guiones bajos: $numeroGrande")

}

//EJERCICIO 4
fun cadena_interpolacion() {

    var nombre:String = "Jose"
    var apellido:String = "Fuentes"

    println("Esta es la interpolacion: ${nombre} ${apellido}")

    val mensajeMultilinea = """
        Hola, me llamo $nombre $apellido.
        !Te saludo bb!
        """.trimIndent() //trimIndent() se usa para eliminar la sangria es decir para que no salga full a la derecha el texto
    println( mensajeMultilinea)

}

//EJERCICIO 5
fun condicionales_simples() {

    println("Escribe un número (positivo/negativo/cero)")
    val numeroUsuario = readLine()?.toIntOrNull()

    if (numeroUsuario == null) {
        println("No ingresaste un numerito valido.")
        return
    }

    if (numeroUsuario == 0) {
        println("El numero del usuario es cero.")
    } else if (numeroUsuario > 0) {
        println("El numero $numeroUsuario es positivo.")
    } else (
        println("El numero $numeroUsuario es negativo.")
    )

}

//EJERCICIO 6
fun condicional_when () {
    println("Escribe un numero del 1-7")
    val numeroUsuario = readLine()?.toIntOrNull()
    if (numeroUsuario == null) {
        println("Te dije que solo valian numeros del 1-7")
        return
    } else if (numeroUsuario > 7 || numeroUsuario < 1) {
        println("El numero ha de ser entre 1-7 bro te lo he dicho antes.")
        return
    } else {
        when (numeroUsuario) {
            1 -> println("Lunes")
            2 -> println("Martes")
            3 -> println("Miercoles")
            4 -> println("Jueves")
            5 -> println("Viernes")
            6 -> println("Sábado")
            7 -> println("Domingo")
        }
    }
}

//EJERCICIO 7
fun nubalidad () {

    var telefono:String? = null //Declaro una variable que tiene la posibilidad de ser nula

    val longitud = telefono?.length ?: "El telefono es nulo" //Si tiene valor, obtiene su longitud sino, mostrara el texto

    println("La longitud es: $longitud")

}

//EJERCICIO 8
fun listas() {

    var listaInmutable: List<String> = listOf("amarillo", "azul", "rosa", "rojo", "morado") //Lista inmutable

    var listaMutable: MutableList<String> = mutableListOf("uno", "dos", "tres") //LISTA MUTABLE

    println("========================")
    println("Recorrer lista inmutable")
    println("========================")
    for (x in listaInmutable) {
        println("- $x")
    }

    println("========================")
    println("Recorrer lista mutable 4")
    println("========================")

    listaMutable.add("cuatro")
    for (y in listaMutable) {
        println("- $y")
    }
    listaMutable.remove("cuatro")

    println("========================")
    println("Recorrer lista mutable 3")
    println("========================")

    for (y in listaMutable) {
        println("- $y")
    }
}

//EJERCICIO 9
fun arrays() {

    var arrayEnteros: Array<Int> = arrayOf(1,2,3,4,5)
    var arrayEnterosIntOf: IntArray = intArrayOf(1,2,3,4,5)

    val arrayCombinado: Array<Int> = arrayEnteros + arrayEnterosIntOf.toTypedArray() //convierte un IntArray o cualquiera en un
                                                                                    // Array generico, para poder operar

    println(Arrays.toString(arrayCombinado)) //mostramos el array combinado con Arrays.toString()

}

//EJERCICIO 10
fun bucles_rangos () {

    for (i in 1..10) {
        print(" - $i ")
    }

    println()

    for (i in 10 downTo 1) {
        print(" - $i")
    }

    println()

    for (i in 2..20 step 2) {
        print(" - $i")
    }

}



//EJERCICIO 11
fun do_while() {

    println()

    var numero = 0
    var numero2= 5

    while (numero <= 5) {
        print("|  $numero")
        numero++
    }

    println()

    do {
        print("|  $numero2")
        numero2--
    } while (numero2 >= 0)

}

//EJERCICIO 12
fun suma () {

    println()

    println("Ingresa el primer numero: ")
    val primerInput = readLine()?.toIntOrNull() ?: 0

    println("Ingresa el segundo numero: ")
    val segundoInput = readLine()?.toIntOrNull() ?: 0

    println("La suma de ambos numeros es ${primerInput + segundoInput}.")

}

//EJERCICIO 13
fun saludarConArgs(nombre: String = "invitado") {

    println(nombre)

}

fun saludarSinArgs () {

    val nombre: String = "invitado"
    println(nombre)

}

//EJERCICIO 14
fun funcion_compacto(temperatura: Double): Boolean = temperatura > 25

//EJERCICIO 15
fun filtros_listas() {

    val listaNumeros: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)

    val pares = listaNumeros.filter { it % 2 == 0 }
    println("Numeros pares son: $pares")

    val multiplicados = listaNumeros.map{ it * 2 }
    println("Multiplicados son: $multiplicados")

}

//EJERCICIO 15
fun lambda() {

    println("Ingresa un numero: ")
    val input = readLine()?.toIntOrNull() ?: 0

    val cuadrado: (Int) -> Int = { it * it }

    println("El cuadrado de $input es ${cuadrado(input)}")

}

//EJERCICIO 16
fun orden_superior(lista: List<Int>, funcion: (Int) -> Int) {
    val resultado = lista.map(funcion)
    println("Resultado: $resultado")
}

fun main() {

    variables_numericos()
    conversion_tipos()
    numeros_legibles()
    cadena_interpolacion()
    condicionales_simples()
    condicional_when ()
    nubalidad()
    listas()
    arrays()
    bucles_rangos()
    do_while()
    suma()
    saludarConArgs()
    saludarSinArgs()
    funcion_compacto(22.3)
    filtros_listas()
    lambda()
    val numeros = listOf(1, 2, 3, 4, 5)
    val duplicar: (Int) -> Int = { it * 2 }
    orden_superior(numeros, duplicar)

}