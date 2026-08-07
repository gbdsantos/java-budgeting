package br.com.gbdsantos.budgeting.infrastructure.http.request;

import br.com.gbdsantos.budgeting.application.input.PersistTransactionInput;
import br.com.gbdsantos.budgeting.domain.Category;

public record TransactionRequest(
  String description,
  Category category,
  long amount
) {

  public PersistTransactionInput toInput() {
    return new PersistTransactionInput(
      description,
      amount,
      category
    );
  }}
