package com.bridgelabs;


import java.util.ArrayList;
import java.util.List;


public class CabInvoiceGenerator {
    public static final int MINIMUM_FARE = 5;
    public static int COST_PER_KILOMETER;
    public static int COST_PER_MINUTE;
    public static List<Customer> customerList = new ArrayList<>();

    public static double calculateTotalFare(double distance, double time) {
        double totalFare = distance * COST_PER_KILOMETER + time * COST_PER_MINUTE;
        System.out.println(totalFare);
        return totalFare < MINIMUM_FARE ? MINIMUM_FARE : totalFare;
    }

    public static double calculateTotalFare(List<Ride> rideList) {
        double aggregate = 0;
        for (Ride ride : rideList) {
            aggregate += calculateTotalFare(ride.distance, ride.time);
        }
        return aggregate;
    }

    public static Invoice invoiceService(int customerId) {
        List<Ride> rides = null;
        for (Customer customer :
                customerList) {
            if (customer.customerId == customerId) {
                rides = customer.rides;
            }
        }
        return generateInvoice(rides);
    }

    public static Invoice generateInvoice(List<Ride> rideList) {
        double aggregate = calculateTotalFare(rideList);
        int rides = rideList.size();
        double average = aggregate / rides;
        return new Invoice(rides, aggregate, average);
    }

    public static Invoice invoiceForCustomerByCategory(int customerId, Category CA) {
        double aggregate = 0;
        double average = 0;
        List<Ride> rides = null;
        setCategory(CA);
        for (Customer customer : customerList) {
            if (customerId == customer.customerId) {
                rides = customer.rides;
            }
        }
        for (Ride ride : rides) {
            aggregate += ride.distance * COST_PER_KILOMETER + ride.time * COST_PER_MINUTE;
        }
        average = aggregate / rides.size();
        return new Invoice(rides.size(), aggregate, average);
    }

    private static void setCategory(Category ca) {
        switch (ca) {
            case PREMIUM_CUSTOMER -> {
                COST_PER_KILOMETER = 15;
                COST_PER_MINUTE = 2;
            }
            case NORMAL_CUSTOMER -> {
                COST_PER_KILOMETER = 10;
                COST_PER_MINUTE = 1;
            }
            default -> {
                System.out.println("wrong customer");
            }
        }
    }

    enum Category {
        PREMIUM_CUSTOMER,
        NORMAL_CUSTOMER;
    }


}
