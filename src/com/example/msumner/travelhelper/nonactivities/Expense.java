package com.example.msumner.travelhelper.nonactivities;

public class Expense {
	private String name;
	private String amountSpent;
	private String unitofCurrency;
	
	public Expense(String name, String category, String description, String date, 
			String amountSpent, String unitofCurrency){
		this.name = name;
		this.amountSpent = amountSpent;
		this.unitofCurrency = unitofCurrency;
	}

	public String getName() {
		return name;
	}
	
	public String getCurrency() {
		return unitofCurrency;
	}


	//supposed to be amountSpent not description
	public String getDescription() {
		return amountSpent;
	}


}
	
	



