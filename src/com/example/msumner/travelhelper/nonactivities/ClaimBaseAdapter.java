package com.example.msumner.travelhelper.nonactivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.msumner.travelhelper.R;

public class ClaimBaseAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	
	public ClaimBaseAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		ClaimController claimController = ExpenseClaimNeeded.getClaimController();
		return claimController.getNumberOfClaims();
	}

	@Override
	public Object getItem(int position) {
		ClaimController claimController = ExpenseClaimNeeded.getClaimController();
		return claimController.getClaim(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ClaimController claimController = ExpenseClaimNeeded.getClaimController();
		ViewHolder holder;
		//row code and status code from https://github.com/nklose/CMPUT301/tree/master/NoNameProject
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.claim_row, null);
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView.findViewById(R.id.textViewName);
			holder.txtStatus = (TextView) convertView.findViewById(R.id.textViewStatus);
			holder.txtStart = (TextView) convertView.findViewById(R.id.textViewStart);
			holder.txtEnd = (TextView) convertView.findViewById(R.id.textViewEnd);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String statusText;
		Claim entry = claimController.getClaim(position);
		Claim.ClaimStatus status = entry.getStatus();
		holder.txtName.setText(entry.getName());
		if (status == Claim.ClaimStatus.Approved){
			statusText = "Approved";
		}
		else if (status == Claim.ClaimStatus.In_Progress){
			statusText = "In Progress";
		}
		else if (status == Claim.ClaimStatus.Returned){
			statusText = "Returned";
		}
		else if (status == Claim.ClaimStatus.Submitted){
			statusText = "Submitted";
		}
		else{
			statusText = "In Progress";
		}	

		holder.txtStatus.setText(statusText);
		holder.txtStart.setText(entry.getStartDate());
		holder.txtEnd.setText(entry.getEndDate());
		return convertView;
	}
	
	static class ViewHolder {
		TextView txtName;
		TextView txtStatus;
		TextView txtStart;
		TextView txtEnd;
	}
}

