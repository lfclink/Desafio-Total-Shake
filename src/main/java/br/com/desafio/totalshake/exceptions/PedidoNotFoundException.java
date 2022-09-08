package br.com.desafio.totalshake.exceptions;

public class PedidoNotFoundException extends RuntimeException {

    private String message;
    public PedidoNotFoundException() {
        super();
        this.message = "Pedido não encontrado.";
    }
}
