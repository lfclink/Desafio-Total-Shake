package br.com.desafio.totalshake.services;

import br.com.desafio.totalshake.exceptions.PedidoNotFoundException;
import br.com.desafio.totalshake.model.ItemPedido;
import br.com.desafio.totalshake.model.Pedido;
import br.com.desafio.totalshake.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido create(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll(){
        return (List<Pedido>) pedidoRepository.findAll();
    }

    public Pedido findById(Long id){
        validateFindedPedido(id);
        return pedidoRepository.findById(id).get();
    }

    public Pedido update(Long id, Pedido newPedido){
        validateFindedPedido(id);

        newPedido.setId(id);
        Pedido updatedPedido = pedidoRepository.save(newPedido);

        return updatedPedido;
    }

    public void delete(Long id){
        validateFindedPedido(id);

        pedidoRepository.deleteById(id);
    }

    public List<ItemPedido> findItenPedidoByPedido(Long id){
        validateFindedPedido(id);

        Pedido findedPedido = pedidoRepository.findById(id).get();

        return findedPedido.getItensPedidoList();
    }

    public void validateFindedPedido(Long id){
        try {
            pedidoRepository.findById(id).get();
        } catch (Exception e){
            throw new PedidoNotFoundException();
        }
    }
}
