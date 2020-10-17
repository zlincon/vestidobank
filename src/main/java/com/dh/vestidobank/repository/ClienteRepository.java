package com.dh.vestidobank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.vestidobank.model.entity.Cliente;
import com.dh.vestidobank.model.entity.Gerente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
	List<Cliente> findByGerenteAndAtivoFalse(Gerente gerente);
}
