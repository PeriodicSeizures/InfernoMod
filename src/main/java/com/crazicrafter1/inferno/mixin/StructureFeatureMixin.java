package com.crazicrafter1.inferno.mixin;

import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StructureFeature.class)
public class StructureFeatureMixin {

    @Invoker("register")
    public static <F extends StructureFeature<?>> F register(String name, F structureFeature, GenerationStep.Feature step) {
        throw new AssertionError();
    }

}
