package br.edu.infnet.cotacao.service.impl;

import java.io.FileNotFoundException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import br.edu.infnet.cotacao.persistence.model.Cotacao;
import br.edu.infnet.cotacao.service.WriteCsvResponse;

@Service
public class WriteCsvResponseImpl implements WriteCsvResponse{
	
	@Override
	public void writeCotacoes(String path, List<Cotacao> cotacoes) throws Exception {

		try {
			
	        Writer writer = Files.newBufferedWriter(Paths.get(path));
	        StatefulBeanToCsv<Cotacao> beanToCsv = new StatefulBeanToCsvBuilder<Cotacao>(writer).build();

	        beanToCsv.write(cotacoes);

	        writer.flush();
	        writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Error("Caminho passado nao pode ser acessado");
			
		}
	}
}
