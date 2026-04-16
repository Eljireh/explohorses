package io.github.eljireh.event;

import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.entity.ModEntities;
import io.github.eljireh.entity.client.HenryHorseModel;
import io.github.eljireh.entity.client.BristlyHorseModel;
import io.github.eljireh.entity.client.SandBombHorseModel;
import io.github.eljireh.entity.custom.HenryHorseEntity;
import io.github.eljireh.entity.custom.BristlyHorseEntity;
import io.github.eljireh.entity.custom.SandBombHorseEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExplosiveHorses.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BristlyHorseModel.LAYER_LOCATION, BristlyHorseModel::createBodyLayer);
        event.registerLayerDefinition(SandBombHorseModel.LAYER_LOCATION, SandBombHorseModel::createBodyLayer);
        event.registerLayerDefinition(HenryHorseModel.LAYER_LOCATION, HenryHorseModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BRISTLY_HORSE.get(), BristlyHorseEntity.createBaseHorseAttributes().build());
        event.put(ModEntities.SAND_BOMB_HORSE.get(), SandBombHorseEntity.createBaseHorseAttributes().build());
        event.put(ModEntities.HENRY_HORSE.get(), HenryHorseEntity.createBaseHorseAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.BRISTLY_HORSE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
