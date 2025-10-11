package io.github.eljireh.Block;

import io.github.eljireh.Block.Custom.HorseyTntBlock;
import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.Item.ModCreativeModTab;
import io.github.eljireh.Item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ExplosiveHorses.MOD_ID);

    public static final RegistryObject<Block> AMETRINE_BLOCK = ModCreativeModTab.addBlockToTab(registerBlock("ametrine_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS))));

    public static final RegistryObject<Block> HORSEY_TNT = ModCreativeModTab.addBlockToTab(registerBlock("horsey_tnt",
            () -> new HorseyTntBlock(BlockBehaviour.Properties.of().instabreak().sound(SoundType.WOOL).ignitedByLava())));

    // Porte amétrine
    public static final RegistryObject<DoorBlock> AMETRINE_DOOR = ModCreativeModTab.addBlockToTab(registerBlock("ametrine_door",
            () -> new DoorBlock(BlockSetType.SPRUCE, BlockBehaviour.Properties.of().strength(1.5f).noOcclusion())));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
