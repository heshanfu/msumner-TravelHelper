package com.example.msumner.travelhelper.nonactivities;

import android.app.Application;
import android.content.Context;

public class ExpenseClaimNeeded extends Application {
	transient private static ExpenseController expensecontroller;

	public static ExpenseController getExpenseController(){
		if (expensecontroller == null){
			expensecontroller = new ExpenseController();
		}
		return expensecontroller;
	}
	
	public static ExpenseController getExpenseController(Context context) {
		if(expensecontroller == null) 
			expensecontroller = new ExpenseController(context);
		return expensecontroller;
	}

	transient private static ClaimController claimcontroller;

	public static ClaimController getClaimController(){
		if (claimcontroller == null){
			claimcontroller = new ClaimController();
		}
		return claimcontroller;
	}
	
	public static ClaimController getClaimController(Context context) {
		if(claimcontroller == null) 
			claimcontroller = new ClaimController(context);
		return claimcontroller;
	}

}

