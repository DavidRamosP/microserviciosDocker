package com.abc.prestamos.client;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "clientes")
public interface ClienteClient {
  Logger logger = LoggerFactory.getLogger(ClienteClient.class);
  @RequestMapping(
          method= RequestMethod.GET,
          value="/clientes/{codCliente}",
          consumes="application/json")
  @CircuitBreaker(name = "clientes", fallbackMethod = "getClienteFallback")
//  @Bulkhead(name = "clientes", fallbackMethod = "getClienteFallback")
  @Retry(name = "clientes")
  ClienteDto getCliente(@PathVariable("codCliente") Integer codCliente);

  default ClienteDto getClienteFallback( Integer codCliente, Exception e){
    logger.debug("Obteniendo el cliente {} mediante fallback", codCliente);
    return ClienteDto
            .builder()
            .codCliente(0)
            .nombres("--")
            .apellidoPaterno("--")
            .apellidoMaterno("--")
            .clasificacion(0)
            .build();
  }

//  default ClienteDto getClienteFallback( Integer codCliente, BulkheadFullException e){
//    logger.debug("Obteniendo el cliente {} mediante fallback, por sobrecarga mapeada por BulkHead", codCliente);
//    return ClienteDto
//            .builder()
//            .codCliente(0)
//            .nombres("No disponible")
//            .apellidoPaterno("No disponible")
//            .apellidoMaterno("No disponible")
//            .clasificacion(0)
//            .build();
//  }
}
