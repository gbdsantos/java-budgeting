package br.com.gbdsantos.budgeting.application;

import org.springframework.stereotype.Service;

import br.com.gbdsantos.budgeting.application.input.PersistTransactionInput;
import br.com.gbdsantos.budgeting.application.output.TransactionOutput;
import br.com.gbdsantos.budgeting.domain.Category;
import br.com.gbdsantos.budgeting.domain.Transaction;
import br.com.gbdsantos.budgeting.domain.TransactionRepository;

@Service
public class PersistTransactionUseCase {
  private final TransactionRepository transactionRepository;

  public PersistTransactionUseCase(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public Transaction execute(PersistTransactionInput input) {
    var transaction = transactionRepository.save(
      new Transaction(
        input.description(),
        input.amount(),
        input.category()
      )
    );

    return TransactionOutput.from(transaction);
  }
}
