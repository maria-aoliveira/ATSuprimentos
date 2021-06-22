package br.edu.infnet.cotacao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.cotacao.dto.CotacaoDto;
import br.edu.infnet.cotacao.dto.CotacaoUpdateDto;

@Service
public interface CotacaoService {

	public List<CotacaoDto> getAll();
	
	public List<CotacaoDto> getAllExpired();

//	public List<CotacaoDto> getById(Long id);
	
	public CotacaoDto getById(Long id);
	
	public CotacaoDto create(CotacaoDto cotacao) throws Exception;
	
	public CotacaoDto update(CotacaoUpdateDto cotacao) throws Exception;
	
	public void delete(Long id) throws Exception;
	
	public String writeCotacoes(String path) throws Exception;

}
