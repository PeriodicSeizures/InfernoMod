package com.crazicrafter1.inferno.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;

import java.util.Random;

public class SingleBlockFeature extends Feature<SingleStateFeatureConfig> {

    public SingleBlockFeature(Codec<SingleStateFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, SingleStateFeatureConfig config) {
        world.setBlockState(pos, config.state, 2);
        return true;
    }
}
