package com.bridgelabs;

import java.util.List;
import java.util.Objects;

public class Invoice {
    int rides;
    double totalFare;
    double averagePerFare;

    public Invoice(int rides, double totalFare, double averagePerFare) {
        this.rides = rides;
        this.totalFare = totalFare;
        this.averagePerFare = averagePerFare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return rides == invoice.rides && Double.compare(invoice.totalFare, totalFare) == 0 && Double.compare(invoice.averagePerFare, averagePerFare) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rides, totalFare, averagePerFare);
    }
}
