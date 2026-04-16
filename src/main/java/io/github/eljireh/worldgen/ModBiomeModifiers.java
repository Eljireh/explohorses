package io.github.eljireh.worldgen;

import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_AMETRINE_BLOCK = registerKey("add_ametrine_block");

    // EN : Ametrine Blocks are generated in stone and deepslate
    // FR : Le Bloc d'Amétrine est généré dans la roche et l'ardoise

    public static final ResourceKey<BiomeModifier> SPAWN_BRISTLY_HORSE = registerKey("spawn_bristly_horse");
    public static final ResourceKey<BiomeModifier> SPAWN_SAND_BOMB_HORSE = registerKey("spawn_sand_bomb_horse");
    public static final ResourceKey<BiomeModifier> SPAWN_HENRY_HORSE = registerKey("spawn_henry_horse");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_AMETRINE_BLOCK, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.AMETRINE_BLOCK_PLACE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(SPAWN_BRISTLY_HORSE, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.BRISTLY_HORSE.get(), 9, 2, 5))
        ));

        context.register(SPAWN_HENRY_HORSE, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.HENRY_HORSE.get(), 6, 2, 3))
        ));

        context.register(SPAWN_SAND_BOMB_HORSE, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DESERT)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.SAND_BOMB_HORSE.get(), 12, 1, 2))
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(ExplosiveHorses.MOD_ID, name));
    }

}
