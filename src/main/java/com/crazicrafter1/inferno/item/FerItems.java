package com.crazicrafter1.inferno.item;

import com.crazicrafter1.inferno.Inferno;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum FerItems {
    ;

    // elysian plains for biome or fields

    //public static final Item ELYSIAN_GRASS_BLOCK = registerItem("elysian_grass_block",
    //        new Item(new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

    public static final Item AMBROSIA = registerItem("ambrosia",
            new Item(new FabricItemSettings().group(ItemGroup.FOOD)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Inferno.MOD_ID, name), item);
    }

    public static void registerItems() {
        Inferno.LOGGER.info("Registering items");
    }
}
