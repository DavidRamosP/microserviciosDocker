package com.abc.clientes.controller;

import com.abc.clientes.model.ClienteDto;
import com.abc.clientes.model.ClienteReq;
import com.abc.clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.abc.clientes.properties.PropertiesConfig;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

  @Autowired
  ClienteService clienteService;
  @Autowired
  PropertiesConfig propConfig;
  @PostMapping
  public Integer create(@Valid @RequestBody ClienteReq clienteReq, BindingResult result) {
    logger.debug("Creando un Cliente {}", clienteReq);

    if (result.hasErrors()) {
      throw new RuntimeException("Datos incompletos");
    }
    return clienteService.create(clienteReq).getCodCliente();
  }

  @PutMapping("/{codCliente}")
  public void update(@PathVariable Integer codCliente, @Valid @RequestBody ClienteReq clienteReq,
      BindingResult result) {
    logger.debug("Actualizando Cliente con id {} y con data {}", codCliente, clienteReq);
    if (result.hasErrors()) {
      throw new RuntimeException("Datos incompletos");
    }
    clienteService.update(codCliente, clienteReq);
  }

  @GetMapping
  public List<ClienteDto> getAll() {
    logger.debug("Obteniendo Clientes");

    return clienteService.getAll();
  }

  @GetMapping("/{codCliente}")
  public ClienteDto getDtoById(@PathVariable Integer codCliente) {
    logger.debug("Obteniendo Cliente con codCliente {}", codCliente);

    // Simulacion de Falla
    double valor = Math.random() * 100;
    logger.warn("Tasa de errores configurado {} ", propConfig.getTasaErrores());
    if (valor < propConfig.getTasaErrores()) {
      logger.error("Error al obtener Cliente {} ", codCliente);
      throw new RuntimeException("Error al Obtener cliente");
    }
//    if (valor > propConfig.getTasaErrores()){
//     try {
//       logger.warn("Tiempo de espera excedido");
//       Thread.sleep(15000);
//       return clienteService.getDtoById(codCliente);
//     } catch (InterruptedException e) {
//         throw new RuntimeException(e);
//     }
//    }
    return clienteService.getDtoById(codCliente);
  }

}
