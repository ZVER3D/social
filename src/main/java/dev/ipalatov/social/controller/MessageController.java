package dev.ipalatov.social.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dev.ipalatov.social.domain.Message;
import dev.ipalatov.social.domain.Views;
import dev.ipalatov.social.dto.MessageDto;
import dev.ipalatov.social.repo.MessageRepo;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {
  private final MessageRepo messageRepo;

  public MessageController(MessageRepo messageRepo) {
    this.messageRepo = messageRepo;
  }

  @GetMapping
  @JsonView(Views.IdName.class)
  public List<Message> list() {
    return messageRepo.findAll();
  }

  @GetMapping("{message}")
  @JsonView(Views.FullName.class)
  public Message getOne(@PathVariable Message message) {
    return message;
  }

  @PostMapping
  @JsonView(Views.FullName.class)
  public Message create(@RequestBody MessageDto msg) {
    Message message = new Message();
    message.setText(msg.getText());
    message.setCreationDate(LocalDateTime.now());

    return messageRepo.save(message);
  }

  @PutMapping("{message}")
  @JsonView(Views.FullName.class)
  public Message update(@RequestBody MessageDto message, @PathVariable Message messageFromDb) {
    BeanUtils.copyProperties(message, messageFromDb, "id");

    return messageRepo.save(messageFromDb);
  }

  @DeleteMapping("{message}")
  public void delete(@PathVariable Message message) {
    messageRepo.delete(message);
  }
}
