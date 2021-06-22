package br.edu.infnet.cotacao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.cotacao.persistence.model.Cotacao;

@Service
public interface WriteCsvResponse {
	
	public void writeCotacoes(String path, List<Cotacao> cotacoes) throws Exception;
}
