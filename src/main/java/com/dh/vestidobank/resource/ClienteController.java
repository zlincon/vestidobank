package com.dh.vestidobank.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dh.vestidobank.model.dto.create.ClienteCreateDTO;
import com.dh.vestidobank.model.entity.Cliente;
import com.dh.vestidobank.service.ClienteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
public class ClienteController {
	
    private final ClienteService clienteService;

    
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ClienteCreateDTO cliente){
    	Cliente savedCliente = this.clienteService.create(cliente);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCliente.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    	
    	
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente cliente = this.clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }
    
    
}
