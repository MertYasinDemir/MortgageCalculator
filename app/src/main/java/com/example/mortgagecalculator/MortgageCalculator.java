package com.example.mortgagecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


import java.text.NumberFormat;

public class MortgageCalculator extends Activity {
    private double purchasePrice=0.0;
    private double downPaymentAmount=0.0;
    private double interestRate=0.0;
    private double tenYears;
    private double twentyYears;
    private double thirtyYears;
    private double monthlyPayment;
    private EditText loanAmountGirdi;
    private TextView tenYearsSonuc;
    private TextView twentyYearsSonuc;
    private TextView thirtyYearsSonuc;
    private TextView yearsView;
    private TextView monthlyPaymentSonuc;
    private int years=1;

    private static final NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText purchasePriceGirdi=(EditText)findViewById(R.id.PurchasePriceGirdi);
        EditText downPaymentAmountGirdi=(EditText)findViewById(R.id.DPAGirdi);
        EditText interestRateGirdi=(EditText)findViewById(R.id.InterestRateGirdi);
        loanAmountGirdi=(EditText)findViewById(R.id.LoanAmountGirdi);
        loanAmountGirdi.setEnabled(false);
        tenYearsSonuc=(TextView)findViewById(R.id.TenYearsSonuc);
        twentyYearsSonuc=(TextView)findViewById(R.id.TwentyYearsSonuc);
        thirtyYearsSonuc=(TextView)findViewById(R.id.ThirtyYearsSonuc);
        monthlyPaymentSonuc=(TextView)findViewById(R.id.MPSonuc);
        yearsView=(TextView)findViewById(R.id.Years);

        purchasePriceGirdi.addTextChangedListener(purchasePriceDegerIzleyici);
        downPaymentAmountGirdi.addTextChangedListener(downPaymentDegerIzleyici);
        interestRateGirdi.addTextChangedListener(interestRateDegerIzleyici);

        SeekBar seekBar=findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);



    }

    private void calculations(){
        yearsView.setText(String.valueOf(years));

        double loanAmount=purchasePrice-downPaymentAmount;
        loanAmountGirdi.setText(currencyInstance.format(loanAmount));

        double rateInMonths=interestRate/12;
        double loanTermInMonths=years*12;


        tenYears=(loanAmount * rateInMonths) / (1 - Math.pow(1 + rateInMonths, -120));
        tenYearsSonuc.setText(currencyInstance.format(tenYears));

        twentyYears=(loanAmount * rateInMonths) / (1 - Math.pow(1 + rateInMonths, -240));
        twentyYearsSonuc.setText(currencyInstance.format(twentyYears));

        thirtyYears=(loanAmount * rateInMonths) / (1 - Math.pow(1 + rateInMonths, -360));
        thirtyYearsSonuc.setText(currencyInstance.format(thirtyYears));

        monthlyPayment=(loanAmount * rateInMonths) / (1 - Math.pow(1 + rateInMonths, (-1)*loanTermInMonths));
        monthlyPaymentSonuc.setText(currencyInstance.format(monthlyPayment));



    }

    private final SeekBar.OnSeekBarChangeListener seekBarChangeListener=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            years=progress;
            calculations();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher purchasePriceDegerIzleyici=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                if (s.toString().length() > 0) {
                    purchasePrice = Double.parseDouble(s.toString());
                    calculations();
                }
            }catch (NumberFormatException ex){
                purchasePrice=0.0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final TextWatcher downPaymentDegerIzleyici=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                if (s.toString().length() > 0) {
                    downPaymentAmount = Double.parseDouble(s.toString());
                    calculations();
                }
            }catch(NumberFormatException ex){
                downPaymentAmount=0.0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    };

    private final TextWatcher interestRateDegerIzleyici=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                if (s.toString().length() > 0) {
                    interestRate = Double.parseDouble(s.toString()) / 100.0;
                    calculations();
                }
            }catch (NumberFormatException ex){
                interestRate=0.0;
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}
