package br.com.desafio.totalshake.dto.itemPedido;

import br.com.desafio.totalshake.enums.EnumStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class ItemPedidoInputDTO {

    @Nullable
    private Integer quantidade;

    @Nullable
    private String descricao;


    private Long idPedido;
}
