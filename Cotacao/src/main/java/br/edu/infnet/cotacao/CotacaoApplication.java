package br.edu.infnet.cotacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CotacaoApplication.class, args);
	}

}
