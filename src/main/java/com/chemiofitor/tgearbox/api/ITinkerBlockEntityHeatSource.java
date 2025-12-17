package com.chemiofitor.tgearbox.api;

/**
 * Interface representing a block entity that can act as a heat source in Tinker's Gearbox.
 * Extends the base ITinkerHeatSource interface.
 * Unlike block-based heat sources, block entities typically hold their own world/position data.
 */
public interface ITinkerBlockEntityHeatSource extends ITinkerHeatSource {

    /**
     * Gets the temperature provided by this heat source block entity.
     * @return The temperature value of the heat source
     */
    int getTemperature();

    /**
     * Gets the heating rate of this heat source block entity.
     * @return The heating rate value
     */
    int getRate();

    /**
     * Checks if this block entity is currently active as a heat source.
     * @return True if the block entity is actively heating, false otherwise
     */
    boolean isHeating();
}
