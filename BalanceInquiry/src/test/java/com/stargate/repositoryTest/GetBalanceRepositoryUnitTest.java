package com.stargate.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.stargate.entity.Bank;
import com.stargate.repository.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations="classpath:application.properties")
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class GetBalanceRepositoryUnitTest {
       @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GetBalanceRepository balanceRepo;

    @Test
    public void testFindBalance() throws Exception {
        Bank bal = new Bank();
        bal.setAccount_no("123124424124");
        bal.setAvailable_balance(1000.00);
        bal.setBank_id(15670);
        bal.setBank_name("abc");
        bal.setCustomer_id(10);
        entityManager.merge(bal);
       // entityManager.flush();
        Bank repoResult = (Bank) balanceRepo.findBalance(bal.getAccount_no());

        assertThat(repoResult.getAvailable_balance()).isEqualTo(bal.getAvailable_balance());
    }
}
