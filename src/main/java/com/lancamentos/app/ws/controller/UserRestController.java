package com.lancamentos.app.ws.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lancamentos.app.ws.execptionhandler.Erro;
import com.lancamentos.app.ws.io.model.Grupo;
import com.lancamentos.app.ws.io.model.User;
import com.lancamentos.app.ws.service.UserNotFoundException;
import com.lancamentos.app.ws.service.UserService;

@RestController
@RequestMapping(path = "users")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public List<User> search(){
		return userService.list();
	}

	@PostMapping
	public User save(@Valid @RequestBody User user) {
		return userService.save(user);
	}
	
	@PostMapping(path = "/{userId}/groups")
	public User addGroup(@RequestBody Grupo group, @PathVariable String userId) {
		
		return userService.addGroup(group, userId);
	}
	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex){
		
		String mensagemUsuario = messageSource.getMessage("usuario.nao-encontrado", null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvidor = ExceptionUtils.getRootCauseMessage(ex);
		
		Erro error = new Erro(mensagemUsuario, mensagemDesenvolvidor, HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.badRequest().body(error);
	}
}
















