package com.example.miniwallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WalletIntegrationTest {
    @Autowired
    WalletService service;
    @Test
    void transferTest() {
        User a = service.createUser("Alice", new BigDecimal("100"));
        User b = service.createUser("Bob", new BigDecimal("50"));
        service.transfer(a.getId(), b.getId(), new BigDecimal("40"));
        assertEquals(new BigDecimal("60.00"), service.getBalance(a.getId()));
        assertEquals(new BigDecimal("90.00"), service.getBalance(b.getId()));
    }
}
