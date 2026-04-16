package io.github.eljireh.entity.custom;

import io.github.eljireh.Item.ModItems;
import io.github.eljireh.entity.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

public class HenryHorseEntity extends ExplosiveHorseEntity {
    private int explosionRadius = 3;

    public HenryHorseEntity(EntityType<? extends ExplosiveHorseEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override // Changement du BreedGoal
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2));
        this.goalSelector.addGoal(1, new RunAroundLikeCrazyGoal(this, 1.2));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, HenryHorseEntity.class));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.7));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        if (this.canPerformRearing()) {
            this.goalSelector.addGoal(9, new RandomStandGoal(this));
        }

        this.addBehaviourGoals();
    }

    @Override // TemptGoal via Bad Apple!! plutôt que Carotte Ferreuse
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, ItemStack -> ItemStack.is(ModItems.HEALING_APPLE.get()), false));
    }

    public static AttributeSupplier.Builder createBaseHorseAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30F)
                .add(Attributes.JUMP_STRENGTH, 0.7)
                .add(Attributes.MOVEMENT_SPEED, 0.21F)
                .add(Attributes.STEP_HEIGHT, 1.0)
                .add(Attributes.SAFE_FALL_DISTANCE, 6.0)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER, 0.5);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.HENRY_HORSE.get().create(pLevel);
    }

    @Override
    protected void explodeHorse() {
        this.dead = true;
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float) explosionRadius, Level.ExplosionInteraction.MOB);
        this.spawnLingeringCloud();
        this.triggerOnDeathMobEffects(Entity.RemovalReason.KILLED);
        this.discard();
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModItems.IRON_CARROT.get()) || pStack.is(ModItems.HEALING_APPLE.get());
    }

    @Override
    protected boolean handleEating(Player pPlayer, ItemStack pStack) {
        boolean flag = false;

        if (pStack.is(ModItems.HEALING_APPLE.get())) {
            flag = true;
            if (this.getHealth() < this.getMaxHealth()) {
                this.heal(this.getMaxHealth());
            }

            if (!this.level().isClientSide && !this.isInLove()) {
                this.setInLove(pPlayer);

                if (!this.isTamed() && !this.isBaby()) {
                    // Possibilité de retirer la condition pour permettre la reproduction dès l'apprivoisement
                    this.resetLove();
                }

                if ((!this.isTamed()) && !this.level().isClientSide) {
                    this.setTamed(true);
                    flag = true;
                }

            }
        }

        if (pStack.is(ModItems.IRON_CARROT.get())) {
            if (this.isTamed() || this.isBaby()) {
                if (this.getHealth() < this.getMaxHealth()) {
                    this.heal(3.0F);
                    flag = true;
                }
                if (this.isBaby()) {
                    this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
                    if (!this.level().isClientSide) {
                        this.ageUp(60);
                        flag = true;
                    }
                }
            }
        }

        if (flag) {
            this.eating();
            this.gameEvent(GameEvent.EAT);
        }

        return flag;
    }

}