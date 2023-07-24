package lck.fichasloja.domain.service;

import lck.fichasloja.api.dto.ClientDTO;
import lck.fichasloja.api.dto.ClientInputDTO;
import lck.fichasloja.domain.exception.ResourceNotFound;
import lck.fichasloja.domain.model.Client;
import lck.fichasloja.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<ClientDTO> getAll(){
        List<Client> clients = repository.findAll();
        return clients.stream()
                .map(ClientDTO::new)
                .toList();
    }

    public ClientDTO findById(Long id){
        Client client = returnsClientIfExistsOrThrowsAnException(id);
        return new ClientDTO(client);
    }

    public ClientDTO save(ClientInputDTO clientDTO){
        Client client = new Client(clientDTO);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    public ClientDTO update(ClientInputDTO clientInputDTO, Long id){
        Client client = returnsClientIfExistsOrThrowsAnException(id);
        client = copyFromClientDTOTOClient(clientInputDTO, client);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    private Client returnsClientIfExistsOrThrowsAnException(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFound(id));
    }

    private Client copyFromClientDTOTOClient(ClientInputDTO dto, Client client){
        client.setName(dto.getName());
        client.setAmount(dto.getAmount());
        return client;
    }

}
