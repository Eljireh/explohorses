package io.github.eljireh.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.entity.custom.BadAppleEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BadAppleRenderer extends MobRenderer<BadAppleEntity, BadAppleModel<BadAppleEntity>> {
    public BadAppleRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BadAppleModel<>(pContext.bakeLayer(BadAppleModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(BadAppleEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(ExplosiveHorses.MOD_ID, "textures/entity/bad_apple_horse.png");
    }

    @Override
    public void render(BadAppleEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            pPoseStack.scale(1f, 1f, 1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
