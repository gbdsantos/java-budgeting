package br.com.gbdsantos.budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
@SpringBootTest
class OpenAiChatModelTest {

    @Autowired
    private ChatClient.Builder chatClientBuilder;

    @Test
    void shouldReceiveResponseWhenChatModelIsCalled() {

        String response = chatClientBuilder.build()
          .prompt()
          .user("Gere um registro de budgeting, com descrição de gasto, valor em reais e local.")
          .call()
          .content();

        assertThat(response).isNotBlank();

        System.out.println(response);
    }
}