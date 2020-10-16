package com.dh.vestidobank.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dh.vestidobank.exception.ArgumentNotValidException;
import com.dh.vestidobank.exception.ObjectNotFoundException;
import com.dh.vestidobank.model.dto.create.ClienteCreateDTO;
import com.dh.vestidobank.model.dto.update.ClienteUpdateDTO;
import com.dh.vestidobank.model.entity.Cliente;
import com.dh.vestidobank.model.entity.Conta;
import com.dh.vestidobank.model.entity.Gerente;
import com.dh.vestidobank.repository.ClienteRepository;
import java.util.List;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {
	
	private final  ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente create(ClienteCreateDTO clienteDto) {
			
		Cliente cliente = fromCreateDto(clienteDto);
		return this.clienteRepository.save(cliente);
		
	}
	
	
	@Transactional
	public Cliente update(ClienteUpdateDTO clienteNovoDto) {
		
		Cliente clienteSalvo = this.findById(clienteNovoDto.getId());
		
		clienteSalvo.setNome(clienteNovoDto.getNome());
		clienteSalvo.setEndereco(clienteNovoDto.getEndereco());
		
		return this.clienteRepository.save(clienteSalvo);
		
	}
	
	
	public Cliente findById(Long id) {
		
		Optional
		.ofNullable(id)
		.orElseThrow(() -> new ArgumentNotValidException("Id não pode ser nulo") );
		
		return this.clienteRepository.findById(id)
				.orElseThrow( () -> new  ObjectNotFoundException("Cliente de id " + id + " não encontrado"));
	
	}

	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}
	
	public void delete(Long id) {
		this.findById(id);
		this.clienteRepository.deleteById(id);
	}
	
	public Cliente fromCreateDto(ClienteCreateDTO clienteDto) {
		
		Cliente cliente = Cliente.builder()
		  .nome(clienteDto.getNome())
		  .cpf(clienteDto.getCpf())
		  .endereco(clienteDto.getEndereco())
		  .conta(
				 Conta.builder()
				 .tipoConta(clienteDto.getTipoConta())
				 .numeroConta(clienteDto.getNumeroConta())
				 .build())
		  .gerente(
				  Gerente.builder()
				  .id(clienteDto.getGerenteId())
				  .build())
		  .build();
		
		cliente.getConta().setCliente(cliente);
		
		return cliente;
		 
	}

}
