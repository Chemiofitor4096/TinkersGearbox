package com.chemiofitor.tgearbox;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TinkersGearbox.MOD_ID)
public class TinkersGearbox {
    public static final String MOD_ID = "tgearbox";
    public static final String NAME = "Tinkers' Gearbox";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    public TinkersGearbox() {
        LOGGER.info("Tinker's Gearbox has loaded!.");
    }
}
