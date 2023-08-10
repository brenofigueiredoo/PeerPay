package br.com.breno.learningspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.breno.learningspring.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
