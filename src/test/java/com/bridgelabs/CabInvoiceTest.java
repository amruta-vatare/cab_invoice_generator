package com.bridgelabs;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CabInvoiceTest {
    @Test
    public void givenDistanceAndTimeShouldReturnTotalFare() {
        double totalFare = CabInvoiceGenerator.calculateTotalFare(2.0, 4.0);
        Assert.assertEquals(24.0, totalFare, 0.0);
    }

    @Test
    public void givenDistanceAndTimeShouldReturnMinimumFare() {
        double totalFare = CabInvoiceGenerator.calculateTotalFare(0.3, 1);
        Assert.assertEquals(5, totalFare, 0.0);
    }

    @Test
    public void givenMultipleRidesShouldReturnAggregateFare() {
        Ride ride1 = new Ride(5, 10);
        Ride ride2 = new Ride(4, 8);
        Ride ride3 = new Ride(2, 9);
        Ride ride4 = new Ride(6, 3);
        List<Ride> rideList = new ArrayList<>();
        rideList.add(ride1);
        rideList.add(ride2);
        rideList.add(ride3);
        rideList.add(ride4);
        double totalFare = CabInvoiceGenerator.calculateTotalFare(rideList);
        Assert.assertEquals(200.0, totalFare, 0.0);
    }

    @Test
    public void givenMultipleRidesShouldReturnsInvoice() {
        Ride ride1 = new Ride(5, 10);
        Ride ride2 = new Ride(4, 8);
        Ride ride3 = new Ride(2, 9);
        Ride ride4 = new Ride(6, 3);
        List<Ride> rideList = new ArrayList<>();
        rideList.add(ride1);
        rideList.add(ride2);
        rideList.add(ride3);
        rideList.add(ride4);
        Invoice expectedInvoice = new Invoice(rideList.size(), 200, 50);
        Invoice actualInvoice = CabInvoiceGenerator.generateInvoice(rideList);
        Assert.assertEquals(expectedInvoice, actualInvoice);
    }

    @Test
    public void givenCustomerIdShouldReturnsInvoice() {
        Ride ride1 = new Ride(5, 10);
        Ride ride2 = new Ride(4, 8);
        Ride ride3 = new Ride(2, 9);
        Ride ride4 = new Ride(6, 3);
        List<Ride> rideList = new ArrayList<>();
        rideList.add(ride1);
        rideList.add(ride2);
        List<Ride> rideList1 = new ArrayList<>();
        rideList1.add(ride3);
        rideList1.add(ride4);
        Customer customer = new Customer(1, rideList);
        Customer customer1 = new Customer(2, rideList1);
        CabInvoiceGenerator.customerList.add(customer);
        CabInvoiceGenerator.customerList.add(customer1);
        Invoice actualInvoice = CabInvoiceGenerator.invoiceService(customer.customerId);
        Invoice expectedInvoice = new Invoice(rideList.size(), 108, 54);
        Assert.assertEquals(expectedInvoice, actualInvoice);
    }

    @Test
    public void givenCustomerIdShouldReturnsInvoiceForPremiumCustomer() {
        Ride ride1 = new Ride(5, 10);
        Ride ride2 = new Ride(4, 8);
        Ride ride3 = new Ride(2, 9);
        Ride ride4 = new Ride(6, 3);
        List<Ride> rideList = new ArrayList<>();
        rideList.add(ride1);
        rideList.add(ride2);
        List<Ride> rideList1 = new ArrayList<>();
        rideList1.add(ride3);
        rideList1.add(ride4);
        Customer customer = new Customer(1, rideList);
        Customer customer1 = new Customer(2, rideList1);
        CabInvoiceGenerator.customerList.add(customer);
        CabInvoiceGenerator.customerList.add(customer1);
        Invoice expectedInvoice = CabInvoiceGenerator.invoiceForCustomerByCategory(customer.customerId, CabInvoiceGenerator.Category.PREMIUM_CUSTOMER);
        Invoice actualInvoice = CabInvoiceGenerator.invoiceForCustomerByCategory(1, CabInvoiceGenerator.Category.PREMIUM_CUSTOMER);
        Assert.assertEquals(expectedInvoice, actualInvoice);
    }

    @Test
    public void givenCustomerIdShouldReturnsInvoiceForNormalCustomer() {
        Ride ride1 = new Ride(5, 10);
        Ride ride2 = new Ride(4, 8);
        Ride ride3 = new Ride(2, 9);
        Ride ride4 = new Ride(6, 3);
        List<Ride> rideList = new ArrayList<>();
        rideList.add(ride1);
        rideList.add(ride2);
        List<Ride> rideList1 = new ArrayList<>();
        rideList1.add(ride3);
        rideList1.add(ride4);
        Customer customer = new Customer(1, rideList);
        Customer customer1 = new Customer(2, rideList1);
        CabInvoiceGenerator.customerList.add(customer);
        CabInvoiceGenerator.customerList.add(customer1);
        Invoice expectedInvoice = CabInvoiceGenerator.invoiceForCustomerByCategory(customer.customerId, CabInvoiceGenerator.Category.NORMAL_CUSTOMER);
        Invoice actualInvoice = CabInvoiceGenerator.invoiceForCustomerByCategory(1, CabInvoiceGenerator.Category.NORMAL_CUSTOMER);
        Assert.assertEquals(expectedInvoice, actualInvoice);
    }

}
