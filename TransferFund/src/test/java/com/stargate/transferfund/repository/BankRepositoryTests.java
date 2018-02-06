package com.stargate.transferfund.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.stargate.transferfund.entity.Bank;

@RunWith(SpringRunner.class)
@DataJpaTest 
//@AutoConfigureTestDatabase(replace = NONE)
public class BankRepositoryTests {

	@Autowired
    private TestEntityManager entityManager;
    
	@Autowired
	BankRepository bankRepository;
	
	@Test
	public void whenDebit_Success() {
		/*int result = bankRepository.debitBankBalance(101, new Double(1000));

	    Assert.assertNotNull(bankRepository.findOne(101));

		assertThat(result).isEqualTo(1);
		
	    assertThat(found.getName())
	      .isEqualTo(alex.getName());
		 */	
		
		 // given
	    Bank bank = new Bank();
	    bank.setBankId(101);
	    bank.setAccountType("CHECKING");
	    bank.setAccountNo("12345");
	    bank.setAvailableBalance(50000.0);
	    bank.setBankName("Bank of New Mexico");
	    bank.setRoutingNo("23435345");
	    bank.setCustomerId(5);
	    
	    entityManager.persist(bank);
	    entityManager.flush();
	 
	    // when
	    //Employee found = employeeRepository.findByName(alex.getName());
	    int result = bankRepository.debitBankBalance(101, new Double(1000));
	    
	    // then
		assertThat(result).isEqualTo(1);
	}
}
