package com.projectt.projectts.innerdomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnerPaymentData {
	
	private double Amount;
	private String requestId;
	private String clientId;
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
	
}
