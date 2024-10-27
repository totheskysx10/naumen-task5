package ru.urfu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageServiceTest {

    @Test
    public void createMessageTest() {
        MessageService messageService = new MessageService();
        String messageFromUser = "123";
        String createdMessage = "Ваше сообщение: 123";

        assertEquals(createdMessage, messageService.createMessage(messageFromUser));
    }
}
