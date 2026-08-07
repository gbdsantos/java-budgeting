package br.com.gbdsantos.budgeting.infrastructure.http;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gbdsantos.budgeting.application.ListTransactionsByCategoryUseCase;
import br.com.gbdsantos.budgeting.application.PersistTransactionUseCase;
import br.com.gbdsantos.budgeting.domain.Category;
import br.com.gbdsantos.budgeting.infrastructure.http.request.TransactionRequest;
import br.com.gbdsantos.budgeting.infrastructure.http.response.TransactionReponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
  private final PersistTransactionUseCase persistTransactionUseCase;
  private final ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase;

  public TransactionController(
    ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase,
    PersistTransactionUseCase persistTransactionUseCase
  ) {
    this.listTransactionsByCategoryUseCase = listTransactionsByCategoryUseCase;
    this.persistTransactionUseCase = persistTransactionUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TransactionReponse createTransaction(@RequestBody TransactionRequest request) {
    var transaction = persistTransactionUseCase.execute(request.toInput());

    return TransactionResponse.from(transaction);
  }

  @GetMapping("/{category}")
  @ResponseStatus(HttpStatus.OK)
  public List<TransactionResponse> readTransactions(@PathVariable Category category) {
      return listTransactionsByCategoryUseCase.execute(category)
        .stream()
        .map(TransactionResponse::from)
        .toList();
  }
  
}
