package utils.enums;

/**
 * Тип {@link Event}.
 *
 * @author Gleb Kiva
 * @see EventBus
 * @see Event
 * @see EventListener
 */
public enum EventType {
    /**
     * Отделение ступени ракеты.
     */
    STAGE_SEPARATED,
    /**
     * Вход в фазу полёта ракеты.
     * @see FlightPhases
     */
    FLIGHT_PHASE_ENTERED,
    /**
     * Двигателю ракеты задана новая мощность.
     */
    ENGINE_POWER_SET;
    // TODO engine power set event call
    // FIXME potential risk, may be should state <T> type here -> интерфейс Payload , Event <- EventType, 
}
