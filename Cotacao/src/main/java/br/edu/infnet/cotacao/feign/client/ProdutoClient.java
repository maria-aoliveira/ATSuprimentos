package br.edu.infnet.cotacao.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.cotacao.dto.ProdutoDto;

@FeignClient(name = "produto", url = "http://localhost:8080")
public interface ProdutoClient {
	
	@GetMapping(value = "/produto/{id}", consumes = "application/json")
	public ProdutoDto getById(@PathVariable("id") Long id);	
}
