package br.com.gbdsantos.budgeting.application.input;

import br.com.gbdsantos.budgeting.domain.Category;

public record PersistTransactionInput(
  String description,
  long amount,
  Category category
) {}
