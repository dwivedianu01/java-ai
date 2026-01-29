package com.learn.basic.ai.chatbot.controller;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChaBotController {

    private final OpenAiChatModel chatModel;

    @Autowired
    public ChaBotController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/ai/message")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Today's weather") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));


        return chatModel.stream(prompt);
    }

    @GetMapping("/ai/message/string")
    public Flux<String> generateString(@RequestParam(value = "message", defaultValue = "Today's weather") String message) {
//        Prompt prompt = new Prompt(new UserMessage(message));

        return chatModel.stream(message);

//        return Flux.just("I'm good, this is a static response from server");
    }
}