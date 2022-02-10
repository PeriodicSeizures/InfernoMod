package com.crazicrafter1.inferno;

import com.crazicrafter1.inferno.block.FerBlocks;
import com.crazicrafter1.inferno.item.FerItems;
import com.crazicrafter1.inferno.world.biome.FerBiomes;
import com.crazicrafter1.inferno.world.gen.feature.FerStructureFeatureConfigs;
import com.crazicrafter1.inferno.world.gen.feature.FerStructureFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.mixin.structure.StructuresConfigAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public enum Inferno {
    INSTANCE;

    public static final String MOD_ID = "inferno";
    public static final String MOD_VERSION = "1.0.0";

    public static final Logger LOGGER = LogManager.getLogger("InfernoMod");
    public static final MinecraftClient MC = MinecraftClient.getInstance();


    public void initialize() {
        LOGGER.info("Initializing inferno");

        FerItems.init();
        FerBlocks.init();

        // structures
        FerStructureFeatures.register();
        FerStructureFeatureConfigs.register();

        /*
         * EXAMPLE BUSH
         */
        BiomeModifications.create(
                new Identifier(MOD_ID, "example_bush_addition")
        ).add(
                ModificationPhase.ADDITIONS,
                BiomeSelectors.all(),
                context -> context.getGenerationSettings().addBuiltInStructure(FerStructureFeatureConfigs.CONFIGURED_EXAMPLE_BUSH)
        );

        /*
         * LUST_GATE
         */
        BiomeModifications.create(
                new Identifier(MOD_ID, "lust_gate_addition")
        ).add(
                ModificationPhase.ADDITIONS,
                BiomeSelectors.includeByKey(FerBiomes.ELYSIAN_FIELDS_KEY),
                context -> context.getGenerationSettings().addBuiltInStructure(FerStructureFeatureConfigs.CONFIGURED_LUST_GATE)
        );

        addStructureDimensionSpawning();

        // then biomes
        FerBiomes.init();
        FerBiomes.registerBiomes();
    }

    private static void addStructureDimensionSpawning() {
        // Controls the dimension blacklisting
        ServerWorldEvents.LOAD.register((MinecraftServer minecraftServer, ServerWorld serverWorld) -> {

            if (serverWorld.getRegistryKey() == World.OVERWORLD) {
                // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
                Map<StructureFeature<?>, StructureConfig> tempMap = new HashMap<>(serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

                // Add out structure so json defined dimensions can spawn it
                tempMap.put(FerStructureFeatures.EXAMPLE_BUSH, StructuresConfig.DEFAULT_STRUCTURES.get(FerStructureFeatures.EXAMPLE_BUSH));
                tempMap.put(FerStructureFeatures.LUST_GATE, StructuresConfig.DEFAULT_STRUCTURES.get(FerStructureFeatures.LUST_GATE));

                // Set the new modified map of structure spacing to the dimension's chunkgenerator.
                ((StructuresConfigAccessor)serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(tempMap);
            }
        });
    }

}
