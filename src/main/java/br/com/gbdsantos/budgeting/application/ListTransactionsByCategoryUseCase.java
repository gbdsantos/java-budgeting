package br.com.gbdsantos.budgeting.application;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import br.com.gbdsantos.budgeting.application.output.TransactionOutput;
import br.com.gbdsantos.budgeting.domain.Category;
import br.com.gbdsantos.budgeting.domain.TransactionRepository;

@Service
public class ListTransactionsByCategoryUseCase {
  private final TransactionRepository transactionRepository;

  public ListTransactionsByCategoryUseCase(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Tool(
    description = "Lista transações financeiras por categoria",
    name = "list-transactions-by-category"
  )
  public List<TransactionOutput> execute(Category category) {
    return transactionRepository.findAllByCategory(category)
    .stream()
    .map(TransactionOutput::from)
    .toList();
  }
}
