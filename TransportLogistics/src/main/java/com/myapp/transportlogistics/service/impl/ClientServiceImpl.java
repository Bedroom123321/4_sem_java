package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.mapper.ClientMapper;
import com.myapp.transportlogistics.model.Client;
import com.myapp.transportlogistics.repository.ClientRepository;
import com.myapp.transportlogistics.service.ClientService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponseDto findById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new IllegalStateException("Водителя с id " + id + " нет в базе");
        }
        return clientMapper.toDto(optionalClient.get());
    }

    @Override
    public List<ClientResponseDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toDtoList(clients);
    }

    @Override
    public ClientResponseDto create(ClientRequestDto clientRequestDto) {
        Optional<Client> optionalClient =
                clientRepository.findByPhoneNumber(clientRequestDto.getPhoneNumber());
        if (optionalClient.isPresent()) {
            throw new IllegalStateException("Клиент с таким номером "
                    + "телефона уже зарегистрирован");
        }

        Client client = clientMapper.toEntity(clientRequestDto);
        Client savedClients = clientRepository.save(client);
        return clientMapper.toDto(savedClients);
    }

    @Override
    public void delete(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new IllegalStateException("Клиента с id " + id + " нет в базе");
        }
        clientRepository.deleteById(id);
    }

    @Override
    public void update(Long id, String secondName, String phoneNumber) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new IllegalStateException("Клиента с id " + id + " нет в базе");
        }

        Client client = optionalClient.get();

        if (secondName != null && !secondName.equals(client.getSecondName())) {
            client.setSecondName(secondName);
        }

        if (phoneNumber != null && !phoneNumber.equals(client.getPhoneNumber())) {
            client.setPhoneNumber(phoneNumber);
        }

        clientRepository.save(client);
    }
}
