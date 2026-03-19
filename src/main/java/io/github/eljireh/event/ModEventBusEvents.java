package io.github.eljireh.event;

import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.entity.ModEntities;
import io.github.eljireh.entity.client.BadAppleModel;
import io.github.eljireh.entity.client.BristlyHorseModel;
import io.github.eljireh.entity.client.SandBombHorseModel;
import io.github.eljireh.entity.custom.BadAppleEntity;
import io.github.eljireh.entity.custom.BristlyHorseEntity;
import io.github.eljireh.entity.custom.SandBombHorseEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExplosiveHorses.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BristlyHorseModel.LAYER_LOCATION, BristlyHorseModel::createBodyLayer);
        event.registerLayerDefinition(SandBombHorseModel.LAYER_LOCATION, SandBombHorseModel::createBodyLayer);
        event.registerLayerDefinition(BadAppleModel.LAYER_LOCATION, BadAppleModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BRISTLY_HORSE.get(), BristlyHorseEntity.createAttributes().build());
        event.put(ModEntities.SAND_BOMB_HORSE.get(), SandBombHorseEntity.createAttributes().build());
        event.put(ModEntities.BAD_APPLE_HORSE.get(), BadAppleEntity.createAttributes().build());
    }
}
