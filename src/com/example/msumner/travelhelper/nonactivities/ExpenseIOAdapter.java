package com.example.msumner.travelhelper.nonactivities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class ExpenseIOAdapter {
	//Location of the stored expenses
	public final static String ExpenseFileName = "Expenses.log";
	public final static String ClaimFileName = "Claims.log";
	/**
	 * Reads in  tasks from file and returns them.
	 * @param context Current context.
	 * @return ArrayList of Tasks that were store
	 *  in the  task file
	 */
	public static ArrayList<Expense> readLog(Context context){
		ArrayList<Expense> ExpensesFromFile = ExpensesFromFile(context);
		ArrayList<Expense> Expenses = new ArrayList<Expense>();
		
		for (int i = 0; i < ExpensesFromFile.size()-1; i++){
			ExpensesFromFile.add(ExpensesFromFile.get(i));
			
		}
		return Expenses;
	}
	
	public static ArrayList<Claim> readLogClaim(Context context){
		ArrayList<Claim> ClaimsFromFile = ClaimsFromFile(context);
		ArrayList<Claim> Claims = new ArrayList<Claim>();
		
		for (int i = 0; i < ClaimsFromFile.size()-1; i++){
			ClaimsFromFile.add(ClaimsFromFile.get(i));
			
		}
		return Claims;
	}
	/**
	 * Reads a list of  tasks from the file and returns them.
	 * @param context Current context.
	 * @return ArrayList of Tasks that were stored in the  task file
	 */
	@SuppressWarnings("unchecked")
	private static ArrayList<Expense> ExpensesFromFile(Context context) {
		ArrayList<Expense> ExpensesFromFile = new ArrayList<Expense>();
		try {
			FileInputStream fis = context.openFileInput(ExpenseFileName);
			ObjectInputStream objectStream = new ObjectInputStream(fis);
			ExpensesFromFile = (ArrayList<Expense>) objectStream.readObject();
			objectStream.close();
		}
		catch (Exception e) {
			Log.e("msumner.travelhelper", "Unable to load entries.", e);
		}
		return ExpensesFromFile;
	}
	
	@SuppressWarnings("unchecked")
	private static ArrayList<Claim> ClaimsFromFile(Context context) {
		ArrayList<Claim> ClaimsFromFile = new ArrayList<Claim>();
		try {
			FileInputStream fis = context.openFileInput(ClaimFileName);
			ObjectInputStream objectStream = new ObjectInputStream(fis);
			ClaimsFromFile = (ArrayList<Claim>) objectStream.readObject();
			objectStream.close();
		}
		catch (Exception e) {
			Log.e("msumner.travelhelper", "Unable to load entries.", e);
		}
		return ClaimsFromFile;
	}
	/**
	 * Overwrites the  expense file with an input collection of expenses.
	 * @param context Current context. 
	 * @param tasks List of tasks to write into the  file.	
	 */
	public static void overwriteLog(Context context, ArrayList<Expense> expenses){
		try{
			FileOutputStream fos = context.openFileOutput(ExpenseFileName, 
					Context.MODE_PRIVATE);
			ObjectOutputStream objectStream = new ObjectOutputStream(fos);
			try{
				objectStream.writeObject(expenses);
			} finally {
				objectStream.close();
			}
		} catch(IOException e){
			Log.e("msumner.travelhelper", "Unable to save entries.", e);
		}
	}

	public static void overwriteLogClaim(Context context, ArrayList<Claim> claims){
		try{
			FileOutputStream fos = context.openFileOutput(ClaimFileName, 
					Context.MODE_PRIVATE);
			ObjectOutputStream objectStream = new ObjectOutputStream(fos);
			try{
				objectStream.writeObject(claims);
			} finally {
				objectStream.close();
			}
		} catch(IOException e){
			Log.e("msumner.travelhelper", "Unable to save entries.", e);
		}
	}
	/**
	 * Appends an expense to the  task file.
	 * @param context Current context.
	 * @param task The Task to append to the  task file.
	 */
	public static void appendToLog(Context context, Expense expense){
		try{
			FileOutputStream fos = context.openFileOutput(ExpenseFileName, 
					Context.MODE_APPEND);
			ObjectOutputStream objectStream = new ObjectOutputStream(fos);
			try{
				objectStream.writeObject(expense);
			} finally {
				objectStream.close();
			}
		} catch(IOException e){
			Log.e("msumner.travelhelper", "Unable to save entry.", e);
		}
	}

	public static void appendToLogClaim(Context context, Claim claim){
		try{
			FileOutputStream fos = context.openFileOutput(ClaimFileName, 
					Context.MODE_APPEND);
			ObjectOutputStream objectStream = new ObjectOutputStream(fos);
			try{
				objectStream.writeObject(claim);
			} finally {
				objectStream.close();
			}
		} catch(IOException e){
			Log.e("msumner.travelhelper", "Unable to save entry.", e);
		}
	}
}
