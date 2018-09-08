package com.ooogurooo.photoshare.controller;

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
import com.ooogurooo.photoshare.infrastructure.CloudinaryClient;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@LineMessageHandler
public class BotController {

    @Value("${temp.image.path}")
    private String tempPath;

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
        Path postImage = null;
        try {
            MessageContentResponse response = lineMessagingClient.getMessageContent(event.getMessage().getId()).get();
            String uuid = UUID.randomUUID().toString();
            CloudinaryClient cloudinaryClient = new CloudinaryClient();
            cloudinaryClient.upload(response.getStream(), "wedding/" + uuid);
        } catch (Exception e) {
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
