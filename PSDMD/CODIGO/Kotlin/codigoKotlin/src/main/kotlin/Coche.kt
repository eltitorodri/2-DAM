package org.example

class Coche (private val marca: String,private val  modelo: String, private val año: Int) {

    override fun toString(): String {
        return "Mi coche es el modelo $modelo\nde la marca$marca\ny es del año $año "
    }
}