/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class DateTimeHelper {
    public static int getLateTime(Date datetime) {
        LocalDateTime checkin = datetime.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime();
        LocalDateTime localDate = checkin;
        localDate = localDate.withHour(8);
        localDate = localDate.withMinute(0);
        if(checkin.getHour() == localDate.getHour() && checkin.getMinute() > localDate.getMinute() ){
            return  checkin.getMinute() - localDate.getMinute();
        }
        return 0;
    }    
    public static Date setMonthValue(Date datetime, int month) {
        LocalDate localDate = datetime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate date = localDate.withMonth(month);
        return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }  
    public static int getMonthValue(Date datetime) {
        LocalDate localDate = datetime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate.getMonthValue();
    }  
    public static int getDayOfMonth(Date datetime) {
        LocalDate localDate = datetime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate.getDayOfMonth();
    }
    
    public static Timestamp getTimeStamp(Date date)
    {
        return new Timestamp(date.getTime());
    }

    public static int getDayOfWeek(Date datetime) {
        LocalDate localDate = datetime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate.getDayOfWeek().getValue();
    }

    public static Date addDays(Date d, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, days);
        Date newdate = c.getTime();
        return newdate;
    }
    
    public static Date addMonths(Date d, int months) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MONTH, months);
        Date newdate = c.getTime();
        return newdate;
    }
    public static Date removeTime(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date newdate = c.getTime();
        return newdate;
    }
    
    public static ArrayList<Date> getDates(Date from, Date to)
    {
        ArrayList<Date> dates = new ArrayList<>();
        int count = 0;
        while(true)
        {
            
            Date item = addDays(from, count);
            dates.add(item);
            count++;
            if(item.getTime() == to.getTime())
                break;
        }
        return dates;
    }
    
    public static Date getDateFrom(Timestamp ts)
    {
        return new Date(ts.getTime());
    }
}
