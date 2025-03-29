package com.cjcaram.clients.controller;

import com.cjcaram.clients.dto.ClientDto;
import com.cjcaram.clients.entity.Client;
import com.cjcaram.clients.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client", description = "API used to manage client information")
@RestController
@RequestMapping("api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Create new client")
    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientDto client) {
        Client newClient = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @Operation(summary = "Get client by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    // TODO: List should be post with SearchDto used as RequestBody
    @Operation(summary = "List all clients")
    @GetMapping
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> clients = clientService.listActives();
        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Update client by ID ID")
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDto client) {
        Client updatedClient = clientService.updateClient(id, client);
        return ResponseEntity.ok(updatedClient);
    }

    // TODO: PathMapping

    @Operation(summary = "Disable client by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableClientById(@PathVariable Long id) {
        clientService.disableClient(id);
        return ResponseEntity.noContent().build();
    }
}
