package com.utils;

import org.apache.commons.lang3.StringUtils;

public class StringEscaper {


    public static String escapeQuotes(String input){
        return input.replaceAll("'", "''");
    }

    public static String escapeAccents(String input){
       return StringUtils.stripAccents(input);
    }
}
