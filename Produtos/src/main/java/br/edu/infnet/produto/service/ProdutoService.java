package br.edu.infnet.produto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.produto.dto.ProdutoDto;

@Service
public interface ProdutoService {

	public List<ProdutoDto> getAll();
	
	public ProdutoDto getById(Long id);
	
	public ProdutoDto create(ProdutoDto produto);
	
	public ProdutoDto update(ProdutoDto produto) throws Exception;
	
	public void delete(Long id) throws Exception;

	public List<ProdutoDto> getAllWithQuotes();

	public ProdutoDto getByIdWithQuotes(Long id);
}
