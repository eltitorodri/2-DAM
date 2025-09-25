public class EjerciciosKotlin {

    var pi = 3.14
    val numero = 2

    fun ejercicio1() {

        println("El numero pi es inmutable: $pi")
        println("El numero es mutable: $numero")

        println("Suma: ${numero + pi}")
        println("Resta: ${numero - pi}")
        println("Multiplicacion: ${numero * pi}")
        println("Division: ${numero / pi}")

    }

    fun ejercicio2() {

        val numeroDouble = 3.14

        val numeroInt = numeroDouble.toInt()

        println("Numero double pasado a Int: $numeroInt")

    }

    fun ejercicio3() {

        val numeroGrande = 1_000_000

        println("Numero grande a Double: $numeroGrande")

    }

    fun ejercicio4() {

        val nombre = "Rodrigo"
        val edad:Int = 19

        println("Mi nombre es $nombre, ademas de que mi edad es $edad")

    }

    fun ejercicio5() {

        print("Introduce un número: ")
        val input = readLine()
        val numero = input?.toInt()

        if (numero == null) {
            println("No has introducido nada")
        } else if (numero < 0) {
            println("El numero es negativo")
        } else if (numero > 0) {
            println("El numero es positivo")
        } else {
            println("El numero es 0")
        }
    }

    fun ejercicio5() {

        print("Introduce un número: ")
        val input = readLine()
        val numero = input?.toInt()

        if (numero == null) {
            println("No has introducido nada")
        } else if (numero < 0) {
            println("El numero es negativo")
        } else if (numero > 0) {
            println("El numero es positivo")
        } else {
            println("El numero es 0")
        }
    }

}

fun main() {

    val ejercicio = EjerciciosKotlin()

    println("-----------")
    println("Ejercicio 1")
    println("-----------")

    ejercicio.ejercicio1()

    println("-----------")
    println("Ejercicio 2")
    println("-----------")

    ejercicio.ejercicio2()

    println("-----------")
    println("Ejercicio 3")
    println("-----------")

    ejercicio.ejercicio3()

    println("-----------")
    println("Ejercicio 4")
    println("-----------")

    ejercicio.ejercicio4()

    println("-----------")
    println("Ejercicio 5")
    println("-----------")

    ejercicio.ejercicio5()

}