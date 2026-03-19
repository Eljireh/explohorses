package io.github.eljireh.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.entity.custom.SandBombHorseEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SandBombHorseModel<T extends SandBombHorseEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ExplosiveHorses.MOD_ID, "sand_bomb_horse"), "main");
    private final ModelPart fullbody;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart left_legs;
    private final ModelPart front_left_leg;
    private final ModelPart back_left_leg;
    private final ModelPart right_legs;
    private final ModelPart front_right_leg;
    private final ModelPart back_right_leg;

    public SandBombHorseModel(ModelPart root) {
        this.fullbody = root.getChild("fullbody");
        this.neck = this.fullbody.getChild("neck");
        this.head = this.neck.getChild("head");
        this.left_legs = this.fullbody.getChild("left_legs");
        this.front_left_leg = this.left_legs.getChild("front_left_leg");
        this.back_left_leg = this.left_legs.getChild("back_left_leg");
        this.right_legs = this.fullbody.getChild("right_legs");
        this.front_right_leg = this.right_legs.getChild("front_right_leg");
        this.back_right_leg = this.right_legs.getChild("back_right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition fullbody = partdefinition.addOrReplaceChild("fullbody", CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, new CubeDeformation(0.05F))
                .texOffs(42, 36).addBox(-1.5F, -8.0F, 5.0F, 3.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 6.0F));

        PartDefinition neck = fullbody.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(1, 36).addBox(-2.05F, -6.0F, -3.0F, 4.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(57, 37).addBox(-1.0F, -12.0F, 3.0F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -15.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 13).addBox(-2.0F, -11.0F, -2.0F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(19, 16).addBox(0.55F, -14.0F, 3.99F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 16).addBox(-2.55F, -14.0F, 3.99F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).addBox(-2.0F, -10.0F, -7.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition left_legs = fullbody.addOrReplaceChild("left_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, -6.0F));

        PartDefinition front_left_leg = left_legs.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(1.0F, -11.0F, -10.9F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition back_left_leg = left_legs.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(1.0F, -11.0F, 7.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_legs = fullbody.addOrReplaceChild("right_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, -6.0F));

        PartDefinition front_right_leg = right_legs.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-5.0F, -11.0F, -10.9F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition back_right_leg = right_legs.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-5.0F, -11.0F, 7.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void setupAnim(SandBombHorseEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(SandBombHorseAnimations.horsey_walking, limbSwing, limbSwingAmount, 1.2f, 2.5f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        fullbody.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return fullbody;
    }
}