package com.lancamentos.app.ws.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lancamentos.app.ws.io.model.ContaCorrente;
import com.lancamentos.app.ws.io.repository.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {
	
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;

	public BigDecimal getSaldo() {
		List<ContaCorrente> contaCorrente = contaCorrenteRepository.findAll();
		if(contaCorrente.size() != 1)
			throw new RuntimeException("Conta nao encontrada");
		return contaCorrente.get(0).getSaldoConta();
	}
	
	public BigDecimal atualizarConta(BigDecimal montante, int operacao) {
		List<ContaCorrente> contaCorrente = contaCorrenteRepository.findAll();
		
		if(contaCorrente.size() != 1) 
			throw new RuntimeException("Conta nao encontrada");
		BigDecimal saldoAtual = contaCorrente.get(0).getSaldoConta();
		
		if(operacao == -1) 
			contaCorrente.get(0).setSaldoConta(saldoAtual.subtract(montante));
		
		if(operacao == 1)
			contaCorrente.get(0).setSaldoConta(saldoAtual.add(montante));
		
		contaCorrente = contaCorrenteRepository.saveAll(contaCorrente);
		if(contaCorrente.size() != 1) 
			throw new RuntimeException("Conta nao encontrada");
		
		return contaCorrente.get(0).getSaldoConta();
	}
}












