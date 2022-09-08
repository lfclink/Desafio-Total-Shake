package br.com.desafio.totalshake.dto.pedido;

import br.com.desafio.totalshake.enums.EnumStatus;
import br.com.desafio.totalshake.model.ItemPedido;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoInputDTO {

    @Nullable
    private LocalDateTime dataHora;

    @NotNull
    private EnumStatus status;

    @Nullable
    private List<ItemPedido> itemPedidos;


}
