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
import model.Festival;
import model.Leave;

/**
 *
 * @author ADMIN
 */
public class FestivalDBContext extends DBContext {
    public ArrayList<Festival> getFestival(int month){
    ArrayList<Festival> fes = new ArrayList<>();
      try {
            String sql ="select [from], [to], [name] from Festival where MONTH([from]) <=? and MONTH([to])>=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, month);
            stm.setInt(2, month);
            ResultSet rs = stm.executeQuery();
            Festival f = new Festival();
            while(rs.next()){
                f.setName(rs.getString("name"));
                f.setFrom(DateTimeHelper.getDateFrom(rs.getTimestamp("from")));
                f.setTo(DateTimeHelper.getDateFrom(rs.getTimestamp("to")));
                fes.add(f);
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return fes;
}
       public static void main(String[] args)   {
       FestivalDBContext fdb = new FestivalDBContext();
       ArrayList<Festival> fes = fdb.getFestival(9);
           System.out.println(fes.get(0).getTo());
    }
}
