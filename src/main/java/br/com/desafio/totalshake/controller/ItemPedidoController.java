package br.com.desafio.totalshake.controller;

import br.com.desafio.totalshake.dto.itemPedido.ItemPedidoInputDTO;
import br.com.desafio.totalshake.dto.itemPedido.ItemPedidoOutputDTO;
import br.com.desafio.totalshake.model.ItemPedido;
import br.com.desafio.totalshake.services.ItemPedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/item-pedido")
public class ItemPedidoController {

    @Autowired
    ItemPedidoService itemPedidoService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(itemPedidoService.findAll()
                .stream()
                .map(this::toItemPedidoOutputDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getItemPedidoById(@PathVariable Long id){
        return ResponseEntity.ok(toItemPedidoOutputDTO(itemPedidoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> createItemPedido(@Valid @RequestBody ItemPedidoInputDTO novoItemPedido){
        ItemPedido itemPedido = modelMapper.map(novoItemPedido, ItemPedido.class);

        return ResponseEntity.ok(itemPedidoService.create(itemPedido));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateItemPedido(@PathVariable Long id,@Valid  @RequestBody ItemPedidoInputDTO newItemPedido){
        ItemPedido findedItemPedido = itemPedidoService.findById(id);
        ItemPedido updatedItemPedido = modelMapper.map(newItemPedido, ItemPedido.class);

        findedItemPedido.setDescricao(updatedItemPedido.getDescricao() != null ? updatedItemPedido.getDescricao() : findedItemPedido.getDescricao());
        findedItemPedido.setQuantidade(updatedItemPedido.getQuantidade() != null ? updatedItemPedido.getQuantidade() : findedItemPedido.getQuantidade());

        return ResponseEntity.ok(itemPedidoService.updateItemPedido(id, findedItemPedido));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteItemPedido(@PathVariable Long id){
        itemPedidoService.delete(id);
        return ResponseEntity.ok("Item do pedido removido");
    }

    private ItemPedidoOutputDTO toItemPedidoOutputDTO(ItemPedido itemPedido){
        return modelMapper.map(itemPedido, ItemPedidoOutputDTO.class);
    }

}
