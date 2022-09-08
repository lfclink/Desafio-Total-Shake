package br.com.desafio.totalshake.repositories;

import br.com.desafio.totalshake.enums.EnumStatus;
import br.com.desafio.totalshake.model.Pedido;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @BeforeAll
    public void setup(){
        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(EnumStatus.PRONTO);
        pedidoRepository.save(pedido);
    }

    @AfterAll
    public void reset(){
        pedidoRepository.deleteAll();
    }

    @Test
    public void createNewPedido(){
        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(EnumStatus.CONFIRMADO);

        pedidoRepository.save(pedido);

        assertTrue(pedidoRepository.findById(2L).isPresent());
    }

    @Test
    public void findAll() {
        List<Pedido> pedidos = (List<Pedido>) pedidoRepository.findAll();

        int quantidade = pedidos.size();

        assertTrue(quantidade >= 1);
    }

}