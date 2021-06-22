package br.edu.infnet.produto.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.produto.dto.CotacaoDto;


@FeignClient(name = "cotacao", url = "http://localhost:8081")
public interface CotacaoClient {

	@GetMapping(value = "/cotacao/{id}", produces = "application/json")
	public List<CotacaoDto> getById(@PathVariable Long id);
	
	@GetMapping(value = "/cotacao/listar", produces = "application/json")
	public List<CotacaoDto> getAll();
}
