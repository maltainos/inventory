package com.lancamentos.app.ws.io.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lancamentos.app.ws.io.model.Lancamento;
import com.lancamentos.app.ws.io.model.Pessoa;
import com.lancamentos.app.ws.io.model.TipoLancamento;
import com.lancamentos.app.ws.io.repository.LancamentoRepository;
import com.lancamentos.app.ws.io.repository.PessoaRepository;
import com.lancamentos.app.ws.io.repository.filter.LancamentoFilter;
import com.lancamentos.app.ws.io.service.exception.PessoaInexistenteOuInativoException;
import com.lancamentos.app.ws.shared.MyUtils;

@Service
public class LancamentoService {
	
	@Autowired
	private MyUtils myUtils;
	
	@Autowired
	private ContaCorrenteService contaCorrenteService;

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Page<Lancamento> listar(LancamentoFilter lancamentoFilter, Pageable pageable){
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}
	
	public Lancamento buscar(Long codigo) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo);
		
		if(!lancamento.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return lancamento.get();
	}

	public Lancamento salvar(@Valid Lancamento lancamento) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		
		System.out.println(pessoa.get().isInativo());;
		if(!pessoa.isPresent() || pessoa.get().isInativo())
				throw new PessoaInexistenteOuInativoException();
		lancamento.setLancamentoCodigo(myUtils.generatedString(25));
		Lancamento lancamentoSalva = lancamentoRepository.save(lancamento);
		if(lancamentoSalva.getTipo().equals(TipoLancamento.DESPESA))
			contaCorrenteService.atualizarConta(lancamentoSalva.getValor(), -1);
		
		if(lancamentoSalva.getTipo().equals(TipoLancamento.RECEITA))
			contaCorrenteService.atualizarConta(lancamentoSalva.getValor(), 1);
		
		return lancamentoSalva;
	}
}












