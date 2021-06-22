package br.edu.infnet.cotacao.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.cotacao.dto.ArquivoCsvDto;
import br.edu.infnet.cotacao.dto.CotacaoDto;
import br.edu.infnet.cotacao.dto.CotacaoUpdateDto;
import br.edu.infnet.cotacao.erros.Erro;
import br.edu.infnet.cotacao.service.CotacaoService;

@RestController
@RequestMapping("cotacao")
public class CotacaoController {
	
	@Autowired
	private CotacaoService service;

	@GetMapping("/listar")
	@ResponseStatus(value = HttpStatus.OK)
	public List<CotacaoDto> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/listarVencidos")
	@ResponseStatus(value = HttpStatus.OK)
	public List<CotacaoDto> getAllExpired(){
		return service.getAllExpired();
	}

//	@GetMapping("/{id}")
//	@ResponseStatus(value = HttpStatus.OK)
//	public List<CotacaoDto> getById(@PathVariable Long id){
//		return service.getById(id);
//	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public CotacaoDto getById(@PathVariable Long id){
		return service.getById(id);
	}
	
	@PostMapping("/criar")
	@ResponseStatus(value = HttpStatus.CREATED)
	public CotacaoDto create(@RequestBody @Valid CotacaoDto cotacao) throws Exception {
		return service.create(cotacao);
	}
	
	@PutMapping("/atualizar")
	@ResponseStatus(value = HttpStatus.OK)
	public CotacaoDto update(@RequestBody @Valid CotacaoUpdateDto cotacao) throws Exception {
		return service.update(cotacao);
	}
	
	@DeleteMapping("/{id}/excluir")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable Long id) throws Exception {
		service.delete(id);
	}
	
	@PostMapping(path = "/csv", produces = "text/csv")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void writeCsv(@RequestBody ArquivoCsvDto file) throws Exception{
		service.writeCotacoes(file.getPath()+"\\"+file.getFile());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Erro validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        
        return new Erro("Erro de Validacao", fieldErrors.stream().map(s -> s.getDefaultMessage()).collect(Collectors.toList()));
    }
}
