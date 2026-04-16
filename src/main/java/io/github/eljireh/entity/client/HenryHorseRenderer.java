package io.github.eljireh.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.entity.custom.HenryHorseEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HenryHorseRenderer extends MobRenderer<HenryHorseEntity, HenryHorseModel<HenryHorseEntity>> {
    public HenryHorseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HenryHorseModel<>(pContext.bakeLayer(HenryHorseModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(HenryHorseEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(ExplosiveHorses.MOD_ID, "textures/entity/henry_horse.png");
    }

    @Override
    public void render(HenryHorseEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            pPoseStack.scale(1f, 1f, 1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
