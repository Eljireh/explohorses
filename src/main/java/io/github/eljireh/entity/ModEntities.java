package io.github.eljireh.entity;

import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.entity.custom.HenryHorseEntity;
import io.github.eljireh.entity.custom.BristlyHorseEntity;
import io.github.eljireh.entity.custom.SandBombHorseEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ExplosiveHorses.MOD_ID);

    public static final RegistryObject<EntityType<BristlyHorseEntity>> BRISTLY_HORSE =
            ENTITY_TYPES.register("bristly_horse", () -> EntityType.Builder.of(BristlyHorseEntity::new, MobCategory.CREATURE)
                    .sized(1.6f, 1.3965f).build("bristly_horse"));

    public static final RegistryObject<EntityType<SandBombHorseEntity>> SAND_BOMB_HORSE =
            ENTITY_TYPES.register("sand_bomb_horse", () -> EntityType.Builder.of(SandBombHorseEntity::new, MobCategory.CREATURE)
                    .sized(1.6f, 1.3965f).build("sand_bomb_horse"));

    public static final RegistryObject<EntityType<HenryHorseEntity>> HENRY_HORSE =
            ENTITY_TYPES.register("henry_horse", () -> EntityType.Builder.of(HenryHorseEntity::new, MobCategory.CREATURE)
                    .sized(1.6f, 1.3965f).build("henry_horse"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
