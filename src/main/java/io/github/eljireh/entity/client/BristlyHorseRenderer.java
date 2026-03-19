package io.github.eljireh.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.entity.custom.BristlyHorseEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BristlyHorseRenderer extends MobRenderer<BristlyHorseEntity, BristlyHorseModel<BristlyHorseEntity>> {
    public BristlyHorseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BristlyHorseModel<>(pContext.bakeLayer(BristlyHorseModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(BristlyHorseEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(ExplosiveHorses.MOD_ID, "textures/entity/bristly_horse.png");
    }

    @Override
    public void render(BristlyHorseEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            pPoseStack.scale(1f, 1f, 1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
