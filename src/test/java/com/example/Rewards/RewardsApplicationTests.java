package com.example.Rewards;

import com.example.pojo.Customer;
import com.example.pojo.Reward;
import com.example.pojo.Transaction;
import com.example.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class RewardsApplicationTests {

	@InjectMocks
	private RewardService rewardsService;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// Define test data
		List<Transaction> transactions1 = Arrays.asList(
				new Transaction(1L, 1L, 120.0, LocalDate.of(2023, 1, 1)),
				new Transaction(2L, 1L, 75.0, LocalDate.of(2023, 1, 15))
		);
		Customer customer1 = new Customer(1L, "Pranay", transactions1);

		List<Transaction> transactions2 = Arrays.asList(
				new Transaction(3L, 2L, 50.0, LocalDate.of(2023, 2, 3)),
				new Transaction(4L, 2L, 150.0, LocalDate.of(2023, 2, 10))
		);
		Customer customer2 = new Customer(2L, "Dasharath", transactions2);

		// Mock service responses
		when(rewardsService.findCustomerById(1L)).thenReturn(customer1);
		when(rewardsService.findCustomerById(2L)).thenReturn(customer2);
		when(rewardsService.findCustomerById(3L)).thenReturn(null); // Non-existent customer
	}

	@Test
	void testCalculateRewards() {
		// Test case for customer with multiple transactions
		List<Reward> rewards1 = rewardsService.calculateRewards(1L);
		assertEquals(2, rewards1.size());
		assertEquals(70, rewards1.get(0).getPoints());
		assertEquals(25, rewards1.get(1).getPoints());

		// Test case for customer with different transactions
		List<Reward> rewards2 = rewardsService.calculateRewards(2L);
		assertEquals(2, rewards2.size());
		assertEquals(0, rewards2.get(0).getPoints()); // Transaction below 50
		assertEquals(100, rewards2.get(1).getPoints());

		// Test case for non-existent customer
		List<Reward> rewards3 = rewardsService.calculateRewards(3L);
		assertEquals(0, rewards3.size()); // Expect empty list as there is no such customer
	}

}