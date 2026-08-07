package br.com.gbdsantos.budgeting.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.gbdsantos.budgeting.domain.Category;
import br.com.gbdsantos.budgeting.domain.Transaction;
import br.com.gbdsantos.budgeting.domain.TransactionRepository;
import br.com.gbdsantos.budgeting.infrastructure.persistence.entity.TransactionEntity;

@Repository
public class JpaTransactionRepository implements TransactionRepository {
  private final TransactionEntityRepository transactionEntityRepository;
  
  public JpaTransactionRepository(TransactionEntityRepository transactionEntityRepository) {
    this.transactionEntityRepository = transactionEntityRepository;
  }

  @Override
  public Transaction save(Transaction transaction) {
    var entity = TransactionEntity.from(transaction);
    return transactionEntityRepository.save(entity).toDomain();
  }

  @Override
  public List<Transaction> findAllByCategory(Category category) {
    return transactionEntityRepository.findAllByCategory(category)
      .stream()
      .map(TransactionEntity::toDomain)
      .toList();
  }
}
