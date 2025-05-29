package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.request.LoginDto;
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
        Client client = optionalClient.get();
        return clientMapper.toDto(client); // Маппер должен теперь включать пароль
    }

    @Override
    @Transactional
    public List<ClientResponseDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toDtoList(clients); // Маппер должен включать пароль
    }

    @Override
    @Transactional
    public ClientResponseDto addClient(ClientRequestDto clientRequestDto) {
        Optional<Client> optionalClient = clientRepository.findByLogin(clientRequestDto.getLogin());
        if (optionalClient.isPresent()) {
            throw new EntityAlreadyExistsException("Клиент с таким логином уже существует");
        }

        Client client = clientMapper.toEntity(clientRequestDto);
        client.setPassword(clientRequestDto.getPassword()); // Устанавливаем пароль
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient); // Возвращаем с паролем
    }

    @Override
    @Transactional
    public List<ClientResponseDto> addClients(List<ClientRequestDto> clientRequestDtos) {
        List<Client> newClients = clientRequestDtos.stream()
                .distinct()
                .filter(client -> clientRepository.findByLogin(client.getLogin()).isEmpty())
                .map(clientMapper::toEntity)
                .toList();

        List<Client> savedClients = clientRepository.saveAll(newClients);
        return savedClients.stream()
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

    @Override
    public ClientResponseDto login(LoginDto loginDto) {
        Optional<Client> optionalClient = clientRepository.findByLogin(loginDto.getLogin());
        if (optionalClient.isEmpty()) {
            throw new EntityNotFoundException("Клиент с таким логином не найден");
        }

        Client client = optionalClient.get();
        if (!loginDto.getPassword().equals(client.getPassword())) {
            throw new EntityNotFoundException("Неверный пароль");
        }

        return clientMapper.toDto(client); // Возвращаем с паролем
    }

    public Client updateClient(Long id, Client client) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        existingClient.setFirstName(client.getFirstName());
        existingClient.setLastName(client.getLastName());
        existingClient.setPhoneNumber(client.getPhoneNumber());
        // Если нужно обновлять логин и пароль, добавьте:
        // existingClient.setLogin(client.getLogin());
        // existingClient.setPassword(client.getPassword());
        return clientRepository.save(existingClient);
    }
}