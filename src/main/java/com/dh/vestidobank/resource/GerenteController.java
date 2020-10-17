package com.dh.vestidobank.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dh.vestidobank.model.dto.create.GerenteCreateDTO;
import com.dh.vestidobank.model.entity.Cliente;
import com.dh.vestidobank.model.entity.Gerente;
import com.dh.vestidobank.service.ClienteService;
import com.dh.vestidobank.service.GerenteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/gerentes")
@AllArgsConstructor
public class GerenteController {
	
	private final GerenteService gerenteService;
	private final ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody GerenteCreateDTO gerente){
		
		Gerente gerenteCriado = this.gerenteService.create(gerente);
		
		URI uri = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(gerenteCriado.getId())
		.toUri();
		
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Gerente> update(@PathVariable Long id,@Valid @RequestBody Gerente gerente){
		gerente.setId(id);
		
		this.gerenteService.update(gerente);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Gerente> findById(@PathVariable Long id){
		
		Gerente gerente = this.gerenteService.findById(id);
		
		return ResponseEntity.ok(gerente);
	}
	
	
	@GetMapping("/{id}/clientesInativos")
	public ResponseEntity<List<Cliente>> findAllClienteInativos(@PathVariable Long id){
		List<Cliente> clientes = this.clienteService.findByGerenteAndInativo(Gerente.builder().id(id).build());
		
		return ResponseEntity.ok(clientes);

	}
	
	
	
	@GetMapping
	public ResponseEntity<List<Gerente>> findAll(){
		List<Gerente> allGerente = this.gerenteService.findAll();
		return ResponseEntity.ok(allGerente);
	}
	
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		this.gerenteService.delete(id);
		
		return  ResponseEntity.noContent().build();
	}

	
}
