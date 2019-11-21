package pl.piomin.microservices.account.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.piomin.microservices.account.model.Address;

	@FeignClient("validation-service")
	public interface AddressValidateClient {

	    @RequestMapping(method = RequestMethod.GET, value = "/validateAddress/")
	    Boolean validateAddress(@RequestBody Address address);
	    
	}

