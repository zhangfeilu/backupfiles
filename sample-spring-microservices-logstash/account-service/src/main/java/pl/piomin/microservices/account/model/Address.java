package pl.piomin.microservices.account.model;

public class Address {

	private String address1;
	private String state;
	private String county;
	private String zip_code;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String address1, String state, String county, String zip_code) {
		super();
		this.address1 = address1;
		this.state = state;
		this.county = county;
		this.zip_code = zip_code;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	@Override
	public String toString() {
		return "Address [address1=" + address1 + ", state=" + state + ", county=" + county + ", zip_code=" + zip_code
				+ "]";
	}

}
