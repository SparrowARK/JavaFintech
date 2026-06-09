package com.scotiabank.corebanking;

import com.scotiabank.corebanking.model.BankAccount;
import com.scotiabank.corebanking.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CorebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(BankAccountRepository repository){
		return args -> {
			BankAccount player1 = new BankAccount();
			player1.setAccountNumber(1L);
			player1.setOwnerName("Aaditya");
			player1.setBalance(500.0);
			repository.save(player1);

			BankAccount player2 = new BankAccount();
			player2.setAccountNumber(2L);
			player2.setOwnerName("Matthew");
			player2.setBalance(100.0);
			repository.save(player2);

			System.out.println("Test level loaded. Spawned account for aaditya and matthew");
		};
	}

}
