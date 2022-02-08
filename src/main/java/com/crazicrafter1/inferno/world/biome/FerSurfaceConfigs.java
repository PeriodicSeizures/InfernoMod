package com.crazicrafter1.inferno.world.biome;

import com.crazicrafter1.inferno.Inferno;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class FerSurfaceConfigs {

    public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig>
            ELYSIAN_SURFACE_BUILDER = register("elysian_surface",
                    SurfaceBuilder.DEFAULT.withConfig(new TernarySurfaceConfig(
                            Blocks.GRASS_BLOCK.getDefaultState(),
                            Blocks.SAND.getDefaultState(),
                            Blocks.SANDSTONE.getDefaultState()
                    ))
            );

    private static <T extends SurfaceConfig> ConfiguredSurfaceBuilder<T> register(String name,
                                                                                  ConfiguredSurfaceBuilder<T> surfaceBuilder) {
        return Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER,
                new Identifier(Inferno.MOD_ID, name), surfaceBuilder);
    }

}
