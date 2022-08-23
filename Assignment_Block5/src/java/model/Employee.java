/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Employee {

    private int id;
    private String name;
    private String room;
    private ArrayList<TimeSheet> timesheets = new ArrayList<>();
    private ArrayList<Leave> leaves = new ArrayList<>();

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public ArrayList<Leave> getLeaves() {
        return leaves;
    }

    public int getTotalLeaves() {
        int sum = 0;
        for (Leave leave : leaves) {
            sum += leave.getTotalDays();
        }
        return sum;
    }

    public void setLeaves(ArrayList<Leave> leaves) {
        this.leaves = leaves;
    }

    public int getNumberOfWorkingDays() {
        return timesheets.size();
    }

    public float getNumberOfWorkingHours() {
        float sum = 0;
        for (TimeSheet timesheet : timesheets) {
            sum += timesheet.getWorkingHours();
        }
        return sum;
    }

    public ArrayList<TimeSheet> getTimesheets() {
        return timesheets;
    }

    public void setTimesheets(ArrayList<TimeSheet> timesheets) {
        this.timesheets = timesheets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
