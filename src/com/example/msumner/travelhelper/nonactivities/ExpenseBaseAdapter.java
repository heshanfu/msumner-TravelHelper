package com.example.msumner.travelhelper.nonactivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.msumner.travelhelper.R;

public class ExpenseBaseAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	
	public ExpenseBaseAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		ExpenseController expenseController = ExpenseClaimNeeded.getExpenseController();
		return expenseController.getNumberOfExpenses();
	}

	@Override
	public Object getItem(int position) {
		ExpenseController expenseController = ExpenseClaimNeeded.getExpenseController();
		return expenseController.getExpense(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ExpenseController expenseController = ExpenseClaimNeeded.getExpenseController();
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.expense_row, null);
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView.findViewById(R.id.textViewName);
			holder.txtDescription = (TextView) convertView.findViewById(R.id.textViewDescription);
			holder.txtCurrency = (TextView) convertView.findViewById(R.id.textViewCurrency);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Expense entry = expenseController.getExpense(position);
		holder.txtName.setText(entry.getName());
		holder.txtDescription.setText(entry.getDescription());
		holder.txtCurrency.setText(entry.getCurrency());
		return convertView;
	}
	
	static class ViewHolder {
		TextView txtName;
		TextView txtDescription;
		TextView txtCurrency;
	}
}
