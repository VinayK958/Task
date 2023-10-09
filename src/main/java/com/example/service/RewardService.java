package com.example.service;

import ch.qos.logback.core.CoreConstants;
import com.example.pojo.Customer;
import com.example.pojo.Reward;
import com.example.pojo.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardService {

    private List<Customer> customers;

    public RewardService() {
        customers = new ArrayList<>();

        // Transactions for Customer 1
        List<Transaction> transactionsCustomer1 = List.of(
                new Transaction(1L, 1L, 120.0, LocalDate.of(2023, 1, 1)),
                new Transaction(2L, 1L, 75.0, LocalDate.of(2023, 1, 15)),
                new Transaction(3L, 1L, 200.0, LocalDate.of(2023, 2, 5)),
                new Transaction(12L, 1L, 180.0, LocalDate.of(2023, 3, 18))
        );
        Customer customer1 = new Customer(1L, "Pranay", transactionsCustomer1);

        // Transactions for Customer 2
        List<Transaction> transactionsCustomer2 = List.of(
                new Transaction(4L, 2L, 50.0, LocalDate.of(2023, 1, 3)),
                new Transaction(5L, 2L, 150.0, LocalDate.of(2023, 2, 10)),
                new Transaction(13L, 2L, 210.0, LocalDate.of(2023, 3, 22))
        );
        Customer customer2 = new Customer(2L, "Dasharath", transactionsCustomer2);

        // Transactions for Customer 3
        List<Transaction> transactionsCustomer3 = List.of(
                new Transaction(6L, 3L, 110.0, LocalDate.of(2023, 1, 6)),
                new Transaction(7L, 3L, 70.0, LocalDate.of(2023, 2, 14)),
                new Transaction(8L, 3L, 90.0, LocalDate.of(2023, 3, 7)),
                new Transaction(14L, 3L, 250.0, LocalDate.of(2023, 3, 29))
        );
        Customer customer3 = new Customer(3L, "sai shanker", transactionsCustomer3);

        // Transactions for Customer 4
        List<Transaction> transactionsCustomer4 = List.of(
                new Transaction(9L, 4L, 95.0, LocalDate.of(2023, 1, 9)),
                new Transaction(10L, 4L, 65.0, LocalDate.of(2023, 2, 17)),
                new Transaction(11L, 4L, 130.0, LocalDate.of(2023, 3, 12)),
                new Transaction(15L, 4L, 300.0, LocalDate.of(2023, 3, 30))
        );
        Customer customer4 = new Customer(4L, "vinay", transactionsCustomer4);

        // Add customers to the list
        customers.addAll(List.of(customer1, customer2, customer3, customer4));
    }

    public List<Reward> calculateRewards(Long customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer == null) return new ArrayList<>();

        return customer.getTransactions().stream()
                .map(this::calculateReward)
                .collect(Collectors.toList());
    }

    public Customer findCustomerById(Long customerId) {
        System.out.println("am in customerid finding");
        return customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    private Reward calculateReward(Transaction transaction) {
        int points = 0;
        if (transaction.getAmount() > 100) {
            points += (transaction.getAmount() - 100) * 2 + 50;
        } else if (transaction.getAmount() > 50) {
            points += transaction.getAmount() - 50;
        }
        return new Reward(transaction.getCustomerId(), transaction.getDate().getMonthValue(), points);
    }


}