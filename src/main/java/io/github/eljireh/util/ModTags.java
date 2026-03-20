package io.github.eljireh.util;

import io.github.eljireh.ExplosiveHorses;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> create_tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ExplosiveHorses.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> HORSE_IGNITERS = create_tag("horse_igniters");
        private static TagKey<Item> create_tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(ExplosiveHorses.MOD_ID, name));
        }
    }
}
