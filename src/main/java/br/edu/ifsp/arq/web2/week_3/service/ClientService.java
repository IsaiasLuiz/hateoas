package br.edu.ifsp.arq.web2.week_3.service;

import br.edu.ifsp.arq.web2.week_3.domain.resource.ClientResource;
import br.edu.ifsp.arq.web2.week_3.domain.model.Client;
import br.edu.ifsp.arq.web2.week_3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Page<Client> getAll(Pageable page) {
        return repository.findAll(page);
    }

    public Optional<Client> getById(final Long code) {
        return repository.findById(code);
    }

    public Client save(ClientResource clientDto) {
        Client client = Client.builder()
                .name(clientDto.getName())
                .mail(clientDto.getMail())
                .phone(clientDto.getPhone())
                .build();
        return repository.save(client);
    }

}
