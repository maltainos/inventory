package com.lancamentos.app.ws.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lancamentos.app.ws.event.RecursoCriadoEvent;
import com.lancamentos.app.ws.service.PessoaService;
import com.lancamentos.app.ws.shared.MyUtils;
import com.lancamentos.app.ws.io.model.Pessoa;
import com.lancamentos.app.ws.io.repository.PessoaRepository;

@RestController
@RequestMapping(path = "pessoas")
public class PessoaRestController {
	
	@Autowired
	private MyUtils myUtils;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Pessoa> listar(){
		return pessoaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, 
			HttpServletResponse response){
		pessoa.setPessoaCodigo(myUtils.generatedString(30));
		System.out.println(pessoa.getPessoaCodigo());
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@GetMapping(path = "/{codigo}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Long codigo){
		Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(codigo);
		
		if(!pessoaEncontrada.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoaEncontrada.get());
	}
	
	@DeleteMapping(path = "/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.deleteById(codigo);
	}
	
	@PutMapping(path = "/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, 
			@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		
		return ResponseEntity.ok(pessoaService.atualizar(codigo, pessoa));
	}
	
	@PutMapping(path = "/{codigo}/status")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}

}








