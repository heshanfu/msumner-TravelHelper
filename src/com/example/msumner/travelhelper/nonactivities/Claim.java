package com.example.msumner.travelhelper.nonactivities;

import java.util.ArrayList;

import com.example.msumner.travelhelper.AddClaim;
import com.example.msumner.travelhelper.nonactivities.Claim.ClaimStatus;

public class Claim {
	private String name;
	private String startDate;
	private String endDate;
	private ClaimStatus status;
	private ArrayList<Claim> claimItems;
	
	public Claim(String name, String startDate, String endDate, ClaimStatus status) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.claimItems = new ArrayList<Claim>();
	}
	
	public String getName() {
		return name;
	}
	
	//http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
	public enum ClaimStatus {
		Submitted,
		In_Progress,
		Returned,
		Approved;
	}

	public ClaimStatus getStatus() {
		return status;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
}
