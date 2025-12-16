package main.Interfaces;

import main.PersistenceModels.Contract;
import main.PersistenceModels.Employee;
import main.Utils.ValidationUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface IEmployee {
    public double getSalary();
    public void setSalary(double salary);
    public int getItemsSold();
    public void setItemsSold(int itemsSold);
    public Employee getManager();
    public HashSet<Employee> getSubordinates();
    public void deleteEmployee();
    public void assignManager(Employee manager);
    public void addSubordinate(Employee subordinate);
    public void removeSubordinate(Employee subordinate);
    public void addNewEmployee();
    public void checkEmployeeList();
    public void viewDashboard();
    public void checkFinancialReport();
}
