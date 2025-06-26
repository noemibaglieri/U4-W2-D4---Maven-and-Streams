package noemibaglieri;

import noemibaglieri.entities.Customer;
import noemibaglieri.entities.Order;
import noemibaglieri.entities.Product;

import java.util.*;
import java.util.stream.Collectors;

public class Application {

    static List<Product> warehouse = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();


    public static void main(String[] args) {

        initializeWarehouse();
        createCustomers();
        placeOrders();

        System.out.println("--- TASK 1 ---");
        System.out.println("Show all orders by customer");
        Map<String, List<Order>> ordersByClient = orders.stream().collect(Collectors.groupingBy(order -> order.getCustomer().getName()));
        ordersByClient.forEach((customerName, orderList) -> {
            System.out.println("Customer: " + customerName);
            System.out.println("Orders:");
            orderList.forEach(order -> {
                order.getProducts().forEach(product -> {
                    System.out.println(" - Product: " + product.getName());
                });
            });
            System.out.println(ordersByClient);
        });

        System.out.println("--- TASK 2 ---");
        System.out.println("Total of all prodcuts by Customer");
        Map<String, Double> totalOfAllProductsByClient = orders.stream().collect(Collectors.groupingBy(customer -> customer.getCustomer().getName(), Collectors.summingDouble(Order::getTotal)));
        totalOfAllProductsByClient.forEach((customer, total) -> System.out.println("- Customer: " + customer + " - Total of ordered products: " + total + "€"));

        System.out.println("--- TASK 3 ---");
        System.out.println("Sort products by highest price");
        List<Product> productsSortedByHighestPrice = warehouse.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).toList();
        productsSortedByHighestPrice.forEach(System.out::println);

        System.out.println("--- TASK 4 ---");
        System.out.println("Average order total");
        Double averageOrderTotal = orders.stream().collect(Collectors.averagingDouble(order -> order.getTotal()));
        System.out.println(averageOrderTotal);

        System.out.println("--- TASK 5 ---");
        System.out.println("Average order total");
    }

    public static void initializeWarehouse() {
        Product iPhone = new Product("IPhone", "Smartphones", 2000.0);
        Product lotrBook = new Product("LOTR", "Books", 101);
        Product itBook = new Product("IT", "Books", 2);
        Product davinciBook = new Product("Da Vinci's Code", "Books", 2);
        Product diapers = new Product("Pampers", "Baby", 3);
        Product toyCar = new Product("Car", "Boys", 15);
        Product toyPlane = new Product("Plane", "Boys", 25);
        Product lego = new Product("Lego Star Wars", "Boys", 500);

        warehouse.addAll(Arrays.asList(iPhone, lotrBook, itBook, davinciBook, diapers, toyCar, toyPlane, lego));
    }

    public static void createCustomers() {
        Customer aldo = new Customer("Aldo Baglio", 1);
        Customer giovanni = new Customer("Giovanni Storti", 2);
        Customer giacomo = new Customer("Giacomo Poretti", 3);
        Customer marina = new Customer("Marina Massironi", 2);

        customers.add(aldo);
        customers.add(giovanni);
        customers.add(giacomo);
        customers.add(marina);
    }

    public static void placeOrders() {
        Order aldoOrder = new Order(customers.get(0));
        Order giovanniOrder = new Order(customers.get(1));
        Order giacomoOrder = new Order(customers.get(2));
        Order marinaOrder = new Order(customers.get(3));
        Order giacomoOrder2 = new Order(customers.get(2));

        Product iPhone = warehouse.get(0);
        Product lotrBook = warehouse.get(1);
        Product itBook = warehouse.get(2);
        Product davinciBook = warehouse.get(3);
        Product diaper = warehouse.get(4);

        aldoOrder.addProduct(iPhone);
        aldoOrder.addProduct(lotrBook);
        aldoOrder.addProduct(diaper);

        giovanniOrder.addProduct(itBook);
        giovanniOrder.addProduct(davinciBook);
        giovanniOrder.addProduct(iPhone);

        giacomoOrder.addProduct(lotrBook);
        giacomoOrder.addProduct(diaper);

        marinaOrder.addProduct(diaper);

        giacomoOrder2.addProduct(iPhone);

        orders.add(aldoOrder);
        orders.add(giovanniOrder);
        orders.add(giacomoOrder);
        orders.add(marinaOrder);
        orders.add(giacomoOrder2);

    }
}
