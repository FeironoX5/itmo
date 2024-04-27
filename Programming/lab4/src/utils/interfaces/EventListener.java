package utils.interfaces;

import utils.implementations.Event;

// FIXME мб реализовать все расш. как анонимные классы?
public interface EventListener {
    /**
     * Функция, которая вызывается при срабатывании
     * события типа, на который подписан {@code EventListener}.
     *
     * @param event Событие
     */
    void handle(Event<?> event);
}
