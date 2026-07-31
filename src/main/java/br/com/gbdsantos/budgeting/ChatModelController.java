package br.com.gbdsantos.budgeting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/api")
@RestController
public class ChatModelController {
  private final OpenAiChatModel openAiChatModel;

  public ChatModelController(OpenAiChatModel openAichatModel) {
    this.openAiChatModel = openAiChatModel;
  }

  @GetMapping("/chat-model")
  public String chat(String prompt) {
      return this.openAiChatModel.call(prompt);
  }  
}
