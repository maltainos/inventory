package com.lancamentos.app.ws.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lancamentos.app.ws.io.model.Grupo;
import com.lancamentos.app.ws.io.model.User;
import com.lancamentos.app.ws.io.repository.UserRepository;
import com.lancamentos.app.ws.shared.MyUtils;

@Service
public class UserService {
	
	@Autowired
	private MyUtils myUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<User> list() {
		return userRepository.findAll();
	}

	public User save(User user) {
		
		user.setUserCodigo(myUtils.generatedString(30));
		user.setCreatedOn(LocalDateTime.now());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}
	
	public User addGroup(Grupo grupo, String userId) {
		
		Optional<User> userFounded = userRepository.findByUserCodigo(userId);
		
		if(!userFounded.isPresent())
			throw new UserNotFoundException();
		User user = userFounded.get();
		user.getGrupo().add(grupo);
		System.out.println(user);
		return userRepository.save(user);
	}

}





