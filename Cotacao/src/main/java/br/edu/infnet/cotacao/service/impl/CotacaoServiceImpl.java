package br.edu.infnet.cotacao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.cotacao.dto.CotacaoDto;
import br.edu.infnet.cotacao.dto.CotacaoUpdateDto;
import br.edu.infnet.cotacao.dto.ProdutoDto;
import br.edu.infnet.cotacao.feign.client.ProdutoClient;
import br.edu.infnet.cotacao.mapper.Mapper;
import br.edu.infnet.cotacao.persistence.model.Cotacao;
import br.edu.infnet.cotacao.persistence.repository.CotacaoRepository;
import br.edu.infnet.cotacao.service.CotacaoService;
import br.edu.infnet.cotacao.service.WriteCsvResponse;
import feign.FeignException;

@Service
public class CotacaoServiceImpl implements CotacaoService {
	
	
	@Autowired
	private WriteCsvResponse csv;

	@Autowired
	private ProdutoClient client;
	
	@Autowired
	private CotacaoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<CotacaoDto> getAll() {
		return new Mapper().fromEntity(
				repository.findByValidadeCotacaoGreaterThanEqualOrderById(LocalDate.now()));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CotacaoDto> getAllExpired() {
		return new Mapper().fromEntity(
				repository.findByValidadeCotacaoLessThanOrderById(LocalDate.now()));
	}

//	@Override
//	@Transactional(readOnly = true)
//	public List<CotacaoDto> getById(Long id) {
//		return new Mapper().fromEntity(repository.findByIdProdutoAndValidadeCotacaoGreaterThanEqualOrderById(id, LocalDate.now()));
//	}
	
	@Override
	@Transactional(readOnly = true)
	public CotacaoDto getById(Long id) {
		return new Mapper().fromEntity(repository.getById(id));
	}

	@Override
	@Transactional
	public CotacaoDto create(CotacaoDto cotacao) throws Exception {
		
		try {
			ProdutoDto produto = client.getById(cotacao.getIdProduto());
			
			if(produto != null)
				return new Mapper().fromEntity(
						repository.save(new Mapper().fromModel(cotacao, LocalDate.now(), produto.getNome())));
			else
				return null;
		} catch (FeignException e) {
			throw new Error("NAO EXISTE PRODUTO COM ID INFORMADO", e);
		}
	}

	@Override
	@Transactional
	public CotacaoDto update(CotacaoUpdateDto cotacao) throws Exception {
		
		Optional<Cotacao> cotacaoEncontrada = repository.findById(cotacao.getId());
		
		if(cotacaoEncontrada.isPresent()) {
			return new Mapper().fromEntity(
						repository.save(new Mapper().fromModelUpdate(cotacao)));
		}else {
			throw new Exception("ID NAO ENCONTRADO");
		}
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		Optional<Cotacao> cotacaoEncontrada = repository.findById(id);
		
		if(cotacaoEncontrada.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new Exception("ID NAO ENCONTRADO");
		}
		
	}

	@Override
	public String writeCotacoes(String path) throws Exception {
		try {
			List<Cotacao> cotacoes = repository.findByValidadeCotacaoGreaterThanEqualOrderById(LocalDate.now());
			csv.writeCotacoes(path, cotacoes);
			
			return	"Arquivo Criado em " + path;		
		} catch (Exception e) {
			throw new Exception("Algo deu errado");
		}
	}

}
