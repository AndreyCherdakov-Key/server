package com.server.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.services.ifcs.ServerServiceIfc;

@RestController
public class ServerController {

	private final ServerServiceIfc serverServiceImpl;

	@Autowired
	public ServerController(ServerServiceIfc serverServiceImpl) {
		super();
		this.serverServiceImpl = serverServiceImpl;
	}
	
	/*
	 * Get HTTP requests
	 * Send back JSON - HTTP status
	 */
	@GetMapping
	public ResponseEntity<HttpStatus> handleRequest(@RequestParam int clientId) throws InterruptedException, ExecutionException {
        return ResponseEntity.status(serverServiceImpl.handleHTTPRequest(clientId).get()).build();
    }
	
	
}
