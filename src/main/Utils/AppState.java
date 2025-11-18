package main.Utils;

import main.Clothing.Hoodie;
import main.Clothing.Shirt;
import main.Clothing.Trousers;
import main.Footwear.Boot;
import main.Footwear.HeeledShoe;
import main.MembershipTiers.MembershipCard;
import main.Order.Order;
import main.Person.Contract;
import main.Person.Customer;
import main.Person.DebitCard;
import main.Person.Employee;

import java.io.Serializable;
import java.util.List;

public class AppState implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<Hoodie> hoodies;
    public List<Shirt> shirts;
    public List<Trousers> trousers;
    public List<Boot> boots;
    public List<HeeledShoe> heeledShoes;
    public List<MembershipCard> membershipCards;
    public List<Order> orders;
    public List<Contract> contracts;
    public List<Customer> customers;
    public List<DebitCard> debitCards;
    public List<Employee> employees;

    public static AppState fromStatics() {
        AppState s = new AppState();
        s.hoodies = Hoodie.getExtent();
        s.shirts = Shirt.getExtent();
        s.trousers = Trousers.getExtent();
        s.boots = Boot.getExtent();
        s.heeledShoes = HeeledShoe.getExtent();
        s.membershipCards = MembershipCard.getExtent();
        s.orders = Order.getExtent();
        s.contracts = Contract.getExtent();
        s.customers = Customer.getExtent();
        s.debitCards = DebitCard.getExtent();
        s.employees = Employee.getExtent();
        return s;
    }

    public void applyToStatics() {
        Hoodie.setExtent(hoodies);
        Shirt.setExtent(shirts);
        Trousers.setExtent(trousers);
        Boot.setExtent(boots);
        HeeledShoe.setExtent(heeledShoes);
        MembershipCard.setExtent(membershipCards);
        Order.setExtent(orders);
        Contract.setExtent(contracts);
        Customer.setExtent(customers);
        DebitCard.setExtent(debitCards);
        Employee.setExtent(employees);
    }
}