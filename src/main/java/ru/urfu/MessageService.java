package ru.urfu;

/**
 * Класс работы с сообщениями
 */
public class MessageService {

    /**
     * Возвращает сообщение формата:
     * "Ваше сообщение: messageFromUser"
     *
     * @param messageFromUser сообщение пользователя
     */
    public String createMessage(String messageFromUser) {
        return String.format("Ваше сообщение: %s", messageFromUser);
    }
}
