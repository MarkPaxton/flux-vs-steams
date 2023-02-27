package mpaxton.spring.rest.springreststub.controller;

import lombok.extern.slf4j.Slf4j;
import mpaxton.spring.rest.springreststub.model.Client;
import mpaxton.spring.rest.springreststub.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/clients")
@Slf4j
public class ClientController {
	private final ClientService service;

	public ClientController(@Autowired ClientService service) {
		this.service = service;
	}

	@RequestMapping(value="/{clientId}", method = RequestMethod.GET)
	public @ResponseBody Client getClientWithDelay(@PathVariable String clientId) throws InterruptedException {
		Thread.sleep(2000);
		var client = service.getClient(clientId);
		log.info("Returning client: {} ", client);
		return client;
	}
}
