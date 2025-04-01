package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;

import java.util.List;

public interface ClientService {
    ClientResponseDto findById(Long id);

    List<ClientResponseDto> findAllClients();

    ClientResponseDto create(ClientRequestDto clientRequestDto);

    void delete(Long id);

    void update(Long id, String phoneNumber);

}
