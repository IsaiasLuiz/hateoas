package br.edu.ifsp.arq.web2.assembler;

import br.edu.ifsp.arq.web2.controller.ClientController;
import br.edu.ifsp.arq.web2.domain.model.Client;
import br.edu.ifsp.arq.web2.domain.resource.ClientResource;
import br.edu.ifsp.arq.web2.domain.resource.AddressResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClientAssembler extends RepresentationModelAssemblerSupport<Client, ClientResource> {

    public ClientAssembler() {
        super(ClientController.class, ClientResource.class);
    }

    @Override
    public ClientResource toModel(Client client) {
        ClientResource resource = ClientResource.builder()
                .code(client.getCode())
                .name(client.getName())
                .mail(client.getMail())
                .phone(client.getPhone())
                .cpf(client.getCpf())
                .addressResource(AddressResource.builder()
                        .street(client.getAddress().getStreet())
                        .number(client.getAddress().getNumber())
                        .complement(client.getAddress().getComplement())
                        .district(client.getAddress().getDistrict())
                        .city(client.getAddress().getCity())
                        .state(client.getAddress().getState())
                        .zipCode(client.getAddress().getZipCode())
                        .build())
                .build();
        resource.add(linkTo(methodOn(ClientController.class).getByCode(client.getCode())).withSelfRel());
        return resource;
    }
}
