package com.healt_cost_prediction;

import java.time.LocalDate;

public class DateMng {
public static void main(String arg[]){
    LocalDate toDay=LocalDate.now();
    LocalDate otherDate=LocalDate.of(2000, 01, 01);
    var validate=toDay.isAfter(otherDate);
    System.out.println(validate);
}
}
