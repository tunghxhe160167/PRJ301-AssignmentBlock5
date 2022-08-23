/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Helper.DateTimeHelper;
import dal.EmployeeDBContext;
import dal.FestivalDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import model.Employee;
import model.Festival;
import model.Leave;

/**
 *
 * @author ADMIN
 */
public class TimeSheetReportController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/BangChamCong.jsp").forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int month = Integer.parseInt(request.getParameter("month"));
        int count = 0;
        Date today = new Date();
        today = DateTimeHelper.removeTime(today);
        today = DateTimeHelper.setMonthValue(today, month);
        int dayOfMonth = DateTimeHelper.getDayOfMonth(today);
        Date begin = DateTimeHelper.addDays(today, -1 * (dayOfMonth - 1));
        Date end = DateTimeHelper.addDays(DateTimeHelper.addMonths(begin, 1), -1);
        ArrayList<Date> dates = DateTimeHelper.getDates(begin, end);
        EmployeeDBContext db = new EmployeeDBContext();
        FestivalDBContext fdb = new FestivalDBContext();
        ArrayList<Employee> employees = db.getEmployees(begin, DateTimeHelper.addDays(end, 1));
        ArrayList<Festival> fes = fdb.getFestival(DateTimeHelper.getMonthValue(begin));
        request.setAttribute("dates", dates);
        request.setAttribute("fes", fes);
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("view/BangChamCong.jsp").forward(request, response);
        
    }
//    public static void main(String[] args) {
//          int month = 9;
//        int count = 0;
//        Date today = new Date();
//        today = DateTimeHelper.removeTime(today);
//        today = DateTimeHelper.setMonthValue(today, month);
//        int dayOfMonth = DateTimeHelper.getDayOfMonth(today);
//        Date begin = DateTimeHelper.addDays(today, -1 * (dayOfMonth - 1));
//        Date end = DateTimeHelper.addDays(DateTimeHelper.addMonths(begin, 1), -1);
//        ArrayList<Date> dates = DateTimeHelper.getDates(begin, end);
//        EmployeeDBContext db = new EmployeeDBContext();
//        FestivalDBContext fdb = new FestivalDBContext();
//        ArrayList<Employee> employees = db.getEmployees(begin, DateTimeHelper.addDays(end, 1));
//        ArrayList<Festival> fes = fdb.getFestival(DateTimeHelper.getMonthValue(begin));
//        
//        System.out.println(employees.get(1).getLeaves().get(0).getTo() );
//    }
}
