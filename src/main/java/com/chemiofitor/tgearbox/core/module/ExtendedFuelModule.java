package com.chemiofitor.tgearbox.core.module;

import com.chemiofitor.tgearbox.api.ITinkerBlockEntityHeatSource;
import com.chemiofitor.tgearbox.api.ITinkerBlockHeatSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

/**
 * Extended fuel module that supports custom Tinker heat sources (both blocks and block entities).
 * Extends the base SolidFuelModule to integrate additional heating mechanics.
 */
public class ExtendedFuelModule extends SolidFuelModule {
    // Position of the fuel/heat source block in the world
    private final BlockPos fuelPos;

    /**
     * Constructs an ExtendedFuelModule with a parent block entity and fuel position.
     * @param parent The parent MantleBlockEntity this module belongs to
     * @param fuelPos The position of the fuel/heat source in the world
     */
    public ExtendedFuelModule(MantleBlockEntity parent, BlockPos fuelPos) {
        super(parent, fuelPos);
        this.fuelPos = fuelPos;
    }

    /**
     * Checks if there is an active heat source at the fuel position.
     * Prioritizes block entities first, then checks block types.
     * @return True if an active heat source is found, false otherwise
     */
    public boolean isHeating() {
        Level level = getLevel();

        BlockEntity be = level.getBlockEntity(fuelPos);
        if (be instanceof ITinkerBlockEntityHeatSource heatSource) {
            return heatSource.isHeating();
        }

        Block block = level.getBlockState(fuelPos).getBlock();
        if (block instanceof ITinkerBlockHeatSource heatSource) {
            return heatSource.isHeating(level, fuelPos);
        }

        return false;
    }

    /**
     * Gets the temperature from the active heat source at the fuel position.
     * Prioritizes block entities first, then checks block types.
     * @return Temperature value from the heat source, or 0 if no active source
     */
    private int getHeatSourceTemperature() {
        Level level = getLevel();

        BlockEntity be = level.getBlockEntity(fuelPos);
        if (be instanceof ITinkerBlockEntityHeatSource heatSource) {
            return heatSource.getTemperature();
        }

        Block block = level.getBlockState(fuelPos).getBlock();
        if (block instanceof ITinkerBlockHeatSource heatSource) {
            return heatSource.getTemperature(level, fuelPos);
        }

        return 0;
    }

    /**
     * Gets the heating rate from the active heat source at the fuel position.
     * Prioritizes block entities first, then checks block types.
     * @return Heating rate value from the heat source, or 0 if no active source
     */
    private int getHeatSourceRate() {
        Level level = getLevel();

        BlockEntity be = level.getBlockEntity(fuelPos);
        if (be instanceof ITinkerBlockEntityHeatSource heatSource) {
            return heatSource.getRate();
        }

        Block block = level.getBlockState(fuelPos).getBlock();
        if (block instanceof ITinkerBlockHeatSource heatSource) {
            return heatSource.getRate(level, fuelPos);
        }

        return 0;
    }

    /**
     * Finds the fuel temperature, using custom heat sources if active.
     * Falls back to the parent class implementation if no active heat source.
     * @param consume Unused in this implementation (inherited parameter)
     * @return Temperature from the active heat source or parent fuel system
     */
    @Override
    public int findFuel(boolean consume) {
        return isHeating() ? getHeatSourceTemperature() : super.findFuel(consume);
    }

    /**
     * Gets the current temperature, using custom heat sources if active.
     * Falls back to the parent class implementation if no active heat source.
     * @return Current temperature value
     */
    @Override
    public int getTemperature() {
        return isHeating() ? getHeatSourceTemperature() : super.getTemperature();
    }

    /**
     * Gets the current heating rate, using custom heat sources if active.
     * Falls back to the parent class implementation if no active heat source.
     * @return Current heating rate value
     */
    @Override
    public int getRate() {
        return isHeating() ? getHeatSourceRate() : super.getRate();
    }

    /**
     * Checks if there is available fuel/heat, including active custom heat sources.
     * @return True if there's an active heat source or fuel in the parent system
     */
    @Override
    public boolean hasFuel() {
        return isHeating() || super.hasFuel();
    }

    /**
     * Decreases fuel amount, but only if no active custom heat source is present.
     * @param amount The amount to decrease the fuel by
     */
    @Override
    public void decreaseFuel(int amount) {
        if (!isHeating()) {
            super.decreaseFuel(amount);
        }
    }
}
