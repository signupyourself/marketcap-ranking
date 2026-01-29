package com.collector.parsers;

import java.util.ArrayList;
import java.util.List;
import com.collector.models.CompanyDao;


public class CompanyParser{

    public List<CompanyDao> findDetails(String html){

        String table=html.split("<table")[1]
                .split("</table>")[0];

        String body = table.split("<tbody")[1]
                .split("</tbody>")[0];


        String[] productBlocks = body.split("<tr>");
        List<CompanyDao> companyList = new ArrayList<>();
        int index=1;

        while (index < productBlocks.length){
            CompanyDao companyDao = new CompanyDao();
            companyDao.setName(findCompany(productBlocks[index]));
            companyDao.setCountry(findCountry(productBlocks[index]));
            companyDao.setMktCap(Double.parseDouble(findMarketCap(productBlocks[index])));
            companyDao.setUrl(findDetailsPage(productBlocks[index]));
            companyList.add(companyDao);
            index+=1;
        }
        return companyList;
    }

    private String findCompany(String body){
        return body.split("><div class=\"company-name\">")[1]
                .split("</div>")[0];
    }

    private String findMarketCap(String body){
        return body.split("data-sort=\"")[2]
                .split("\"")[0];

    }

    private String findDetailsPage(String body){
        return body.split("<div class=\"name-div\">")[1]
                .split("\">")[0]
                .split("<a href=\"")[1]
                .split("marketcap")[0];
    }

    private String findCountry(String body){
        return body.split(" <span class=\"responsive-hidden\">")[1]
                .split("</span>")[0];
    }

    public Double findRevenue(String html){
        String intermediate = html.split("class=\"background-ya\">")[1];
        String number = intermediate.split(" ")[0];
        String base = intermediate.split(" ")[1].split(" ")[0];
        number = number.replace("$", "");
        double revenue = Double.parseDouble(number);
        if(base.equals("Billion")){
            revenue = revenue * 1_000_000_000.0;
        }else if(base.equals("Million")){
            revenue = revenue * 1_000_000;
        }
        return revenue;
    }

    public Double findEarnings(String html){
        String intermediate = html.split("class=\"background-ya\">")[1];
        String number = intermediate.split(" ")[0];
        String base = intermediate.split(" ")[1].split(" ")[0];
        number = number.replace("$", "");
        double revenue = Double.parseDouble(number);
        if(base.equals("Billion")){
            revenue = revenue * 1_000_000_000.0;
        }else if(base.equals("Million")){
            revenue = revenue * 1_000_000;
        }
        return revenue;
    }

    public String findDescription(String html){
        try {
            String intermediate = html.split("class=\"col-lg-4 company-description\">")[1]
                    .split("</div>")[0];
            intermediate = intermediate.replaceAll("<[^>]*>", "").trim();
            return intermediate;
        }
        catch (Exception e){
            return "No description";
        }
    }

    public String findImageUrl(String html){
        return html.split("class=\"company-profile-logo\"")[1]
                .split("src=\"")[1]
                .split("\"")[0];
    }

}