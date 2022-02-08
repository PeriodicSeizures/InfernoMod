package com.crazicrafter1.inferno.item;

import com.crazicrafter1.inferno.Inferno;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class FerItemGroup {
    public static ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(Inferno.MOD_ID, "inferno"),
            () -> new ItemStack(FerItems.AMBROSIA)
    );

}
