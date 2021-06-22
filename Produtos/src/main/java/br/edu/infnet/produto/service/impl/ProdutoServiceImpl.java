package br.edu.infnet.produto.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.produto.dto.CotacaoDto;
import br.edu.infnet.produto.dto.ProdutoDto;
import br.edu.infnet.produto.feign.client.CotacaoClient;
import br.edu.infnet.produto.mapper.Mapper;
import br.edu.infnet.produto.persistence.model.Produto;
import br.edu.infnet.produto.persistence.repository.ProdutoRepository;
import br.edu.infnet.produto.service.ProdutoService;
import feign.FeignException;

@Service
public class ProdutoServiceImpl implements ProdutoService{

	@Autowired
	private CotacaoClient client;
	
	@Autowired
	private ProdutoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<ProdutoDto> getAll() {
		return new Mapper().fromEntity(repository.findAllByOrderById());
	}

	@Override
	@Transactional(readOnly = true)
	public ProdutoDto getById(Long id) {
		return new Mapper().fromEntity(repository.findById(id).get());
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProdutoDto> getAllWithQuotes() {
		
		try {
			List<ProdutoDto> produtosWithQuotes = new ArrayList<>();
			List<Produto> produtos = repository.findAllByOrderById();
			
			for(Produto produto : produtos) {
				List<CotacaoDto> quotes = client.getById(produto.getId());
				produtosWithQuotes.add(new Mapper().fromEntity(produto, quotes));
			}
			
			return produtosWithQuotes;
		} catch (FeignException e) {
			e.printStackTrace();
			throw new Error("ALGO DEU ERRADO");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ProdutoDto getByIdWithQuotes(Long id) {
		
		try {
			
			List<CotacaoDto> cotacao = client.getById(id);
			
			return new Mapper().fromEntity(repository.findById(id).get(), cotacao);
			
		} catch (FeignException e) {
			throw new Error("ALGO DEU ERRADO");
		}
	}

	@Override
	@Transactional
	public ProdutoDto create(ProdutoDto produto) {
		return new Mapper().fromEntity(
					repository.save(
							new Mapper().fromModel(produto)));
	}

	@Override
	@Transactional
	public ProdutoDto update(ProdutoDto produto) throws Exception {
		
		Optional<Produto> produtoEncontrado = repository.findById(produto.getId());
		
		if(produtoEncontrado.isPresent()) {
			return new Mapper().fromEntity(
					repository.save(
							new Mapper().fromModel(produto)));
		}
		else {
			throw new Exception("ID NAO ENCONTRADO");
		}
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		Optional<Produto> produtoEncontrado = repository.findById(id);
		
		if(produtoEncontrado.isPresent()) {
			repository.deleteById(id);
		}
		else {
			throw new Exception("ID NAO ENCONTRADO");
		}
		
	}
	
}
