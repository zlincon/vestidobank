package com.dh.vestidobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.vestidobank.model.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}
