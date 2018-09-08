package com.ooogurooo.photoshare.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

@LineMessageHandler
public class BotController {

    private LineMessagingClient lineMessagingClient;

    public BotController(LineMessagingClient lineMessagingClient) {
        this.lineMessagingClient = lineMessagingClient;
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        final String originalMessageText = event.getMessage().getText();
        return new TextMessage("テキストです。");
    }

    @EventMapping
    public Message handleImageMessageEvent(MessageEvent<ImageMessageContent> event) throws IOException {
        try {
            MessageContentResponse response = lineMessagingClient.getMessageContent(event.getMessage().getId()).get();
            Path postImage = Files.createTempFile(Paths.get("src/main/resources/temp/"), "copied.", response.getMimeType());
            Files.copy(response.getStream(), postImage);

            Cloudinary cloudinary = new Cloudinary();
            cloudinary.uploader().upload(postImage, ObjectUtils.emptyMap());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new TextMessage("画像を登録できませんでした。");
        }
        
        return new TextMessage("画像を投稿しました。");
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
