<%-- 
    Document   : BangChamCong
    Created on : Aug 22, 2022, 9:21:02 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .header {
                padding: 60px;
                text-align: center;
                font-size: 30px;
            }
            span{

                color: red;
            }
        </style>
    </head>
    <body>
        <jsp:useBean id="dt" class="Helper.DateTimeHelper"/>
        <div style="text-align: center">
            <span  >CÔNG TY TNHH CƠ ĐIỆN LẠNH QUANG THẮNG</span>
            <img  src="data:image/png;base64,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"  style="width:auto;">
            <span >  QUANG THANG REFRIGERATION COMPANY LIMITED </span>
        </div>  
        <div class="header">
            <img src="http://quangthang.com.vn/wp-content/uploads/2020/04/cong-ty-quang-thang-1-Copy-1-1534x479.jpg">

            <h2 >Bảng chấm công tháng</h2>
            <form action="report" method="POST">
                <select name="month">
                    <option label="Tháng 1">1</option>
                    <option label="Tháng 2">2</option>
                    <option label="Tháng 3">3</option>
                    <option label="Tháng 4">4</option>
                    <option label="Tháng 5">5</option>
                    <option label="Tháng 6">6</option>
                    <option label="Tháng 7">7</option>
                    <option label="Tháng 8">8</option>
                    <option label="Tháng 9">9</option>
                    <option label="Tháng 10">10</option>
                    <option label="Tháng 11">11</option>
                    <option label="Tháng 12">12</option>
                    <input type="submit" value="search" />
                </select>
            </form>

        </div>
        <table border="1px">
            <tr>
                <td>Name</td>
                <td>Room</td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td
                        <c:forEach items="${requestScope.fes}" var="fes">
                            <c:if test="${fes.from <= d and fes.to >= d }">
                                style="background-color: gold;"
                            </c:if>
                            <c:if test="${dt.getDayOfWeek(d) eq 7}">
                                style="background-color: yellow;"
                            </c:if>

                        </c:forEach>
                        >

                        <fmt:formatDate pattern = "dd" 
                                        value = "${d}" /> <br/>
                    </td>
                </c:forEach>
                <td>Total of working days</td>
                <td>Total of working hours</td>
                <td>Total of Late(min)</td>
                <td>Total overtime</td>
                <td>Total overtime on holiday</td>
            </tr>
            <c:forEach items="${requestScope.employees}" var="e">
                <tr>
                    <td>${e.name}</td>
                    <td>${e.room}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td 
                            <c:forEach items="${requestScope.fes}" var="fes">
                                <c:if test="${fes.from <= d and fes.to >= d }">
                                    style="background-color: gold;"
                                </c:if>
                                <c:if test="${dt.getDayOfWeek(d) eq 7}">
                                    style="background-color: yellow;"
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${e.timesheets}" var="t">
                                <c:if test="${t.cidate eq d and dt.getLateTime(t.checkin)>0}">
                                    style="background-color: deeppink;"
                                </c:if>
                            </c:forEach>
                                     <c:forEach items="${e.timesheets}" var="t">
                                <c:if test="${t.cidate eq d and t.getWorkingHours()>9}">
                                    style="background-color: greenyellow;"
                                </c:if>
                            </c:forEach>
                            >

                            <c:forEach items="${e.timesheets}" var="t">
                                <c:if test="${t.cidate eq d}">
                                    ${t.getWorkingHours()}
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${e.leaves}" var="l">
                                <c:if test="${l.from <= d and l.to >=d}">
                                    ${l.getReason()}
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                    <td>${e.getNumberOfWorkingDays()}</td>
                    <td>${e.getNumberOfWorkingHours()}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
