package com.chemiofitor.tgearbox.api;

import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;

/**
 * API interface for block entities that can be processed by Create mod's fan air currents.
 * Defines the core interaction methods for fan airflow processing logic.
 */
public interface IFanProcessingTarget {

    /**
     * Processes the target with fan airflow of a specific type and speed.
     * Called when the fan's air current affects this block entity.
     *
     * @param segmentType Type of fan processing (e.g., push, pull, effect)
     * @param speed       Speed of the fan generating the air current (0.0f to max fan speed)
     */
    void process(FanProcessingType segmentType, float speed);

    /**
     * Checks if the target can be processed by the specified fan processing type.
     * Prevents invalid processing interactions (e.g., a target that only accepts "pull" airflow).
     *
     * @param segmentType Type of fan processing to check
     * @return True if the target supports this processing type, false otherwise
     */
    boolean canProcess(FanProcessingType segmentType);
}
