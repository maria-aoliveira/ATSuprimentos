package br.edu.infnet.cotacao.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.cotacao.dto.CotacaoDto;
import br.edu.infnet.cotacao.dto.CotacaoUpdateDto;
import br.edu.infnet.cotacao.persistence.model.Cotacao;

@Service
public class Mapper {
	
	public Cotacao fromModel(CotacaoDto model, LocalDate date) {
		var entity = new Cotacao();
		
		entity.setId(model.getId());
		entity.setIdProduto(model.getIdProduto());
		entity.setFornecedor(model.getFornecedor());
		entity.setPreco(model.getPreco());
		entity.setValidadeCotacao(model.getValidadeCotacao());
		entity.setDataCotacao(date);
		
		return entity;
	}
	
	public Cotacao fromModel(CotacaoDto model, LocalDate date, String produto) {
		var entity = new Cotacao();
		
		entity.setId(model.getId());
		entity.setIdProduto(model.getIdProduto());
		entity.setFornecedor(model.getFornecedor());
		entity.setPreco(model.getPreco());
		entity.setProduto(produto);
		entity.setValidadeCotacao(model.getValidadeCotacao());
		entity.setDataCotacao(date);
		
		return entity;
	}
	
	public Cotacao fromModel(CotacaoDto model) {
		var entity = new Cotacao();
		
		entity.setId(model.getId());
		entity.setIdProduto(model.getIdProduto());
		entity.setFornecedor(model.getFornecedor());
		entity.setPreco(model.getPreco());
		entity.setValidadeCotacao(model.getValidadeCotacao());
		entity.setDataCotacao(model.getDataCotacao());
		
		return entity;
	}
	
	public Cotacao fromModelUpdate(CotacaoUpdateDto model) {
		var entity = new Cotacao();
		
		entity.setId(model.getId());
		entity.setIdProduto(model.getIdProduto());
		entity.setFornecedor(model.getFornecedor());
		entity.setPreco(model.getPreco());
		entity.setProduto(model.getProduto());
		entity.setValidadeCotacao(model.getValidadeCotacao());
		entity.setDataCotacao(model.getDataCotacao());
		
		return entity;
	}
	
	public CotacaoDto fromEntity(Cotacao model) {
		var entity = new CotacaoDto();
		
		entity.setId(model.getId());
		entity.setIdProduto(model.getIdProduto());
		entity.setFornecedor(model.getFornecedor());
		entity.setPreco(model.getPreco());
		entity.setProduto(model.getProduto());
		entity.setValidadeCotacao(model.getValidadeCotacao());
		entity.setDataCotacao(model.getDataCotacao());
		
		return entity;
	}
	
	public List<CotacaoDto> fromEntity(List<Cotacao> models) {
		List<CotacaoDto> list = new ArrayList<>();
		
		for(Cotacao model : models) {
			
			var entity = new CotacaoDto();
			entity.setId(model.getId());
			entity.setIdProduto(model.getIdProduto());
			entity.setFornecedor(model.getFornecedor());
			entity.setPreco(model.getPreco());
			entity.setProduto(model.getProduto());
			entity.setValidadeCotacao(model.getValidadeCotacao());
			entity.setDataCotacao(model.getDataCotacao());
			
			list.add(entity);
		}
		return list;
	}
}
