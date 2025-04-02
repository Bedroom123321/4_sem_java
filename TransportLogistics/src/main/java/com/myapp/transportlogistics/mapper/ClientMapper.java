package com.myapp.transportlogistics.mapper;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.model.Client;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client toEntity(ClientRequestDto dto) {
        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber(dto.getPhoneNumber());
        return client;
    }

    public ClientResponseDto toDto(Client client) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setPhoneNumber(client.getPhoneNumber());
        return dto;
    }

    public List<ClientResponseDto> toDtoList(List<Client> clients) {
        ArrayList<ClientResponseDto> clientResponseDtos = new ArrayList<>();
        for (Client client : clients) {
            clientResponseDtos.add(toDto(client));
        }
        return clientResponseDtos;
    }
}
