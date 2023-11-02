package com.abc.clientes.repository;

import com.abc.clientes.entity.Cliente;
import com.abc.clientes.model.ClienteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

  @Query("SELECT cl "
      + "   FROM Cliente cl"
  )
  List<ClienteDto> getAll();

  Optional<ClienteDto> findDtoByCodCliente(Integer codCliente);


}
