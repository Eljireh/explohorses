package io.github.eljireh.entity.custom;

import io.github.eljireh.Item.ModItems;
import io.github.eljireh.util.ModTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public abstract class ExplosiveHorseEntity extends Horse {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int explosionRadius;

    public ExplosiveHorseEntity(EntityType<? extends Horse> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override // Permet l'amour des petits sans reproduction
    public boolean canMate(Animal pOtherAnimal) {
        if (pOtherAnimal == this) {
            return false;
        } else {
            return pOtherAnimal.getClass() != this.getClass() ? false : !this.isBaby() && this.isInLove() && pOtherAnimal.isInLove();
        }
    }

    @Override // Changement du BreedGoal
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2));
        this.goalSelector.addGoal(1, new RunAroundLikeCrazyGoal(this, 1.2));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.7));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        if (this.canPerformRearing()) {
            this.goalSelector.addGoal(9, new RandomStandGoal(this));
        }

        this.addBehaviourGoals();
    }

    @Override // Changement du TemptGoal
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, ItemStack -> ItemStack.is(ModItems.IRON_CARROT.get()), false));
    }

    @Override // Pour ne pas randomiser MaxHealth
    protected void randomizeAttributes(RandomSource pRandom) {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(generateSpeed(pRandom::nextDouble));
        this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(generateJumpStrength(pRandom::nextDouble));
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModItems.IRON_CARROT.get());
    }

    protected void eating() {
        if (!this.isSilent()) {
            SoundEvent soundevent = this.getEatingSound();
            if (soundevent != null) {
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                soundevent,
                                this.getSoundSource(),
                                1.0F,
                                1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F
                        );
            }
        }
    }

    @Override
    protected boolean handleEating(Player pPlayer, ItemStack pStack) {
        boolean flag = false;
        float f = 0.0F;
        int i = 0;
        int j = 0;
        if (pStack.is(ModItems.IRON_CARROT.get())) {
            f = 3.0F;
            i = 60;
            j = 3;

            if (!this.level().isClientSide && this.isTamed() && this.getAge() == 0 && !this.isInLove()) {
                flag = true;
                this.setInLove(pPlayer);
            }
        }

        if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
            this.heal(f);
            flag = true;
        }

        if (this.isBaby() && i > 0) {
            this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
            if (!this.level().isClientSide) {
                this.ageUp(i);
                flag = true;
            }
        }

        if (j > 0 && (flag || !this.isTamed()) && this.getTemper() < this.getMaxTemper() && !this.level().isClientSide) {
            this.modifyTemper(j);
            flag = true;
        }

        if (flag) {
            this.eating();
            this.gameEvent(GameEvent.EAT);
        }

        return flag;
    }


    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (!this.isVehicle() && !pPlayer.isSecondaryUseActive()) {
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            if (!itemstack.isEmpty()) {
                if (!this.isTamed() && isFood(itemstack)) {
                    return this.fedFood(pPlayer, itemstack);
                }

                if (!this.isBaby() && itemstack.is(Items.GLASS_BOTTLE)) {
                    pPlayer.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
                    ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, pPlayer, ModItems.HORSEY_NITRO.get().getDefaultInstance());
                    pPlayer.setItemInHand(pHand, itemstack1);
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }

                if (itemstack.is(ModTags.Items.HORSE_IGNITERS)) {
                    SoundEvent soundevent = itemstack.is(Items.FIRE_CHARGE) ? SoundEvents.FIRECHARGE_USE : SoundEvents.FLINTANDSTEEL_USE;
                    this.level().playSound(pPlayer, this.getX(), this.getY(), this.getZ(), soundevent, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
                    this.explodeHorse();
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }

        }
        return super.mobInteract(pPlayer, pHand);
    }




    protected void explodeHorse() {
        this.dead = true;
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float) explosionRadius, Level.ExplosionInteraction.MOB);
        this.spawnLingeringCloud();
        this.triggerOnDeathMobEffects(Entity.RemovalReason.KILLED);
        this.discard();
    }


    protected void spawnLingeringCloud() {
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            areaeffectcloud.setRadius(2.5F);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());

            for (MobEffectInstance mobeffectinstance : collection) {
                areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
            }

            this.level().addFreshEntity(areaeffectcloud);
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    protected void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 60;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Override
    protected SoundEvent getAngrySound() {
        return SoundEvents.HORSE_BREATHE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) { return SoundEvents.DONKEY_HURT; }

    @Override
    protected SoundEvent getDeathSound() { return SoundEvents.TURTLE_DEATH; }
}

