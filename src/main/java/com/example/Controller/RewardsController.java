package com.example.Controller;

import com.example.pojo.Reward;
import com.example.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {
    @Autowired
    private RewardService rewardsService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Reward>> getRewardsByCustomer(@PathVariable Long customerId) {
        List<Reward> rewards = rewardsService.calculateRewards(customerId);
        return ResponseEntity.ok(rewards);
    }
}