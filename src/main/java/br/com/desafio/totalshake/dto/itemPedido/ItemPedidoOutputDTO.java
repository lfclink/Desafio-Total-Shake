package br.com.desafio.totalshake.dto.itemPedido;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class ItemPedidoOutputDTO {

    @Nullable
    private Long id;

    @Nullable
    private Integer quantidade;

    @Nullable
    private String descricao;

}
