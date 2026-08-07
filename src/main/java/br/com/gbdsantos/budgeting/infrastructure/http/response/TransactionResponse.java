package br.com.gbdsantos.budgeting.infrastructure.http.response;

import br.com.gbdsantos.budgeting.application.output.TransactionOutput;

public record TransactionResponse(
  String id,
  String category,
  String description,
  double value
) {
  public static TransactionResponse from(TransactionOutput output) {
    return new TransactionResponse(
      output.id(),
      output.category(),
      output.description(),
      output.value()
    );
  }
}
