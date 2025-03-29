package com.cjcaram.clients.service;

import com.cjcaram.clients.dto.ClientDto;
import com.cjcaram.clients.entity.Client;
import com.cjcaram.clients.exception.ClientNotFoundException;
import com.cjcaram.clients.repository.ClientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public List<Client> listActives() {
        return clientRepository.findByActiveTrue();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));
    }

    @Transactional
    public Client saveClient(@Valid ClientDto clientDto) {
        Client client = new Client();
        modelMapper.map(clientDto, client);
        return clientRepository.save(client);
    }

    @Transactional
    public Client updateClient(Long id, ClientDto clientToUpdate) {
        Client existentClient = findById(id);
        modelMapper.map(clientToUpdate, existentClient);
        return clientRepository.save(existentClient);
    }

    @Transactional
    public Client patchClient(Long id, ClientDto clientDto) {
        Client existentClient = findById(id);
        ClientDto updatedClient = ClientDto.builder()
                .name(clientDto.getName() != null ? clientDto.getName() : existentClient.getName())
                .email(clientDto.getEmail() != null ? clientDto.getEmail() : existentClient.getEmail())
                .birthdate(clientDto.getBirthdate() != null ? clientDto.getBirthdate() : existentClient.getBirthdate())
                .build();

        modelMapper.map(updatedClient, existentClient);
        return clientRepository.save(existentClient);
    }

    @Transactional
    public void disableClient(Long id) {
        Client client = findById(id);
        client.setActive(false);
        clientRepository.save(client);
    }
}
