package pl.piomin.microservices.account.model;

public class CalineInfo {
	private String policy_number;
	private Address address;

	public String getPolicy_number() {
		return policy_number;
	}

	public void setPolicy_number(String policy_number) {
		this.policy_number = policy_number;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CalineInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CalineInfo(String policy_number, Address address) {
		super();
		this.policy_number = policy_number;
		this.address = address;
	}

	@Override
	public String toString() {
		return "CalineInfo [policy_number=" + policy_number + ", address=" + address + "]";
	}

}
