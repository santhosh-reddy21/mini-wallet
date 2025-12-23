package com.example.miniwallet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
@Service
public class WalletService {
    private final UserRepository repo;
    public WalletService(UserRepository repo) { this.repo = repo; }
    public User createUser(String name, BigDecimal balance) {
        User u = new User();
        u.setName(name);
        u.setBalance(balance);
        return repo.save(u);
    }
    public BigDecimal getBalance(Long id) {
        return repo.findById(id).orElseThrow().getBalance();
    }
    @Transactional
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        User from = repo.findById(fromId).orElseThrow();
        User to = repo.findById(toId).orElseThrow();
        if (from.getBalance().compareTo(amount) < 0)
            throw new RuntimeException("Insufficient balance");
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));
        repo.save(from);
        repo.save(to);
    }
}
