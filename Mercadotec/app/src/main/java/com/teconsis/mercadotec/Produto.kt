package com.teconsis.mercadotec

import java.util.*

class Produto(val codigoBarras: String, var nome: String) {
    override fun toString(): String {
        return "$codigoBarras - $nome"
    }
}
