package ru.urfu;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * Дискорд бот
 */
public class DiscordBot {

    private final String token;

    private GatewayDiscordClient client;
    private final MessageService messageService;


    /**
     * Конструктор DiscordBot
     * @param token токен бота
     * @param messageService сервис создания сообщений
     */
    public DiscordBot(String token, MessageService messageService) {
        this.token = token;
        this.messageService = messageService;
    }


    /**
     * Запускает бота Discord, обрабатывает полученное сообщение, отправляет в чат ответ
     * формата "Ваше сообщение: 'сообщение'"
     */
    public void start() {
        client = DiscordClient.create(token).login().block();
        if (client == null) {
            throw new RuntimeException("Ошибка при входе в Discord");
        }
        client.on(MessageCreateEvent.class)
                .doOnError(throwable -> {
                    throw new RuntimeException("Ошибка при работе Discord бота", throwable);
                })
                .subscribe(event -> {
                    Message eventMessage = event.getMessage();
                    if (eventMessage.getAuthor().map(user -> !user.isBot()).orElse(false)) {
                        String chatId = eventMessage.getChannelId().asString();
                        String messageFromUser = eventMessage.getContent();

                        String response = messageService.createMessage(messageFromUser);
                        sendMessage(chatId, response);
                    }
                });
        System.out.println("Discord бот запущен");
        client.onDisconnect().block();
    }

    /**
     * Отправить сообщение
     * @param chatId идентификатор чата
     * @param message текст сообщения
     */
    public void sendMessage(String chatId, String message) {
        Snowflake channelId = Snowflake.of(chatId);
        MessageChannel channel = client.getChannelById(channelId).ofType(MessageChannel.class).block();
        if (channel != null) {
            channel.createMessage(message).block();
        } else {
            System.err.println("Канал не найден");
        }
    }
}
