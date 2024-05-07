package teapot_rocket.utils.enums;

/**
 * Represents different flight phases of a rocket, used for determining
 * the appropriate actions to be taken during different stages of flight,
 * such as deploying parachutes.
 *
 * <p>
 * Each flight phase corresponds to a specific stage of rocket flight and
 * may involve different operations and maneuvers.
 * </p>
 *
 * @author Gleb Kiva
 */
public enum FlightPhases {

    /**
     * The launch phase where the rocket is vertically oriented on the ground,
     * engines are not running, and all mechanisms are in their initial state.
     */
    LAUNCH,

    /**
     * The powered ascent phase where the rocket lifts off the ground,
     * engines are operational, and maneuvering engines are not used.
     */
    POWERED_ASCENT,

    /**
     * The flight phase where the rocket exits the atmosphere and continues
     * its flight, gradually separating stages, and maneuvering engines are not
     * used.
     */
    FLIGHT,

    /**
     * The ejection change phase where the rocket enters the atmosphere,
     * discarding all but one stage, and maneuvering engines are used.
     */
    EJECTION_CHANGE,

    /**
     * The slow descent phase where the rocket descends with one remaining stage,
     * deploying a parachute, and engines are not used.
     */
    SLOW_DESCENT,

    /**
     * The landing phase where the rocket is on the ground with a deployed
     * parachute,
     * consisting of one remaining stage.
     */
    LANDING
}
