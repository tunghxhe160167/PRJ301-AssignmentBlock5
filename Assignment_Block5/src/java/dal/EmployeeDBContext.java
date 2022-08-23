/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Helper.DateTimeHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.Leave;
import model.TimeSheet;

/**
 *
 * @author ADMIN
 */
public class EmployeeDBContext extends DBContext {
    public ArrayList<Leave> getLeaveRequest(int month){
        ArrayList<Leave> leaves = new ArrayList<>();
         try {
            String sql = "select eid,[from], [to], reason from Leave \n" 
                    + "where MONTH([from]) <=? and MONTH([to])>=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, month);
            stm.setInt(2, month);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Leave leave = new Leave();
                leave.setEid(rs.getInt("eid"));
                leave.setFrom(DateTimeHelper.getDateFrom(rs.getTimestamp("from")));
                leave.setTo(DateTimeHelper.getDateFrom(rs.getTimestamp("to")));
                leave.setReason(rs.getString("reason"));
                leaves.add(leave);
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
         return leaves;
    }
      public ArrayList<Employee> getEmployees(Date begin, Date end) {
          ArrayList<Leave> leaves = getLeaveRequest(DateTimeHelper.getMonthValue(begin));
          ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT e.id,e.name,e.room,ISNULL(t.tid,-1) tid,t.checkin,t.checkout\n"
                    + "                    FROM Employee e\n"
                    + "                    	LEFT JOIN (SELECT * FROM Timesheet WHERE \n"
                    + "                    	checkin >= ?\n"
                    + "                    	AND\n"
                    + "                    	checkin < ?) t \n"
                    + "                    	ON e.id = t.eid";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setTimestamp(1, DateTimeHelper.getTimeStamp(DateTimeHelper.removeTime(begin)));
            stm.setTimestamp(2, DateTimeHelper.getTimeStamp(DateTimeHelper.removeTime(end)));
            ResultSet rs = stm.executeQuery();
            Employee curEmp = new Employee();
            curEmp.setId(-1);
            while (rs.next()) {
                int eid = rs.getInt("id");
                if (eid != curEmp.getId()) {
                    curEmp = new Employee();
                    curEmp.setId(eid);
                    curEmp.setName(rs.getString("name"));
                    curEmp.setRoom(rs.getString("room"));
                    for(Leave l: leaves){
                        if(l.getEid() == curEmp.getId()){
                            curEmp.getLeaves().add(l);
                        }
                    }
                    employees.add(curEmp);
                }
                int tid = rs.getInt("tid");
                if (tid != -1) {
                    TimeSheet t = new TimeSheet();
                    t.setEmployee(curEmp);
                    t.setId(tid);
                    t.setCheckin(DateTimeHelper.getDateFrom(rs.getTimestamp("checkin")));
                    t.setCheckout(DateTimeHelper.getDateFrom(rs.getTimestamp("checkout")));
                    curEmp.getTimesheets().add(t);
                }
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }
       public static void main(String[] args)   {
          int month = 9;
        Date today = new Date();
        today = DateTimeHelper.removeTime(today);
        today = DateTimeHelper.setMonthValue(today, month);
        int dayOfMonth = DateTimeHelper.getDayOfMonth(today);
        Date begin = DateTimeHelper.addDays(today, -1*(dayOfMonth-1));
        Date end = DateTimeHelper.addDays(DateTimeHelper.addMonths(begin, 1),-1);
           EmployeeDBContext e = new EmployeeDBContext();
           ArrayList<Employee> f =  e.getEmployees(begin, end);
//           for(Employee g: f) {
//           for(Leave l: leaves){
//                        if(l.getEid() == g.getId()){
//                            g.getLeaves().add(l);
//                            System.out.println(l.getReason());
//                        }
//                    }
//           }
           System.out.println(f.get(1).getLeaves().size());
    }
    
}
