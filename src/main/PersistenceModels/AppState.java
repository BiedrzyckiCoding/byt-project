package main.PersistenceModels;


import java.io.Serializable;
import java.util.List;

class AppState implements Serializable {
    private static final long serialVersionUID = 1L;

    List<ClothingItem> clothingItems;
    List<Footwear> footwearItems;
    List<MembershipCard> membershipCards;
    List<Order> orders;
    List<Contract> contracts;
    List<Customer> customers;
    List<DebitCard> debitCards;
    List<Employee> employees;

    static AppState fromStatics() {
        AppState s = new AppState();
        s.clothingItems = ClothingItem.getExtent();
        s.footwearItems = Footwear.getExtent();
        s.membershipCards = MembershipCard.getExtent();
        s.orders = Order.getExtent();
        s.contracts = Contract.getExtent();
        s.customers = Customer.getExtent();
        s.debitCards = DebitCard.getExtent();
        s.employees = Employee.getExtent();
        return s;
    }

    void applyToStatics() {
        ClothingItem.setExtent(clothingItems);
        Footwear.setExtent(footwearItems);
        MembershipCard.setExtent(membershipCards);
        Order.setExtent(orders);
        Contract.setExtent(contracts);
        Customer.setExtent(customers);
        DebitCard.setExtent(debitCards);
        Employee.setExtent(employees);
    }
}