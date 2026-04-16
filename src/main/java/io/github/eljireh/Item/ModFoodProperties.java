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
            .usingConvertsTo(Items.IRON_NUGGET) // Quand mangé, laisse une pépite de fer
            .build();
            // Carotte de fer :
            // - Apprivoise les chevaux moddés/vanilla
            // - Si mangée : Restaure 4.5 gigots, gros effet de saturation, mais donne Lenteur III pendant 5 secondes

    public static final FoodProperties HEALING_APPLE = new FoodProperties.Builder()
            .nutrition(4) // 2 gigots
            .saturationModifier(2.4f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0f) // Régéneration II, 10 secondes, toujours
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 3), 1.0f) // Résistance IV, 10 secondes, toujours
            .build();
}

