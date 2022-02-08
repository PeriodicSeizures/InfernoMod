package com.crazicrafter1.inferno.world.gen.feature;

import com.crazicrafter1.inferno.Inferno;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public enum FerFeatureConfigs {
    ;
    public static ConfiguredStructureFeature<?, ?> CONFIGURED_EXAMPLE_BUSH
            = FerStructureFeatures.EXAMPLE_BUSH.configure(DefaultFeatureConfig.DEFAULT);

    public static void register() {
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE,
                new Identifier(Inferno.MOD_ID, "configured_example_bush"),
                CONFIGURED_EXAMPLE_BUSH);
    }

}
