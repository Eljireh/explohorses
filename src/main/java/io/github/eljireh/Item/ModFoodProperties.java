package io.github.eljireh.Item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoodProperties {
    public static final FoodProperties IRON_CARROT = new FoodProperties.Builder()
            .nutrition(9) // 4.5 gigots
            .saturationModifier(0.9f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0f) // Lenteur III, 5 secondes, toujours
            .usingConvertsTo(Items.IRON_NUGGET) // Mangé, laisse une pépite de fer
            .build();
            // Carotte de fer :
            // - Apprivoise les chevaux moddés/vanilla
            // - Si mangée : Restaure 4.5 gigots, gros effet de saturation, mais donne Lenteur III pendant 5 secondes


}
