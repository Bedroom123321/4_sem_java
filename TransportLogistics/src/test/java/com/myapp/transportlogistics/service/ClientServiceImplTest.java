package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.exception.EntityAlreadyExistsException;
import com.myapp.transportlogistics.exception.EntityNotFoundException;
import com.myapp.transportlogistics.mapper.ClientMapper;
import com.myapp.transportlogistics.model.Client;
import com.myapp.transportlogistics.repository.ClientRepository;
import com.myapp.transportlogistics.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;

    private Client client1;
    private Client client2;
    private ClientResponseDto clientResponseDto1;
    private ClientResponseDto clientResponseDto2;
    private ClientRequestDto clientRequestDto1;
    private ClientRequestDto clientRequestDto2;
    private final long firstClientId = 1L;
    private final long secondClientId = 2L;
    private final String newPhoneNumber = "+375293332211";

    @BeforeEach
    public void setUp() {
        client1 = new Client(1L, "Роман"
                , "Бадестов", "+375291184712");

        clientRequestDto1 = new ClientRequestDto("Роман"
                , "Бадестов", "+375291184712");

        clientResponseDto1 = new ClientResponseDto(1L, "Роман"
                , "Бадестов", "+375291184712");

        client2 = new Client(2L, "Илья"
                , "Ходин", "+375333916665");

        clientRequestDto2 = new ClientRequestDto("Илья"
                , "Ходин", "+375333916665");

        clientResponseDto2 = new ClientResponseDto(2L, "Илья"
                , "Ходин", "+375333916665");
    }

    @Test
    void testFindById() {

        Mockito.when(clientRepository.findById(firstClientId)).thenReturn(Optional.of(client1));
        Mockito.when(clientMapper.toDto(client1)).thenReturn(clientResponseDto1);

        ClientResponseDto result = clientServiceImpl.findById(firstClientId);

        Assertions.assertEquals(clientResponseDto1, result);
    }

    @Test
    void testFindById_notFoundException() {

        Mockito.when(clientRepository.findById(secondClientId)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> clientServiceImpl.findById(secondClientId));
    }

    @Test
    void testFindAll() {

        Mockito.when(clientRepository.findAll()).thenReturn(List.of(client1, client2));
        Mockito.when(clientMapper.toDtoList(List.of(client1, client2)))
                .thenReturn(List.of(clientResponseDto1,clientResponseDto2));

        List<ClientResponseDto> result = clientServiceImpl.findAllClients();

        Assertions.assertEquals(List.of(clientResponseDto1,clientResponseDto2), result);

    }

    @Test
    void testAddClient() {

        Mockito.when(clientMapper.toEntity(clientRequestDto1)).thenReturn(client1);
        Mockito.when(clientRepository.save(client1)).thenReturn(client1);
        Mockito.when(clientMapper.toDto(client1)).thenReturn(clientResponseDto1);

        ClientResponseDto result = clientServiceImpl.addClient(clientRequestDto1);

        Assertions.assertEquals(clientResponseDto1, result);
    }

    @Test
    void testAddClient_alreadyExistsException() {
        Mockito.when(clientRepository.findByPhoneNumber(clientRequestDto1.getPhoneNumber())).thenReturn(Optional.of(client1));

        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> clientServiceImpl.addClient(clientRequestDto1));
    }

    @Test
    void testAddClients() {

        Mockito.when(clientRepository.findByPhoneNumber(clientRequestDto1.getPhoneNumber())).thenReturn(Optional.empty());
        Mockito.when(clientRepository.findByPhoneNumber(clientRequestDto2.getPhoneNumber())).thenReturn(Optional.empty());

        Mockito.when(clientMapper.toEntity(clientRequestDto1)).thenReturn(client1);
        Mockito.when(clientMapper.toEntity(clientRequestDto2)).thenReturn(client2);

        Mockito.when(clientRepository.save(client1)).thenReturn(client1);
        Mockito.when(clientRepository.save(client2)).thenReturn(client2);

        Mockito.when(clientMapper.toDto(client1)).thenReturn(clientResponseDto1);
        Mockito.when(clientMapper.toDto(client2)).thenReturn(clientResponseDto2);

        List<ClientResponseDto> result = clientServiceImpl.addClients(List.of(clientRequestDto1,clientRequestDto2));

        Assertions.assertEquals(List.of(clientResponseDto1,clientResponseDto2), result);
    }

    @Test
    void testAddClients_clientAlreadyExists() {

        Mockito.when(clientRepository.findByPhoneNumber(clientRequestDto1.getPhoneNumber())).thenReturn(Optional.of(client1));
        Mockito.when(clientRepository.findByPhoneNumber(clientRequestDto2.getPhoneNumber())).thenReturn(Optional.empty());
        Mockito.when(clientMapper.toEntity(clientRequestDto2)).thenReturn(client2);
        Mockito.when(clientRepository.save(client2)).thenReturn(client2);
        Mockito.when(clientMapper.toDto(client2)).thenReturn(clientResponseDto2);

        List<ClientResponseDto> result = clientServiceImpl.addClients(List.of(clientRequestDto1,clientRequestDto2));

        Assertions.assertEquals(List.of(clientResponseDto2), result);
    }

    @Test
    void testDelete() {
        Mockito.when(clientRepository.findById(firstClientId)).thenReturn(Optional.of(client1));

        clientServiceImpl.delete(firstClientId);

        Mockito.verify(clientRepository, Mockito.times(1)).deleteById(firstClientId);
    }

    @Test
    void testDelete_notFoundException() {
        Mockito.when(clientRepository.findById(secondClientId)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> clientServiceImpl.delete(2L));
    }

    @Test
    void testUpdate() {

        Mockito.when(clientRepository.findById(firstClientId)).thenReturn(Optional.of(client1));

        clientServiceImpl.update(firstClientId, newPhoneNumber);

        Assertions.assertEquals(newPhoneNumber,client1.getPhoneNumber());
        Mockito.verify(clientRepository, Mockito.times(1)).save(client1);
    }

    @Test
    void testUpdate_notFoundException() {
        Mockito.when(clientRepository.findById(secondClientId)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> clientServiceImpl.update(secondClientId, newPhoneNumber));
    }

}
