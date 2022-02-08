package com.crazicrafter1.inferno.world.gen.feature;

import com.crazicrafter1.inferno.Inferno;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class FerStructureFeatures {

    public static final StructureFeature<DefaultFeatureConfig> EXAMPLE_BUSH = new ExampleBushFeature(DefaultFeatureConfig.CODEC);

    //https://github.com/TelepathicGrunt/StructureTutorialMod/blob/87b6844beaed0830241389e626db6dc9edf789d1/src/main/java/com/telepathicgrunt/structure_tutorial/STStructures.java#L28
    public static void register() {
        FabricStructureBuilder.create(new Identifier(Inferno.MOD_ID, "example_bush"), EXAMPLE_BUSH)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(
                        10, // avg chunks apart
                        5, // min chunks apart
                        399117345 // seedish
                ))
                .superflatFeature(EXAMPLE_BUSH.configure(FeatureConfig.DEFAULT))
                .adjustsSurface()
                .register();
    }

}
