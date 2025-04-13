package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import java.util.List;

public interface ClientService {
    ClientResponseDto findById(Long id);

    List<ClientResponseDto> findAllClients();

    ClientResponseDto addClient(ClientRequestDto clientRequestDto);

    List<ClientResponseDto> addClients(List<ClientRequestDto> clientRequestDtos);

    void delete(Long id);

    void update(Long id, String phoneNumber);

}
