package com.crazicrafter1.inferno.world.biome;

import com.crazicrafter1.inferno.Inferno;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class FerBiomes {

    public static final RegistryKey<Biome> ELYSIAN_FIELDS_KEY = registerKey("elysian_fields");
    public static final Biome ELYSIAN_FIELDS = createElysianFields();

    private static Biome createElysianFields() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        DefaultBiomeFeatures.addForestFlowers(generationSettings);
        generationSettings.surfaceBuilder(FerSurfaceConfigs.ELYSIAN_SURFACE_BUILDER);

        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultLakes(generationSettings);
        generationSettings.feature(GenerationStep.Feature.LAKES, ConfiguredFeatures.LAKE_WATER);

        DefaultBiomeFeatures.addSprings(generationSettings);

        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE)
                .depth(.125f).scale(.05f).temperature(.8f).downfall(.4f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x3984E8).waterFogColor(0x050533)
                        .fogColor(0xc0d8ff).skyColor(0x77adff).build()
                )
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    public static RegistryKey<Biome> registerKey(String name) {
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(Inferno.MOD_ID, name));
    }

    public static void registerBiomes() {
        Registry.register(BuiltinRegistries.BIOME, ELYSIAN_FIELDS_KEY.getValue(), ELYSIAN_FIELDS);

        OverworldBiomes.addContinentalBiome(ELYSIAN_FIELDS_KEY, OverworldClimate.DRY, 10);
    }

    public static void init() {
        Inferno.LOGGER.info("Registering biomes");
    }

}
