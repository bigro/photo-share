package com.ooogurooo.photoshare.handler;

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
import com.ooogurooo.photoshare.model.image.Image;
import com.ooogurooo.photoshare.service.ImageService;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.util.UUID;

@LineMessageHandler
public class LineBotHandler {

    private LineMessagingClient lineMessagingClient;

    private ImageService service;

    private SimpMessagingTemplate messagingTemplate;

    public LineBotHandler(LineMessagingClient lineMessagingClient,
                          ImageService service,
                          SimpMessagingTemplate messagingTemplate) {
        this.lineMessagingClient = lineMessagingClient;
        this.service = service;
        this.messagingTemplate = messagingTemplate;
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        final String originalMessageText = event.getMessage().getText();
        return new TextMessage("画像を投稿してください");
    }

    @EventMapping
    public Message handleImageMessageEvent(MessageEvent<ImageMessageContent> event) throws IOException {
        String url;
        try {
            MessageContentResponse response = lineMessagingClient.getMessageContent(event.getMessage().getId()).get();
            String uuid = UUID.randomUUID().toString();
            url = service.post(response.getStream(), "wedding/" + uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return new TextMessage("画像を登録できませんでした。");
        }

        try {
            messagingTemplate.convertAndSend("/topic/images", new Image(url));
        } catch (Exception e) {
            e.printStackTrace();
            return new TextMessage("スライドショーに追加できませんでした。");
        }

        return new TextMessage("画像を投稿しました。");
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
