package com.projectt.projectts.innerdomain;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnerConfigData {
	
		private double Amount;
    
	    private String type;

		public double getAmount() {
			return Amount;
		}

		public void setAmount(double amount) {
			Amount = amount;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}  
	    
	    
}
