package com.example.miniwallet;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService service;
    public WalletController(WalletService service) { this.service = service; }
    @PostMapping("/create")
    public User create(@RequestParam String name, @RequestParam BigDecimal balance) {
        return service.createUser(name, balance);
    }
    @GetMapping("/balance/{id}")
    public BigDecimal balance(@PathVariable Long id) {
        return service.getBalance(id);
    }
    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromId,
                           @RequestParam Long toId,
                           @RequestParam BigDecimal amount) {
        service.transfer(fromId, toId, amount);
        return "Transfer successful";
    }
}
