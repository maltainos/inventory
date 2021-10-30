package com.lancamentos.app.ws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lancamentos.app.ws.io.model.Grupo;
import com.lancamentos.app.ws.io.repository.GroupRepository;
import com.lancamentos.app.ws.service.exception.GrupoInexistenteException;

@Service
public class GroupService {
	
	@Autowired
	private GroupRepository groupRepository;

	public List<Grupo> list() {
		return groupRepository.findAll();
	}

	public Grupo save(Grupo group) {
		return groupRepository.save(group);
	}

	public Grupo addRole(Grupo group, Long groupId) {
		Optional<Grupo> groupFounded = groupRepository.findById(groupId);
		if(!groupFounded.isPresent())
			throw new GrupoInexistenteException();
		groupFounded.get().setRoles(group.getRoles());
		Grupo groupSaved = groupRepository.save(groupFounded.get());
		return groupSaved;
	}
}






