package io.github.eljireh.entity.custom;

import io.github.eljireh.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SandBombHorseEntity extends ExplosiveHorseEntity {
    private int explosionRadius = 14;

    public SandBombHorseEntity(EntityType<? extends ExplosiveHorseEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override // Changement du BreedGoal
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2));
        this.goalSelector.addGoal(1, new RunAroundLikeCrazyGoal(this, 1.2));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, SandBombHorseEntity.class));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.7));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        if (this.canPerformRearing()) {
            this.goalSelector.addGoal(9, new RandomStandGoal(this));
        }

        this.addBehaviourGoals();
    }

    public static AttributeSupplier.Builder createBaseHorseAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60F)
                .add(Attributes.JUMP_STRENGTH, 0.7)
                .add(Attributes.MOVEMENT_SPEED, 0.21F)
                .add(Attributes.STEP_HEIGHT, 1.0)
                .add(Attributes.SAFE_FALL_DISTANCE, 6.0)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER, 0.5);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.SAND_BOMB_HORSE.get().create(pLevel);
    }

    @Override
    protected void explodeHorse() {
        this.dead = true;
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float) explosionRadius, Level.ExplosionInteraction.MOB);
        this.spawnLingeringCloud();
        this.triggerOnDeathMobEffects(Entity.RemovalReason.KILLED);
        this.discard();
    }
}
