package com.example.msumner.travelhelper.nonactivities;

import java.util.ArrayList;

import android.content.Context;

public class ClaimController {
	protected ArrayList<Claim> claims;
	
	
	public ClaimController() {
		claims = new ArrayList<Claim>();
	}

	public Claim getClaim(int position) {
		return claims.get(position);
	}
	
	public int getNumberOfClaims(){
		return claims.size();
	}
	
	public ClaimController(Context context) {
		super();
		readClaimFile(context);
	}
	
	public void addClaim(Context context, Claim newClaim) {
		claims.add(newClaim);
		ExpenseIOAdapter.appendToLogClaim(context, newClaim);
	}
	
	
	public void deleteClaim(Context context, int index) {
		claims.remove(index);
		ExpenseIOAdapter.overwriteLogClaim(context, claims);
	}
	

	public void readClaimFile(Context context){
		claims = ExpenseIOAdapter.readLogClaim(context);
	}
	
}
