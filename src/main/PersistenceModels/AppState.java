package main.PersistenceModels;

import java.io.Serializable;
import java.util.List;

class AppState implements Serializable {
    private static final long serialVersionUID = 1L;

    List<Hoodie> hoodies;
    List<Shirt> shirts;
    List<Trousers> trousers;
    List<Boot> boots;
    List<HeeledShoe> heeledShoes;
    List<MembershipCard> membershipCards;
    List<Order> orders;
    List<Contract> contracts;
    List<Customer> customers;
    List<DebitCard> debitCards;
    List<Employee> employees;

    static AppState fromStatics() {
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

    void applyToStatics() {
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