package pl.piomin.microservices.customer.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.piomin.microservices.customer.intercomm.CommercialAutoClient;
import pl.piomin.microservices.customer.model.Account;
import pl.piomin.microservices.customer.model.Address;
import pl.piomin.microservices.customer.model.Customer;
import pl.piomin.microservices.customer.model.CustomerType;

@RestController
public class Api {
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private CommercialAutoClient commercialAutoClient;
	
	protected static Logger logger = LoggerFactory.getLogger(Api.class.getName());
	
	private List<Customer> customers;
	
	public Api() {
		customers = new ArrayList<>();
		customers.add(new Customer(1, "12345", "Adam Kowalski", CustomerType.INDIVIDUAL));
		customers.add(new Customer(2, "12346", "Anna Malinowska", CustomerType.INDIVIDUAL));
		customers.add(new Customer(3, "12347", "Paweł Michalski", CustomerType.INDIVIDUAL));
		customers.add(new Customer(4, "12348", "Karolina Lewandowska", CustomerType.INDIVIDUAL));
	}
	
 	@RequestMapping(value="/validateAddress/", method=RequestMethod.POST)
	public Boolean validateAddress(@RequestBody Address address) {
		logger.debug(String.format("validate address(%s)", address.toString()));
		try {
			Thread.sleep((long)Math.random()*1000);
		} catch (Exception e) {
			logger.error(String.format("validate address(%s) failed", address.toString()));
		}
		return true;
	}
	
	@RequestMapping("/customers/pesel/{pesel}")
	public Customer findByPesel(@PathVariable("pesel") String pesel) {
		logger.info(String.format("Customer.findByPesel(%s)", pesel));
		Customer c = customers.stream().filter(it -> it.getPesel().equals(pesel)).findFirst().get();
		logger.info(String.format("Customer.findByPesel: %s", c));
		return c;
	}
	
	@RequestMapping("/customers")
	public List<Customer> findAll() {
		logger.info("Customer.findAll()");
		logger.info(String.format("Customer.findAll: %s", customers));
		return customers;
	}
	
	@RequestMapping("/customers/{id}")
	public Customer findById(@PathVariable("id") Integer id) {
		logger.info(String.format("Customer.findById(%s)", id));
		Customer customer = customers.stream().filter(it -> it.getId().intValue()==id.intValue()).findFirst().get();
		List<Account> accounts =  commercialAutoClient.getAccounts(id);
		//List<Account> ret_accounts = new ArrayList<Account>();
		//List<Account> account2s = this.restTemplate.getForObject("http://commercialAuto-service/accounts/customer/1",ret_accounts.getClass());
		customer.setAccounts(accounts);
		logger.info(String.format("Customer.findById: %s", customer));
		return customer;
	}
	
	@RequestMapping("/customers2/{id}")
	public Customer findById2(@PathVariable("id") Integer id) {
		logger.info(String.format("Customer.findById(%s)", id));
		Customer customer = customers.stream().filter(it -> it.getId().intValue()==id.intValue()).findFirst().get();
		//List<Account> accounts =  commercialAutoClient.getAccounts(id);
		List<Account> ret_accounts = new ArrayList<Account>();
		List<Account> account2s = this.restTemplate.getForObject("http://commercialAuto-service/accounts/customer/2",ret_accounts.getClass());
		customer.setAccounts(account2s);
		logger.info(String.format("Customer.findById: %s", customer));
		return customer;
	}
	
}
