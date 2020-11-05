package com.dh.vestidobank.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dh.vestidobank.model.dto.ValorOperacao;
import com.dh.vestidobank.model.dto.create.ClienteCreateDTO;
import com.dh.vestidobank.model.dto.update.ClienteUpdateDTO;
import com.dh.vestidobank.model.entity.Cliente;
import com.dh.vestidobank.service.ClienteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
public class ClienteController {
	
    private final ClienteService clienteService;
    
    
  
    @PatchMapping("/{id}/alterarLimite")
    public ResponseEntity<Void> ativar(@PathVariable Long id, @Valid @RequestBody ValorOperacao valor){
    	this.clienteService.alterarLimite(id,valor);
    	return ResponseEntity.noContent().build();
    	
    }
    
    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id){
    	this.clienteService.ativar(id);
    	return ResponseEntity.noContent().build();
    	
    }
    
    @PatchMapping("/{id}/saque")
    public ResponseEntity<Void> sacar(@PathVariable Long id, @Valid @RequestBody ValorOperacao valor){
    	this.clienteService.sacar(valor, id);
    	return ResponseEntity.noContent().build();
    	
    }
    
    @PatchMapping("/{id}/deposito")
    public ResponseEntity<Void> depositar(@PathVariable Long id, @Valid @RequestBody ValorOperacao valor){
    	this.clienteService.depositar(valor, id);
    	return ResponseEntity.noContent().build();
    	
    }
    @PatchMapping("/{origem}/transferencia/{destino}")
    public ResponseEntity<Void> transferir(
    		@PathVariable Long origem,	
    		@PathVariable Long destino,	
    		@Valid @RequestBody ValorOperacao valor){
    	
    	this.clienteService.transferir(valor, origem, destino);
    	
    	return ResponseEntity.noContent().build();
    	
    }
    
    
    
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
