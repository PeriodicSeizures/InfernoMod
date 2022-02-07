package com.crazicrafter1.inferno.block;

import com.crazicrafter1.inferno.Inferno;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum FerBlocks {
    ;

    public static final Block ELYSIAN_WALL = registerBlock("elysian_wall",
            new Block(FabricBlockSettings.of(Material.SOIL)
                    .strength(1.0f).breakByTool(FabricToolTags.SHOVELS, 0)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(Inferno.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(Inferno.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS))
        );
    }

    public static void registerBlocks() {
        Inferno.LOGGER.info("Registering blocks");
    }











}
