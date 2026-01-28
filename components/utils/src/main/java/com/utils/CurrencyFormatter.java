package com.utils;

public class CurrencyFormatter {

    public String format(Double input){

        final double million = 1_000_000.0;
        final double billion = 1_000_000_000.0;
        final double trillion = 1_000_000_000_000.0;
        final double k = 1_000.0;

        String output;

        if(Math.abs(input) >= trillion){
            output = String.format("%3.3f Tn",input/trillion);
        }else if(Math.abs(input) >= billion){
            output = String.format("%3.3f Bn", input/billion);
        }else if(Math.abs(input) >= million){
            output = String.format("%3.3f Mn", input/million);
        }else if(Math.abs(input) >= k){
            output = String.format("%3.3f K",input/k);
        }else{
            output = String.format("%3.3f", input);
        }
        return output;
    }
}
