package com.teconsis.mercadotec

cclass App {
    private val listaCompras: ListaCompras = ListaCompras()
    private val estoque: Estoque = Estoque()

    fun iniciar() {
        var sair = false

        while (!sair) {
            exibirMenuPrincipal()
            when (lerOpcao()) {
                1 -> cadastrarProduto()
                2 -> cadastrarProdutoSemCodigoBarras()
                3 -> adicionarProdutoListaCompras()
                4 -> removerProdutoListaCompras()
                5 -> listarProdutosListaCompras()
                6 -> adicionarProdutoEstoque()
                7 -> removerProdutoEstoque()
                8 -> definirQuantidadeMinima()
                9 -> gerarListaCompraAutomatica()
                10 -> adicionarProdutoListaCompraAutomatica()
                11 -> exibirListaCompraAutomatica()
                0 -> {
                    sair = true
                    println("Encerrando o programa...")
                }
                else -> println("Opção inválida. Tente novamente.")
            }
        }
    }

    private fun exibirMenuPrincipal() {
        println("------ Menu ------")
        println("1. Cadastrar produto (código de barras)")
        println("2. Cadastrar produto (manual)")
        println("3. Adicionar produto à lista de compras")
        println("4. Remover produto da lista de compras")
        println("5. Listar produtos da lista de compras")
        println("6. Adicionar produto ao estoque")
        println("7. Remover produto do estoque")
        println("8. Definir quantidade mínima para um produto no estoque")
        println("9. Gerar lista de compra automática")
        println("10. Adicionar produto à lista de compra automática")
        println("11. Exibir lista de compra automática")
        println("0. Sair")
    }

    private fun lerOpcao(): Int {
        print("Escolha uma opção: ")
        return readLine()?.toIntOrNull() ?: -1
    }

    private fun cadastrarProduto() {
        println("---- Cadastro de Produto ----")
        print("Código de Barras: ")
        val codigoBarras = readLine() ?: ""
        print("Nome do Produto: ")
        val nome = readLine() ?: ""
        listaCompras.adicionarProduto(Produto(codigoBarras, nome))
        println("Produto cadastrado com sucesso!")
    }

    private fun cadastrarProdutoSemCodigoBarras() {
        println("---- Cadastro de Produto ----")
        print("Nome do Produto: ")
        val nome = readLine() ?: ""
        listaCompras.adicionarProduto(Produto(UUID.randomUUID().toString(), nome))
        println("Produto cadastrado com sucesso!")
    }

    private fun adicionarProdutoListaCompras() {
        println("---- Adicionar Produto à Lista de Compras ----")
        print("Código de Barras do Produto: ")
        val codigoBarras = readLine() ?: ""
        val produto = encontrarProdutoPorCodigoBarras(codigoBarras)
        if (produto != null) {
            listaCompras.adicionarProduto(produto)
            println("Produto adicionado à lista de compras.")
        } else {
            println("Produto não encontrado. Deseja cadastrar um novo produto? (S/N)")
            val opcao = readLine()?.toUpperCase()
            if (opcao == "S") {
                cadastrarProduto()
            }
        }
    }

    private fun removerProdutoListaCompras() {
        println("---- Remover Produto da Lista de Compras ----")
        print("Código de Barras do Produto: ")
        val codigoBarras = readLine() ?: ""
        val produto = encontrarProdutoPorCodigoBarras(codigoBarras)
        if (produto != null) {
            listaCompras.removerProduto(produto)
            println("Produto removido da lista de compras.")
        } else {
            println("Produto não encontrado.")
        }
    }

    private fun listarProdutosListaCompras() {
        println("---- Produtos na Lista de Compras ----")
        val produtos = listaCompras.listarProdutos()
        if (produtos.isEmpty()) {
            println("Nenhum produto cadastrado na lista de compras.")
        } else {
            for (produto in produtos) {
                println(produto)
            }
        }
    }

    private fun adicionarProdutoEstoque() {
        println("---- Adicionar Produto ao Estoque ----")
        print("Código de Barras do Produto: ")
        val codigoBarras = readLine() ?: ""
        val produto = encontrarProdutoPorCodigoBarras(codigoBarras)
        if (produto != null) {
            print("Quantidade: ")
            val quantidade = readLine()?.toIntOrNull() ?: 0
            estoque.adicionarProduto(produto, quantidade)
            println("Produto adicionado ao estoque.")
        } else {
            println("Produto não encontrado. Deseja cadastrar um novo produto? (S/N)")
            val opcao = readLine()?.toUpperCase()
            if (opcao == "S") {
                cadastrarProduto()
            }
        }
    }

    private fun removerProdutoEstoque() {
        println("---- Remover Produto do Estoque ----")
        print("Código de Barras do Produto: ")
        val codigoBarras = readLine() ?: ""
        val produto = encontrarProdutoPorCodigoBarras(codigoBarras)
        if (produto != null) {
            print("Quantidade: ")
            val quantidade = readLine()?.toIntOrNull() ?: 0
            estoque.removerProduto(produto, quantidade)
            println("Produto removido do estoque.")
        } else {
            println("Produto não encontrado.")
        }
    }

    private fun definirQuantidadeMinima() {
        println("---- Definir Quantidade Mínima ----")
        print("Código de Barras do Produto: ")
        val codigoBarras = readLine() ?: ""
        val produto = encontrarProdutoPorCodigoBarras(codigoBarras)
        if (produto != null) {
            print("Quantidade Mínima: ")
            val quantidadeMinima = readLine()?.toIntOrNull() ?: 0
            estoque.definirQuantidadeMinima(produto, quantidadeMinima)
            println("Quantidade mínima definida para o produto.")
        } else {
            println("Produto não encontrado.")
        }
    }

    private fun gerarListaCompraAutomatica() {
        val produtos = estoque.gerarListaCompra()
        if (produtos.isEmpty()) {
            println("Não há produtos na lista de compra automática.")
        } else {
            println("---- Lista de Compra Automática ----")
            for (produto in produtos) {
                println(produto)
            }
        }
    }

    private fun adicionarProdutoListaCompraAutomatica() {
        println("---- Adicionar Produto à Lista de Compra Automática ----")
        print("Código de Barras do Produto: ")
        val codigoBarras = readLine() ?: ""
        val produto = encontrarProdutoPorCodigoBarras(codigoBarras)
        if (produto != null) {
            estoque.definirQuantidadeMinima(produto, 0)
            println("Produto adicionado à lista de compra automática.")
        } else {
            println("Produto não encontrado. Deseja cadastrar um novo produto? (S/N)")
            val opcao = readLine()?.toUpperCase()
            if (opcao == "S") {
                cadastrarProduto()
                println("Produto adicionado à lista de compra automática.")
            }
        }
    }

    private fun exibirListaCompraAutomatica() {
        val produtos = estoque.gerarListaCompra()
        if (produtos.isEmpty()) {
            println("Não há produtos na lista de compra automática.")
        } else {
            println("---- Lista de Compra Automática ----")
            for (produto in produtos) {
                println(produto)
            }
        }
    }

    private fun encontrarProdutoPorCodigoBarras(codigoBarras: String): Produto? {
        // Simulação de busca de produto por código de barras em uma API
        // Substitua essa parte do código pela lógica de chamada da API
        // e tratamento da resposta
        return null
    }
}

fun main() {
    val app = App()
    app.iniciar()
}