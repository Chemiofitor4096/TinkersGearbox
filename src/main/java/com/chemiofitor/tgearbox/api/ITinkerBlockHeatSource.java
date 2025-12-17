package com.chemiofitor.tgearbox.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * Interface representing a block that can act as a heat source in Tinker's Gearbox.
 * Extends the base ITinkerHeatSource interface.
 */
public interface ITinkerBlockHeatSource extends ITinkerHeatSource {

    /**
     * Gets the temperature provided by this heat source block.
     * @param level The world/level where the block is located
     * @param pos The position of the block in the world
     * @return The temperature value of the heat source
     */
    int getTemperature(Level level, BlockPos pos);

    /**
     * Gets the heating rate of this heat source block.
     * @param level The world/level where the block is located
     * @param pos The position of the block in the world
     * @return The heating rate value
     */
    int getRate(Level level, BlockPos pos);

    /**
     * Checks if this block is currently active as a heat source.
     * @param level The world/level where the block is located
     * @param pos The position of the block in the world
     * @return True if the block is actively heating, false otherwise
     */
    boolean isHeating(Level level, BlockPos pos);
}
