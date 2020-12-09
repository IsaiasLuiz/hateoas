package br.edu.ifsp.arq.web2.controller;

import br.edu.ifsp.arq.web2.assembler.ClientAssembler;
import br.edu.ifsp.arq.web2.domain.exception.NotFoundException;
import br.edu.ifsp.arq.web2.domain.model.Client;
import br.edu.ifsp.arq.web2.domain.resource.AddressResource;
import br.edu.ifsp.arq.web2.domain.resource.ClientResource;
import br.edu.ifsp.arq.web2.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientAssembler clientAssembler;

    @Autowired
    private PagedResourcesAssembler<ClientResource> pagedResourcesAssembler;

    @GetMapping
    public PagedModel getAll(Pageable page) {
        Page<ClientResource> clients = new PageImpl<>(service.getAll(page)
                .getContent().stream()
                .map(clientAssembler::toModel).collect(Collectors.toList()));
        return pagedResourcesAssembler.toModel(clients);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ClientResource> getByCode(@PathVariable("code") Long code) {
        Client client = service.getByCode(code).orElseThrow(() -> new NotFoundException("Client com codigo " + code + " não existe"));
        return ResponseEntity.ok(clientAssembler.toModel(client));
    }

    @PostMapping
    public ResponseEntity<ClientResource> save(@Valid @RequestBody ClientResource clientResource) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientAssembler.toModel(service.save(clientResource)));
    }

    @PutMapping("/{code}")
    public ResponseEntity<ClientResource> updateByCode(@Valid @RequestBody ClientResource clientResource, @PathVariable("code") Long code) {
        Client client = service.updateByCode(clientResource, code).orElseThrow(() -> new NotFoundException("Client com codigo " + code + " não existe"));
        return ResponseEntity.ok(clientAssembler.toModel(client));
    }

    @PutMapping("/{code}/address")
    public ResponseEntity<ClientResource> updateAddressByClientCode(@Valid @RequestBody AddressResource addressResource, @PathVariable("code") Long code) {
        Client client = service.updateAddressByClientCode(addressResource, code).orElseThrow(() -> new NotFoundException("Client com codigo " + code + " não existe"));
        return ResponseEntity.ok(clientAssembler.toModel(client));
    }

    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable("code") Long code) {
        service.deleteByCode(code);
    }

}
