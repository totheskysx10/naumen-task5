package ru.urfu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для MessageService
 */
public class MessageServiceTest {

    /**
     * Тест метода createMessage, проверяющий, что строка возвращается корректно
     */
    @Test
    public void createMessageTest() {
        MessageService messageService = new MessageService();
        String messageFromUser = "123";
        String createdMessage = "Ваше сообщение: 123";

        assertEquals(createdMessage, messageService.createMessage(messageFromUser));
    }
}
