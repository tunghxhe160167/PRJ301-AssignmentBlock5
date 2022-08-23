/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Helper.DateTimeHelper;
import dal.EmployeeDBContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import model.Employee;

/**
 *
 * @author Ngo Tung Son
 */
public class NewClass {
    public static void main(String[] args)
    {
         int month = 9;
        Date today = new Date();
        today = DateTimeHelper.removeTime(today);
        today = DateTimeHelper.setMonthValue(today, month );
        System.out.println(today);
        int dayOfMonth = DateTimeHelper.getDayOfMonth(today);
        Date begin = DateTimeHelper.addDays(today, -1*(dayOfMonth-1));
        Date end = DateTimeHelper.addDays(DateTimeHelper.addMonths(begin, 1),-1);
        System.out.println(begin);
        System.out.println(end);
    }
}
