package com.chemiofitor.tgearbox.mixin;

import com.chemiofitor.tgearbox.api.IFanProcessingTarget;
import com.simibubi.create.content.kinetics.fan.AirCurrent;
import com.simibubi.create.content.kinetics.fan.IAirCurrentSource;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AirCurrent.class)
public abstract class InjectAirCurrent {

    @Shadow(remap = false)
    @Final
    public IAirCurrentSource source;

    @Shadow(remap = false)
    public Direction direction;

    @Shadow(remap = false)
    protected abstract int getLimit();

    @Shadow(remap = false)
    public abstract FanProcessingType getTypeAt(float offset);

    @Inject(
            remap = false,
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/content/kinetics/fan/AirCurrent;tickAffectedHandlers()V")
    )
    public void tickMixin(CallbackInfo ci) {
        Level world = source.getAirCurrentWorld();
        BlockPos start = source.getAirCurrentPos();
        int limit = getLimit();
        float speed = source.getSpeed();
        if (world != null) {
            for (int i = 1; i <= limit; i++) {
                FanProcessingType type = getTypeAt(i - 1);
                BlockPos pos = start.relative(direction, i);
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof IFanProcessingTarget target)
                    target.process(type, speed);
            }
        }
    }
}
