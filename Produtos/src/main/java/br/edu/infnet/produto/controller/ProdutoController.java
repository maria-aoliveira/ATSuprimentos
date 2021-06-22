package br.edu.infnet.produto.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.infnet.produto.dto.ProdutoDto;
import br.edu.infnet.produto.erros.Erro;
import br.edu.infnet.produto.service.ProdutoService;
import br.edu.infnet.produto.service.impl.AmazonClientImpl;

@RestController
@RequestMapping("produto")
public class ProdutoController {
	
	@Autowired
	private AmazonClientImpl amazonClient;

    
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/listar")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ProdutoDto> getAll(){
		return service.getAll();	
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ProdutoDto getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/cotacoes/listar")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ProdutoDto> getAllWithQuotes(){
		return service.getAllWithQuotes();	
	}
	
	@GetMapping("/cotacoes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ProdutoDto getByIdWithQuotes(@PathVariable Long id) {
		return service.getByIdWithQuotes(id);
	}

//	@PostMapping("/criar")
//	@ResponseStatus(value = HttpStatus.CREATED)
//	public ProdutoDto create(@RequestBody @Valid ProdutoDto produto) {
//		return service.create(produto);
//	}
	
	@PostMapping("/cadastro")
	@ResponseStatus(value = HttpStatus.CREATED)
    public ProdutoDto upload(@RequestPart(value = "file") MultipartFile multipartFile, @RequestPart @Valid ProdutoDto produto) throws IllegalStateException, IOException {
		String uploadFile = this.amazonClient.uploadFile(multipartFile);
		produto.setFoto(uploadFile);   
		System.out.println(produto);
        return service.create(produto);
    }
	
//	@PutMapping("/atualizar")
//	@ResponseStatus(value = HttpStatus.OK)
//	public ProdutoDto update(@RequestBody @Valid ProdutoDto produto) throws Exception {
//		return service.update(produto);
//	}
	
	@PutMapping("/atualizar")
	@ResponseStatus(value = HttpStatus.OK)
	public ProdutoDto update(@RequestPart(value = "file") MultipartFile multipartFile, @RequestPart @Valid ProdutoDto produto) throws IllegalStateException, IOException, Exception {
		String uploadFile = this.amazonClient.uploadFile(multipartFile);
		produto.setFoto(uploadFile);   
		return service.update(produto);
	}
	
	@GetMapping("/file/{keyname}")
	public ResponseEntity<?> downloadFile(@PathVariable String keyname) {
		ByteArrayOutputStream downloadInputStream = amazonClient.download(keyname);
	
		return ResponseEntity.ok()
					.contentType(contentType(keyname))
					.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + keyname + "\"")
					.body(downloadInputStream.toByteArray());	
	}
	
	private MediaType contentType(String keyname) {
		String[] arr = keyname.split("\\.");
		String type = arr[arr.length-1];
		switch(type) {
			case "txt": return MediaType.TEXT_PLAIN;
			case "png": return MediaType.IMAGE_PNG;
			case "jpg": return MediaType.IMAGE_JPEG;
			default: return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
		
	
//	@DeleteMapping("/{id}/excluir")
//	@ResponseStatus(value = HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable Long id) throws Exception {
//		service.delete(id);
//	}
	
	@DeleteMapping("/{id}/excluir")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws Exception {
		ProdutoDto produto = service.getById(id);
        String fileName = produto.getFoto().split(".com/")[1];
        amazonClient.deleteFileFromS3Bucket(fileName);
		service.delete(id);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Erro validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        
        return new Erro("Erro de Validacao", fieldErrors.stream().map(s -> s.getDefaultMessage()).collect(Collectors.toList()));
    }

}
