
fun variables_numericos() {

    val nombre: String = "Jose" //val es para algo inmutable
    var apellido: String = "Fuentes" //es para algo mutable (se le puede cambiar el valor)

    val numero1: Int = 2
    val numero2: Int = 3

    var resultadoSuma = numero1 + numero2
    println(resultadoSuma)
    println("El resultado de la resta: $numero1 - $numero2 ")
    var resultadoMultiplicacion = numero1 * numero2
    println(resultadoMultiplicacion)
    println("El resultado de la division: $numero1 / $numero2")
}

fun main() {

    variables_numericos()

}
