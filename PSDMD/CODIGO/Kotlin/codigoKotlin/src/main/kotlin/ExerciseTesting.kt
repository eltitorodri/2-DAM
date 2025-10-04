package org.example

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
        println("El nunmero del usuario es cero.")
    } else if (numeroUsuario % 2 == 0) {
        println("El numero $numeroUsuario es par.")
    } else (
        println("El numero $numeroUsuario es impar")
    )

}

fun main() {

    variables_numericos()
    conversion_tipos()
    numeros_legibles()
    cadena_interpolacion()
    condicionales_simples()

}