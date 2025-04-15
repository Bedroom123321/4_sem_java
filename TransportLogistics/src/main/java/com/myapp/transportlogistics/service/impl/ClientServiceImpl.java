package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.exception.EntityAlreadyExistsException;
import com.myapp.transportlogistics.exception.EntityNotFoundException;
import com.myapp.transportlogistics.mapper.ClientMapper;
import com.myapp.transportlogistics.model.Client;
import com.myapp.transportlogistics.repository.ClientRepository;
import com.myapp.transportlogistics.service.ClientService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientResponseDto findById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new EntityNotFoundException("Клиент с таким ID не найден");
        }
        return clientMapper.toDto(optionalClient.get());
    }

    @Override
    @Transactional
    public List<ClientResponseDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toDtoList(clients);
    }

    @Override
    @Transactional
    public ClientResponseDto addClient(ClientRequestDto clientRequestDto) {
        Optional<Client> optionalClient = clientRepository
            .findByPhoneNumber(clientRequestDto.getPhoneNumber());
        if (optionalClient.isPresent()) {
            throw new EntityAlreadyExistsException("Такой клиент уже существует");
        }

        Client client = clientMapper.toEntity(clientRequestDto);
        Client savedClients = clientRepository.save(client);
        return clientMapper.toDto(savedClients);
    }

    @Override
    @Transactional
    public List<ClientResponseDto> addClients(List<ClientRequestDto> clientRequestDtos) {

        return  clientRequestDtos.stream()
                .distinct()
                .filter(client -> clientRepository
                        .findByPhoneNumber(client.getPhoneNumber()).isEmpty())
                .map(clientMapper::toEntity)
                .map(clientRepository::save)
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new EntityNotFoundException("Клиент с таким ID не найден");
        }
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String phoneNumber) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new EntityNotFoundException("Клиент с таким ID не найден");
        }

        Client client = optionalClient.get();

        if (phoneNumber != null && !phoneNumber.equals(client.getPhoneNumber())) {
            client.setPhoneNumber(phoneNumber);
            clientRepository.save(client);
        }

    }

}
