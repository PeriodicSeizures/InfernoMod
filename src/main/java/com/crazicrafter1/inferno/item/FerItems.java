package com.crazicrafter1.inferno.item;

import com.crazicrafter1.inferno.Inferno;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
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
            new Item(
                    new FabricItemSettings().food(
                            new FoodComponent.Builder().hunger(5).saturationModifier(1.2f).build())
                    .group(FerItemGroup.GROUP)));

    public static final Item CHALICE = registerItem("chalice",
            new Item(new FabricItemSettings().group(FerItemGroup.GROUP)));

    public static final Item AMBROSIA_CHALICE = registerItem("ambrosia_chalice",
            new Item(
                    new FabricItemSettings().food(
                                    new FoodComponent.Builder().hunger(16).saturationModifier(4.0f).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 20*180, 1), 1.f).build())
                            .group(FerItemGroup.GROUP)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Inferno.MOD_ID, name), item);
    }

    public static void init() {
        Inferno.LOGGER.info("Registering items");
    }
}
