package com.cjcaram.clients.service;

import com.cjcaram.clients.entity.Client;
import com.cjcaram.clients.exception.ClientNotFoundException;
import com.cjcaram.clients.repository.ClientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));
    }

    @Transactional
    public Client saveClient(@Valid Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client updateClient(Long id, @Valid Client client) {
        Client existentClient = findById(id);
        Client updatedClient = Client.builder()
                .id(existentClient.getId())
                .name(client.getName() != null ? client.getName() : existentClient.getName())
                .email(client.getEmail() != null ? client.getEmail() : existentClient.getEmail())
                .birthdate(client.getBirthdate() != null ? client.getBirthdate() : existentClient.getBirthdate())
                .active(existentClient.isActive())
                .build();

        return clientRepository.save(updatedClient);
    }

    @Transactional
    public void disableClient(Long id) {
        Client client = findById(id);
        client.setActive(false);
        clientRepository.save(client);
    }
}
