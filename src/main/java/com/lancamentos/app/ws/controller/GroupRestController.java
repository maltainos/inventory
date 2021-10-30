package com.lancamentos.app.ws.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lancamentos.app.ws.io.model.Grupo;
import com.lancamentos.app.ws.service.GroupService;

@RestController
@RequestMapping(path = "/groups")
public class GroupRestController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping
	public List<Grupo> list(){
		return groupService.list();
	}

	@PostMapping
	public Grupo save(@Valid @RequestBody Grupo group) {
		return groupService.save(group);
	}
	
	@PutMapping(path = "/{groupId}")
	public Grupo update(@Valid @RequestBody Grupo group, @PathVariable Long groupId) {
		
		return groupService.addRole(group, groupId);
	}
}











