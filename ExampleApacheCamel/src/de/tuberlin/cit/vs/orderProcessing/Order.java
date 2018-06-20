package de.tuberlin.cit.vs.orderProcessing;

public class Order {

	private static int nextId = 0;
	private String firstname;
	private String lastname;
	private int numSurf;
	private int numDiv;
	private int custId;
	
	public Order(String firstname, String lastname, int numSurf, int numDiv) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.numSurf = numSurf;
		this.numDiv = numDiv;
		this.custId = nextId++;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getNumSurf() {
		return numSurf;
	}

	public void setNumSurf(int numSurf) {
		this.numSurf = numSurf;
	}

	public int getNumDiv() {
		return numDiv;
	}

	public void setNumDiv(int numDiv) {
		this.numDiv = numDiv;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}
	
	public String toString() {
		return "Order{"
				+ "firstname="+ this.firstname
				+ ", lastname=" + this.lastname
				+ ", numSurf=" + this.numSurf
				+ ", numDiv=" + this.numDiv
				+ ". custId=" + this.custId
				+ "}";
	}
}
