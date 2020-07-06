package dev.ipalatov.social.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
  private String text;

  public MessageDto(String text) {
    this.text = text;
  }
}
