package com.dh.vestidobank.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.dh.vestidobank.model.dto.update.ClienteUpdateDTO;
import com.dh.vestidobank.model.entity.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id,@Valid @RequestBody ClienteUpdateDTO cliente){
        cliente.setId(id);

        this.clienteService.update(cliente);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente cliente = this.clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }



    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> allCliente = this.clienteService.findAll();
        return ResponseEntity.ok(allCliente);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.clienteService.delete(id);

        return  ResponseEntity.noContent().build();
    }



}
