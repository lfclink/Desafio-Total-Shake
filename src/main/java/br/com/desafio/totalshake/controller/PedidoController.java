package br.com.desafio.totalshake.controller;

import br.com.desafio.totalshake.dto.pedido.PedidoInputDTO;
import br.com.desafio.totalshake.dto.pedido.PedidoOutputDTO;
import br.com.desafio.totalshake.model.ItemPedido;
import br.com.desafio.totalshake.model.Pedido;
import br.com.desafio.totalshake.repositories.ItemPedidoRepository;
import br.com.desafio.totalshake.services.PedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(pedidoService.findAll()
                .stream()
                .map(this::toPedidoOutputDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPedidoById(@PathVariable Long id){
        return ResponseEntity.ok(toPedidoOutputDTO(pedidoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> createPedido(@Valid @RequestBody PedidoInputDTO novoPedido){
        Pedido pedido = modelMapper.map(novoPedido, Pedido.class);
        List<ItemPedido> itensPedido = pedido.getItensPedidoList();

        if(pedido.getDataHora() == null){
            pedido.setDataHora(LocalDateTime.now());
        }

        Pedido createdPedido = pedidoService.create(pedido);

        if(!itensPedido.isEmpty()){
            itemPedidoRepository.saveAll(itensPedido);
        }

        return ResponseEntity.ok(createdPedido);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable Long id,@Valid  @RequestBody PedidoInputDTO newPedido){
        Pedido findedPedido = pedidoService.findById(id);
        Pedido updatedPedido = modelMapper.map(newPedido, Pedido.class);

        findedPedido.setStatus(updatedPedido.getStatus() != null ? updatedPedido.getStatus() : findedPedido.getStatus());
        findedPedido.setDataHora(updatedPedido.getDataHora() != null ? updatedPedido.getDataHora() : findedPedido.getDataHora());

        return ResponseEntity.ok(pedidoService.update(id, findedPedido));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Long id){
        pedidoService.delete(id);
        return ResponseEntity.ok("Pedido deletado");
    }

    private PedidoOutputDTO toPedidoOutputDTO(Pedido pedido){
        return modelMapper.map(pedido, PedidoOutputDTO.class);
    }

}
