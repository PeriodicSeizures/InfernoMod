package com.crazicrafter1.inferno.world.biome;

import com.crazicrafter1.inferno.Inferno;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class FerBiomes {

    public static final RegistryKey<Biome> ELYSIAN_FIELDS_KEY = registerKey("elysian_fields");
    public static final RegistryKey<Biome> ELYSIAN_LAKES_KEY = registerKey("elysian_lakes");
    public static final Biome ELYSIAN_FIELDS = createElysianFields();
    public static final Biome ELYSIAN_LAKES = createElysianLakes();

    private static Biome createElysianFields() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        //DefaultBiomeFeatures.addForestFlowers(generationSettings);
        generationSettings.surfaceBuilder(FerSurfaceConfigs.ELYSIAN_SURFACE_BUILDER);

        DefaultBiomeFeatures.addDefaultGrass(generationSettings);

        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.PLAINS)
                .depth(.05f).scale(.03f).temperature(.8f).downfall(.4f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x39DDE8).waterFogColor(0x050533)
                        .fogColor(0xc0d8ff).skyColor(0x4AB6FF).build()
                )
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    private static Biome createElysianLakes() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        //DefaultBiomeFeatures.addForestFlowers(generationSettings);
        generationSettings.surfaceBuilder(FerSurfaceConfigs.ELYSIAN_SURFACE_BUILDER);

        DefaultBiomeFeatures.addDefaultGrass(generationSettings);

        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN)
                .depth(-.09f).scale(.08f).temperature(.8f).downfall(.4f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x39DDE8).waterFogColor(0x050533)
                        .fogColor(0xc0d8ff).skyColor(0x4AB6FF).build()
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
        Registry.register(BuiltinRegistries.BIOME, ELYSIAN_LAKES_KEY.getValue(), ELYSIAN_LAKES);

        OverworldBiomes.addContinentalBiome(ELYSIAN_FIELDS_KEY, OverworldClimate.DRY, 10);
        OverworldBiomes.addEdgeBiome(ELYSIAN_FIELDS_KEY, ELYSIAN_LAKES_KEY, 7);
        //OverworldBiomes.setRiverBiome(ELYSIAN_FIELDS_KEY, null);
        //OverworldBiomes.addBiomeVariant(BiomeKeys.PLAINS, ELYSIAN_FIELDS_KEY, 1);
    }

    public static void init() {
        Inferno.LOGGER.info("Registering biomes");
    }

}
