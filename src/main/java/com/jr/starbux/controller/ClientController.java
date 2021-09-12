package com.jr.starbux.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.entity.Client;
import com.jr.starbux.exceptions.ObjectNotFoundException;
import com.jr.starbux.request.ClientRequest;
import com.jr.starbux.response.ClientResponse;
import com.jr.starbux.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Client service")
@RestController()
@RequestMapping("/client")
public class ClientController
		extends BaseViewAndCreateController<Client, Long, ClientService, ClientRequest, ClientResponse> {

	protected ClientController() {
		super(Client.class, ClientResponse.class);
	}

	@Operation(summary = "Creates a new client")
	@Override
	public ClientResponse create(@RequestBody ClientRequest object) throws Exception {
		return super.create(object);
	}

	@Operation(summary = "Lists all clients")
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	@Override
	public List<ClientResponse> findAll() {
		return super.findAll();
	}

	@Operation(summary = "Finds a specific client by Id")
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	@Override
	public ClientResponse find(@PathVariable Long id) throws ObjectNotFoundException {
		return super.find(id);
	}

}
