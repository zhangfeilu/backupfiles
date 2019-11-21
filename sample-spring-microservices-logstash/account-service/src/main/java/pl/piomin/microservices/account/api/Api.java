package pl.piomin.microservices.account.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.microservices.account.model.Account;
import pl.piomin.microservices.account.model.Address;
import pl.piomin.microservices.account.model.CalineInfo;

@RestController
public class Api {

	private List<Account> accounts;
	
	protected static Logger logger = LoggerFactory.getLogger(Api.class.getName());
	
	@Autowired
	private AddressValidateClient addressValidateClient;
	
	public Api() {
		accounts = new ArrayList<>();
		accounts.add(new Account(1, 1, "111111"));
		accounts.add(new Account(2, 2, "222222"));
		accounts.add(new Account(3, 3, "333333"));
		accounts.add(new Account(4, 4, "444444"));
		accounts.add(new Account(5, 1, "555555"));
		accounts.add(new Account(6, 2, "666666"));
		accounts.add(new Account(7, 2, "777777"));
	}
	
	@RequestMapping(value="/ca/locationInfo",method=RequestMethod.POST)
	public Boolean saveCaLocationInfo(@RequestBody CalineInfo calineInfo) {
		logger.info(String.format("save calines address info policy id: (%s)", calineInfo.getPolicy_number()));
		Boolean result = addressValidateClient.validateAddress(calineInfo.getAddress());
		//logger.info(String.format("Account.findByNumber: %s", a));
		return true;
	}
	
	@RequestMapping("/ca/{number}")
	public Account findByNumber(@PathVariable("number") String number) {
		logger.info(String.format("ca.findByNumber(%s)", number));
		Account a = accounts.stream().filter(it -> it.getNumber().equals(number)).findFirst().get();
		logger.info(String.format("Account.findByNumber: %s", a));
		return a;
	}
	
	@RequestMapping("/ca/user/{customer}")
	public List<Account> findByCustomer(@PathVariable("customer") Integer customerId) {
		logger.info(String.format("ca.findByUser(%s)", customerId));
		List<Account> as = accounts.stream().filter(it -> it.getCustomerId().intValue()==customerId.intValue()).collect(Collectors.toList());
		logger.info(String.format("ac.findByUser: %s", as));
		return as;
	}
	
	@RequestMapping("/users")
	public List<Account> findAll() {
		logger.info("ca.findAll()");
		logger.info(String.format("ca.findAll: %s", accounts));
		return accounts;
	}
	
}
