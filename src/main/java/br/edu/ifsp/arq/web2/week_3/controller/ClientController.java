package br.edu.ifsp.arq.web2.week_3.controller;

import br.edu.ifsp.arq.web2.week_3.assembler.ClientAssembler;
import br.edu.ifsp.arq.web2.week_3.domain.exception.NotFoundException;
import br.edu.ifsp.arq.web2.week_3.domain.model.Client;
import br.edu.ifsp.arq.web2.week_3.domain.resource.ClientResource;
import br.edu.ifsp.arq.web2.week_3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientAssembler clientAssembler;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    @GetMapping
    public PagedModel getAll(final Pageable page) {
        return pagedResourcesAssembler.toModel(service.getAll(page));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ClientResource> getById(@PathVariable("code") final Long code) {
        Client client = service.getById(code).orElseThrow(() -> new NotFoundException("Client com codigo " + code + " não existe"));
        return ResponseEntity.ok(clientAssembler.toModel(client));
    }

    @PostMapping
    public ResponseEntity<ClientResource> save(@Valid @RequestBody ClientResource clientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientAssembler.toModel(service.save(clientDto)));
    }

}
