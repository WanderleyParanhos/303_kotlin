package com.teconsis.mercadotec

class ListaCompras {
    private val produtos: MutableList<Produto> = mutableListOf()

    fun adicionarProduto(produto: Produto) {
        produtos.add(produto)
    }

    fun removerProduto(produto: Produto) {
        produtos.remove(produto)
    }

    fun listarProdutos(): List<Produto> {
        return produtos.toList()
    }
}