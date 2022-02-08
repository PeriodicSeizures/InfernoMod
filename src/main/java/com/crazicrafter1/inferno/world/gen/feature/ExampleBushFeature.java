package com.crazicrafter1.inferno.world.gen.feature;

import com.crazicrafter1.inferno.Inferno;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.apache.logging.log4j.Level;

public class ExampleBushFeature extends StructureFeature<DefaultFeatureConfig> {

    public ExampleBushFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return Start::new;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig) {
        BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);

        // Grab height of land. Will stop at first non-air block.
        int landHeight = chunkGenerator.getHeightInGround(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);

        // Grabs column of blocks at given position. In overworld, this column will be made of stone, water, and air.
        // In nether, it will be netherrack, lava, and air. End will only be endstone and air. It depends on what block
        // the chunk generator will place for that dimension.
        BlockView columnOfBlocks = chunkGenerator.getColumnSample(centerOfChunk.getX(), centerOfChunk.getZ());

        // Combine the column of blocks with land height and you get the top block itself which you can test.
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.up(landHeight));

        // Now we test to make sure our structure is not spawning on water or other fluids.
        // You can do height check instead too to make it spawn at high elevations.
        return topBlock.getFluidState().isEmpty(); //landHeight > 100;
    }

    //https://github.com/TelepathicGrunt/StructureTutorialMod/blob/1.16.3-Fabric-Jigsaw/src/main/java/com/telepathicgrunt/structure_tutorial/structures/RunDownHouseStructure.java
    public static class Start extends MarginedStructureStart<DefaultFeatureConfig> {

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox blockBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, blockBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {

            // Get center of chunk
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;

            //If method_30419's last parameter is true, structure will spawn at terrain height.
            BlockPos.Mutable pos = new BlockPos.Mutable(x, 0, z);

            /*
             * If you are doing Nether structures, loop through the column of blocks to find air.
             * Use this for nether-like dimensions with ceilings
             */
            //BlockView blockView = chunkGenerator.getColumnSample(blockpos.getX(), blockpos.getZ());

            // All a structure has to do is call this method to turn it into a jigsaw based structure!
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN)
                            .get(new Identifier(Inferno.MOD_ID, "example_bush/start_pool")), // Starting Template Pool JSON path
                            10),// Max recursive jigsaw structures
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    pos, // Structure position
                    this.children, // Pieces to add to
                    this.random,
                    false, // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                    // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                    true); // True to set at terrain height

            // **THE FOLLOWING TWO LINES ARE OPTIONAL**
            //
            // Right here, you can do interesting stuff with the pieces in this.children such as offset the
            // center piece by 50 blocks up for no reason, remove repeats of a piece or add a new piece so
            // only 1 of that piece exists, etc. But you do not have access to the piece's blocks as this list
            // holds just the piece's size and positions. Blocks will be placed later in StructurePoolBasedGenerator.
            //
            // By lifting the house up by 1, the terraforming land at bottom of house will now be also raised up by 1.
            this.children.forEach(piece -> piece.translate(0, 1, 0));

            // Sets the bounds of the structure once you are finished.
            this.setBoundingBoxFromChildren();

            // Test
            Inferno.LOGGER.log(Level.DEBUG, "Example bush " +
                    this.children.get(0).getBoundingBox().minX + " " +
                    this.children.get(0).getBoundingBox().minY + " " +
                    this.children.get(0).getBoundingBox().minZ);
        }
    }
















}
