package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cliente.Cliente;
import br.com.deveficiente.novoautor.cliente.ClienteRepository;
import br.com.deveficiente.novoautor.livro.Livro;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NovaCompraRequest {

    private List<ItensRequest> itens = new ArrayList<>();

    private String emailCliente;

    public List<ItensRequest> getItens() {
        return itens;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public Compra toModel(EntityManager manager, ClienteRepository repository) {

        Optional<Livro> possivelLivro = Optional.ofNullable(manager.find(Livro.class, itens.get(0).getIdLivro()));

        if (possivelLivro.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Livro não encontrado!");
        }

        Livro livro = possivelLivro.get();

        List<Item> listItens = this.itens.stream()
                .map(itensRequest -> new Item(itensRequest.getIdLivro(),itensRequest.getQuantidade(),livro.getPreco())).toList();

        Cliente cliente = repository.findByEmail(emailCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não encontrado!"));


        return new Compra(listItens,cliente);
    }
}
