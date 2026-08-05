package br.com.gbdsantos.budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
@SpringBootTest
public class OpenAiSpeechModelIT {
  @Autowired
  OpenAiAudioSpeechModel openAiAudioSpeechModel;

  @Test
  public void should_produceAudio_when_textIsProvided() {
    var response = openAiAudioSpeechModel.call("O valor total do serviço ficou em 80 reais. Posso confirmar o pagamento?");
    
    assertThat(response).hasSizeGreaterThan(1024);

    try {
      var tempFile = Files.createTempFile("AUDIO_", ".mp3");
      Files.write(tempFile, response);
      System.out.println(tempFile.toAbsolutePath());
    } catch (Exception error) {
      System.out.println(error);
    }
  }
}
