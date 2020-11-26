package br.edu.ifsp.arq.web2.week_3.assembler;

import br.edu.ifsp.arq.web2.week_3.controller.ClientController;
import br.edu.ifsp.arq.web2.week_3.domain.resource.ClientResource;
import br.edu.ifsp.arq.web2.week_3.domain.model.Client;

import lombok.SneakyThrows;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

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
                .build();
        resource.add(linkTo(methodOn(ClientController.class).getById(client.getCode())).withSelfRel());
        return resource;
    }
}
