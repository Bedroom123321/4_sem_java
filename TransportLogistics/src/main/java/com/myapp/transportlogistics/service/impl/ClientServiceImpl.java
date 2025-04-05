package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.mapper.ClientMapper;
import com.myapp.transportlogistics.model.Client;
import com.myapp.transportlogistics.repository.ClientRepository;
import com.myapp.transportlogistics.service.ClientService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientResponseDto findById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new IllegalStateException();
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
    public ClientResponseDto create(ClientRequestDto clientRequestDto) {
        Optional<Client> optionalClient =
                clientRepository.findByPhoneNumber(clientRequestDto.getPhoneNumber());
        if (optionalClient.isPresent()) {
            throw new IllegalStateException();
        }

        Client client = clientMapper.toEntity(clientRequestDto);
        Client savedClients = clientRepository.save(client);
        return clientMapper.toDto(savedClients);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new IllegalStateException();
        }
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String phoneNumber) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new IllegalStateException();
        }

        Client client = optionalClient.get();

        if (phoneNumber != null && !phoneNumber.equals(client.getPhoneNumber())) {
            client.setPhoneNumber(phoneNumber);
        }

        clientRepository.save(client);
    }
}
