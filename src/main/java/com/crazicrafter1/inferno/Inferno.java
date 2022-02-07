package com.crazicrafter1.inferno;

import com.crazicrafter1.inferno.item.FerItems;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Inferno {
    INSTANCE;

    public static final String MOD_ID = "inferno";
    public static final String MOD_VERSION = "1.0.0";



    public static final Logger LOGGER = LogManager.getLogger("InfernoMod");
    public static final MinecraftClient MC = MinecraftClient.getInstance();


    public void initialize() {
        LOGGER.info("Initializing inferno");

        FerItems.registerItems();



    }
}
