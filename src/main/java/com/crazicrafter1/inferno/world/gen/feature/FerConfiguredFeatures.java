package com.crazicrafter1.inferno.world.gen.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;

public enum FerConfiguredFeatures {
    ;

    //private static ConfiguredFeature<?, ?> SINGLE_BLOCK_BONE_BLOCK = register("single_block_bone_block", FerFeature.SINGLE_BLOCK.configure(new SingleStateFeatureConfig(Blocks.BONE_BLOCK.getDefaultState())));

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return (ConfiguredFeature) Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }


}
