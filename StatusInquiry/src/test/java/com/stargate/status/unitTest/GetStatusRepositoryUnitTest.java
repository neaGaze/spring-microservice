package com.stargate.status.unitTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.stargate.status.entity.Transaction;
import com.stargate.status.repository.GetStatusRepository;
@RunWith(SpringRunner.class)
@DataJpaTest
public class GetStatusRepositoryUnitTest {
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GetStatusRepository statusRepo;

    @Test
    public void testFindStatus() {
        Transaction txn = new Transaction();
        txn.setId(10);
    	txn.setStatus("Success");
        entityManager.persist(txn);
        entityManager.flush();
        Transaction repoResult = statusRepo.findOne("10");

        assertThat(repoResult.getStatus()).isEqualTo(txn.getStatus());
    }
}
