package com.example.msumner.travelhelper.nonactivities;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;

import com.example.msumner.travelhelper.ExpenseClaim;

/**
 * Controller responsible for modifying and reading  tasks.
 */
public class ExpenseController{
	protected static ArrayList<Expense> expenses;

	
	// create an empty task controller
	public ExpenseController() {
		expenses = new ArrayList<Expense>();
	}

	public static Expense getExpense(int index){
		return expenses.get(index);
	}
	

	public int getNumberOfExpenses(){
		return expenses.size();
	}

/**
 * @param context Current context.
 * @param task New task to replace previous task with.
 * @param index Position index of task to replace.
 */
	
	// create an empty controller and read  tasks from file
	public ExpenseController(Context context) {
		super();
		readExpenseFile(context);
	}

	/**
	 * Adds a task to main memory and appends it to the  task file.
	 * @param context Current context.
	 * @param newTask The task to be added.
	 */
	public void addExpense(Context context, Expense newExpense) {
		expenses.add(newExpense);
		ExpenseIOAdapter.appendToLog(context, newExpense);

		
	}

	/**
	 * Deletes a specified task from main memory and the  task file.
	 * @param context Current context.
	 * @param index The position of the task to be deleted.
	 */
	public void deleteExpense(Context context, int index) {
		expenses.remove(index);
		ExpenseIOAdapter.overwriteLog(context, expenses);
	}
	
	/**
	 * Deletes a specified task from main memory and the  task file
	 * @param context Current context.
	 * @param task The task to be deleted.
	 */
	public void deleteExpense(Context context, Expense expense) {
		expenses.remove(expense);
		ExpenseIOAdapter.overwriteLog(context, expenses);
	}


	/**
	 * Reads all  tasks from the file into main memory.
	 * @param context Current context.
	 */
	public void readExpenseFile(Context context){
		expenses = ExpenseIOAdapter.readLog(context);
	}

	public static Object getExpenseList() {
		// TODO Auto-generated method stub
		return null;
	}


}
