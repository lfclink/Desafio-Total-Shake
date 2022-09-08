package br.com.desafio.totalshake.services;

import br.com.desafio.totalshake.exceptions.ItemPedidoNotFoundException;
import br.com.desafio.totalshake.exceptions.PedidoNotFoundException;
import br.com.desafio.totalshake.model.ItemPedido;
import br.com.desafio.totalshake.model.Pedido;
import br.com.desafio.totalshake.repositories.ItemPedidoRepository;
import br.com.desafio.totalshake.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    public ItemPedido create(ItemPedido itemPedido){
        Optional<Pedido> pedido = pedidoRepository.findById(itemPedido.getPedido().getId());

        if (pedido.isEmpty()){
            throw new PedidoNotFoundException();
        }
        itemPedido.setPedido(pedido.get());

        return itemPedidoRepository.save(itemPedido);
    }

    public List<ItemPedido> findAll(){
        return (List<ItemPedido>) itemPedidoRepository.findAll();
    }

    public ItemPedido findById(Long id){
        validateFindedItemPedido(id);
        return itemPedidoRepository.findById(id).get();
    }

    public ItemPedido updateItemPedido(Long id, ItemPedido newItemPedido){
        validateFindedItemPedido(id);

        newItemPedido.setId(id);
        ItemPedido updatedItemPedido = itemPedidoRepository.save(newItemPedido);

        return updatedItemPedido;
    }

    public void delete(Long id){
        validateFindedItemPedido(id);

        itemPedidoRepository.deleteById(id);
    }

    public void validateFindedItemPedido(Long id){
        try {
            itemPedidoRepository.findById(id).get();
        } catch (Exception e) {
            throw new ItemPedidoNotFoundException();
        }
    }
}
