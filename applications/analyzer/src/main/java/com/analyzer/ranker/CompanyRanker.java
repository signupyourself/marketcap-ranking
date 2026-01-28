package com.analyzer.ranker;

import com.db_support.models.Company;

import java.util.List;

public class CompanyRanker {
    public static void rank(List<Company> companies){
        int index = 0;
        while (index < companies.size()){
            companies.get(index).setMarketCapRanking(++index);
        }
    }
}
