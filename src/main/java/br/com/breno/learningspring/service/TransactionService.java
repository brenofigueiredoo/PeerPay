package br.com.breno.learningspring.service;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.breno.learningspring.dto.CreateTransactionDto;
import br.com.breno.learningspring.exception.AppException;
import br.com.breno.learningspring.model.Transaction;
import br.com.breno.learningspring.model.User;
import br.com.breno.learningspring.repository.TransactionRepository;
import br.com.breno.learningspring.repository.UserRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(final CreateTransactionDto transactionData) {

        final User foundPayer = userRepository.findById(transactionData.getPayer_id())
                .orElseThrow(() -> new AppException("User not found!", HttpStatus.NOT_FOUND));
        ;

        if (Objects.equals(foundPayer.getType(), "SELLER")) {
            throw new AppException("SELLER type users cannot send money", HttpStatus.NOT_FOUND);
        }

        final User foundPayee = userRepository.findById(transactionData.getPayee_id())
                .orElseThrow(() -> new AppException("User not found!", HttpStatus.NOT_FOUND));
        ;

        final float transactionValue = transactionData.getValue();

        final float payerCurrentBalance = foundPayer.getBalance();

        if (payerCurrentBalance < transactionValue) {
            throw new AppException("Payer balance not sufficient", HttpStatus.FORBIDDEN);
        }

        final float payeeCurrentBalance = foundPayee.getBalance();

        foundPayer.setBalance(payerCurrentBalance - transactionValue);
        foundPayee.setBalance(payeeCurrentBalance + transactionValue);

        final Transaction newTransaction = new Transaction(foundPayer, foundPayee, transactionValue);

        return transactionRepository.save(newTransaction);
    }

    public Transaction retrieveTransaction(final long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new AppException("User not found!", HttpStatus.NOT_FOUND));
    }
}
