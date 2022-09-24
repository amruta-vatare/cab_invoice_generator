package com.bridgelabs;

import java.util.List;

public class Customer {
    int customerId;
    List<Ride> rides;

    public Customer(int customerId, List<Ride> rideList) {
        this.customerId = customerId;
        this.rides = rideList;
    }
}
