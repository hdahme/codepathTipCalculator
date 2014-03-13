package com.codepath.example.tipcalculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Spinner;

public class TextListener implements TextWatcher{
	
	final EditText preTipAmountField;
	final EditText splitOverField;
	final EditText customTipPercentageField;
	final Spinner tipPercentageSpinner;
	final EditText totalAmountField;
	
	

	public TextListener(EditText preTipAmountField, EditText splitOverField,
			EditText customTipPercentageField, Spinner tipPercentageSpinner,
			EditText totalAmountField) {
		super();
		this.preTipAmountField = preTipAmountField;
		this.splitOverField = splitOverField;
		this.customTipPercentageField = customTipPercentageField;
		this.tipPercentageSpinner = tipPercentageSpinner;
		this.totalAmountField = totalAmountField;
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (s.length() < 1) {
			return;
		}
		this.totalAmountField.setText(recalculateTip(this.preTipAmountField.getText().toString(), 
				this.splitOverField.getText().toString(), 
				getTipPercentage(this.tipPercentageSpinner, this.customTipPercentageField)));
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}
	
	public String recalculateTip(String preTipAmount, String splitBy, double tipPercentage) {
		NumberFormat currentFormatter = new DecimalFormat("#0.00");
		if (preTipAmount.length() < 1 || splitBy.length() < 1) {
			return "0.00";
		}
		return currentFormatter.format(Double.parseDouble(preTipAmount) / Integer.parseInt(splitBy) 
				* (tipPercentage + 1));
	}
	
	public double getTipPercentage(Spinner tipPercentage, EditText customPercentage) {
		String percentage = tipPercentage.getSelectedItem().toString();
		if (percentage.equals("Custom")) {
			percentage = customPercentage.getText().toString();
		} else {
			percentage = percentage.substring(0, percentage.length() - 1);
		}
		return Double.parseDouble(percentage)/100;
	}

}
