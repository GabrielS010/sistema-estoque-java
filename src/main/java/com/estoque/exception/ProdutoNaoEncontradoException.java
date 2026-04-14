package com.estoque.exception;

/* Estendi de Exception (Checked) para obrigar o tratamento do erro,
 já que num estoque é crítico saber se um produto sumiu.*/
public class ProdutoNaoEncontradoException extends Exception {

    public ProdutoNaoEncontradoException(String sku) {
        // Passo a mensagem para a classe pai (Exception)
        super("Não foi possível encontrar um produto com o código: " + sku);
    }
}