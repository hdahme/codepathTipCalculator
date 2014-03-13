package com.codepath.example.tipcalculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText preTipAmountField = (EditText)findViewById(R.id.tfPreTipAmount);
		final EditText splitOverField = (EditText)findViewById(R.id.tfSplitOver);
		final EditText customTipPercentageField = (EditText)findViewById(R.id.tfCustomAmount);
		final Spinner tipPercentageSpinner = (Spinner)findViewById(R.id.spTipPercentage);
		final EditText totalAmountField = (EditText)findViewById(R.id.tfTotalAmount);
		
		final TextListener tl = new TextListener(preTipAmountField, splitOverField, 
				customTipPercentageField, tipPercentageSpinner, totalAmountField);
		
		preTipAmountField.addTextChangedListener(tl);
		splitOverField.addTextChangedListener(tl);
		customTipPercentageField.addTextChangedListener(tl);
		
		tipPercentageSpinner.setOnItemSelectedListener(new OnItemSelectedListener () {
			public void onNothingSelected(AdapterView<?> arg0) {}
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				totalAmountField.setText(tl.recalculateTip(preTipAmountField.getText().toString(), 
						splitOverField.getText().toString(), 
						tl.getTipPercentage(tipPercentageSpinner, customTipPercentageField)));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
