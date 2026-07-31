package br.com.gbdsantos.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
@SpringBootTest
public class OpenAiChatModel {
  @Autowired
  OpenAiApi openAiApi;

  @Test
  void should_receiveReponse_when_chatModelIsCalled() {
    var options = OpenAiChatOptions.builder()
      .model("gpt-5o-mini")
      .temperature(0.8)
      .responseFormat(ResponseFormat.builder().type(ResponseFormat.Type.TEXT).build())
      .build();

    var chatModel = OpenAiChatModel.builder(openAiApi)
      .openAiApi()
      .defaultOptions(options)
      .build();
      
    var response = chatModel.call("Gere um registro de budgeting, com descrição de gasto, valor em reais e local");
  
    assertThat(response).isNotEmpty();
    System.out.println(response);
  }
}
