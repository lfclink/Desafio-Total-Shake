package br.com.desafio.totalshake.dto.pedido;

import br.com.desafio.totalshake.enums.EnumStatus;
import br.com.desafio.totalshake.model.ItemPedido;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoOutputDTO {

    @NotNull
    private Long id;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    private EnumStatus status;

    private List<ItemPedido> itensPedido;


}
