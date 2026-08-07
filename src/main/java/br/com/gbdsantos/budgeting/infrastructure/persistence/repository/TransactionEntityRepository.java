package br.com.gbdsantos.budgeting.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.gbdsantos.budgeting.domain.Category;
import br.com.gbdsantos.budgeting.infrastructure.persistence.entity.TransactionEntity;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {
  List<TransactionEntity> findAllByCategory(Category category);
}
