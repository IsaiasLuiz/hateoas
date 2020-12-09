package br.edu.ifsp.arq.web2.service;

import br.edu.ifsp.arq.web2.domain.exception.NotFoundException;
import br.edu.ifsp.arq.web2.domain.model.Client;
import br.edu.ifsp.arq.web2.domain.resource.AddressResource;
import br.edu.ifsp.arq.web2.domain.resource.ClientResource;
import br.edu.ifsp.arq.web2.domain.model.Address;
import br.edu.ifsp.arq.web2.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Optional<Client> getByCode(Long code) {
        return repository.findById(code);
    }

    public Client save(ClientResource clientDto) {
        Client client = Client.builder()
                .name(clientDto.getName())
                .mail(clientDto.getMail())
                .phone(clientDto.getPhone())
                .cpf(clientDto.getCpf())
                .address(Address.builder()
                        .street(clientDto.getAddressResource().getStreet())
                        .number(clientDto.getAddressResource().getNumber())
                        .complement(clientDto.getAddressResource().getComplement())
                        .district(clientDto.getAddressResource().getDistrict())
                        .city(clientDto.getAddressResource().getCity())
                        .state(clientDto.getAddressResource().getState())
                        .zipCode(clientDto.getAddressResource().getZipCode())
                        .build())
                .build();
        return repository.save(client);
    }

    public Optional<Client> updateByCode(ClientResource clientResource, Long code) {
        Optional<Client> clientOptional = getByCode(code);
        if (!clientOptional.isPresent()) {
            throw new NotFoundException("Client com codigo " + code + " não existe");
        }
        Client client = clientOptional.get();
        client.setName(clientResource.getName());
        client.setMail(clientResource.getMail());
        client.setPhone(clientResource.getPhone());
        client.setCpf(clientResource.getCpf());
        Address address = new Address();
        address.setStreet(clientResource.getAddressResource().getStreet());
        address.setNumber(clientResource.getAddressResource().getNumber());
        address.setComplement(clientResource.getAddressResource().getComplement());
        address.setDistrict(clientResource.getAddressResource().getDistrict());
        address.setCity(clientResource.getAddressResource().getCity());
        address.setState(clientResource.getAddressResource().getState());
        address.setZipCode(clientResource.getAddressResource().getZipCode());
        client.setAddress(address);

        return Optional.of(repository.save(client));

    }

    public void deleteByCode(Long code) {
        Optional<Client> clientOptional = getByCode(code);
        if (!clientOptional.isPresent()) {
            throw new NotFoundException("Client com codigo " + code + " não existe");
        }
        repository.deleteById(code);
    }

    public Optional<Client> updateAddressByClientCode(AddressResource addressResource, Long code) {
        Optional<Client> clientOptional = getByCode(code);
        if (!clientOptional.isPresent()) {
            throw new NotFoundException("Client com codigo " + code + " não existe");
        }
        Client client = clientOptional.get();
        Address address = new Address();
        address.setStreet(addressResource.getStreet());
        address.setNumber(addressResource.getNumber());
        address.setComplement(addressResource.getComplement());
        address.setDistrict(addressResource.getDistrict());
        address.setCity(addressResource.getCity());
        address.setState(addressResource.getState());
        address.setZipCode(addressResource.getZipCode());
        client.setAddress(address);

        return Optional.of(repository.save(client));

    }

}
