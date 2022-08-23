SELECT e.id,e.name,ISNULL(t.tid,-1) tid,t.checkin,t.checkout
                                        FROM Employee e
                                       	LEFT JOIN (SELECT * FROM Timesheet WHERE 
                                       	checkin >= '2022-09-01 00:00:00.000'
                                       	AND
                                      	checkin < '2022-10-01 00:00:00.000') t
                                      	ON e.id = t.eid

Select*from Festival

SELECT eid,[date],[hours] from OvertimeRegister 

SELECT * from Leave