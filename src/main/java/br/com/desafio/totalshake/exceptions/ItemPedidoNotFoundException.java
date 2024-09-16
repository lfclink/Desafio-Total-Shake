package br.com.desafio.totalshake.exceptions;

public class ItemPedidoNotFoundException extends RuntimeException {

    private String message;
    public ItemPedidoNotFoundException() {
        super();
        this.message = "Item do pedido não encontrado.";
    }
}
