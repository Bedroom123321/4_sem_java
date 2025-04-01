package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.service.impl.ClientServiceImpl;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clients")
public class ClientController {
    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("get/{id}")
    public ClientResponseDto getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping("get")
    public List<ClientResponseDto> getAllClients() {
        return clientService.findAllClients();
    }

    @PostMapping("post")
    public ClientResponseDto createClient(@RequestBody ClientRequestDto clientRequestDto) {
        return clientService.create(clientRequestDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PutMapping("update/{id}")
    public void updateClient(@PathVariable Long id,
                             @RequestParam String phoneNumber) {
        clientService.update(id, phoneNumber);
    }
}
