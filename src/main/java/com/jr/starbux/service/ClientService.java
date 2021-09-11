package com.jr.starbux.service;

import org.springframework.stereotype.Service;

import com.jr.starbux.entity.Client;
import com.jr.starbux.repository.ClientRepository;

@Service
public class ClientService extends BaseService<Client, Long, ClientRepository>{

}
