package com.teconsis.mercadotec

class Estoque {
    private val produtos: MutableMap<Produto, Int> = mutableMapOf()
    private val quantidadeMinima: MutableMap<Produto, Int> = mutableMapOf()
    private val listaCompra: MutableList<Produto> = mutableListOf()

    fun adicionarProduto(produto: Produto, quantidade: Int) {
        val estoqueAtual = produtos.getOrDefault(produto, 0)
        val novaQuantidade = estoqueAtual + quantidade
        produtos[produto] = novaQuantidade

        val quantidadeMinima = quantidadeMinima[produto]
        if (quantidadeMinima != null && novaQuantidade <= quantidadeMinima) {
            if (!listaCompra.contains(produto)) {
                listaCompra.add(produto)
            }
        }
    }

    fun removerProduto(produto: Produto, quantidade: Int) {
        val estoqueAtual = produtos.getOrDefault(produto, 0)
        val novaQuantidade = estoqueAtual - quantidade

        if (novaQuantidade <= 0) {
            produtos.remove(produto)
        } else {
            produtos[produto] = novaQuantidade
        }
    }

    fun definirQuantidadeMinima(produto: Produto, quantidadeMinima: Int) {
        this.quantidadeMinima[produto] = quantidadeMinima

        val estoqueAtual = produtos[produto] ?: 0
        if (estoqueAtual <= quantidadeMinima) {
            if (!listaCompra.contains(produto)) {
                listaCompra.add(produto)
            }
        } else {
            listaCompra.remove(produto)
        }
    }

    fun gerarListaCompra(): List<Produto> {
        return listaCompra.toList()
    }
}