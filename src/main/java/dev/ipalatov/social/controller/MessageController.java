package dev.ipalatov.social.controller;

import dev.ipalatov.social.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
  private int counter = 4;
  private List<Map<String, String>> messages =
      new ArrayList<Map<String, String>>() {
        {
          add(
              new HashMap<String, String>() {
                {
                  put("id", "1");
                  put("text", "Whats up?");
                }
              });
          add(
              new HashMap<String, String>() {
                {
                  put("id", "2");
                  put("text", "My second message");
                }
              });
          add(
              new HashMap<String, String>() {
                {
                  put("id", "3");
                  put("text", "And a third one");
                }
              });
        }
      };

  private Map<String, String> getMessage(String id) {
    return messages.stream()
        .filter(message -> message.get("id").equals(id))
        .findFirst()
        .orElseThrow(NotFoundException::new);
  }

  @GetMapping
  public List<Map<String, String>> list() {
    return messages;
  }

  @GetMapping("{id}")
  public Map<String, String> getOne(@PathVariable String id) {
    return getMessage(id);
  }

  @PostMapping
  public Map<String, String> create(@RequestBody Map<String, String> message) {
    message.put("id", String.valueOf(counter++));

    messages.add(message);

    return message;
  }

  @PutMapping("{id}")
  public Map<String, String> update(
      @RequestBody Map<String, String> message, @PathVariable String id) {
    Map<String, String> msg = getMessage(id);

    msg.put("text", message.get("text"));

    return msg;
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable String id) {
    Map<String, String> message = getMessage(id);

    messages.remove(message);
  }
}
