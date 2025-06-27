package io.github.eljireh.Item;

import io.github.eljireh.ExplosiveHorses;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModCreativeModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExplosiveHorses.MOD_ID);

    public static final List<Supplier<? extends ItemLike>> CREATIVE_TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> CREA_TAB = CREATIVE_MOD_TABS.register("explohorses",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.SCRATCHED_HIDE.get()))
                    .title(Component.translatable("creativetab.explohorses.explosive_horses_items"))
                    .displayItems((itemDisplayParameters, output) ->
                        CREATIVE_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .displayItems((itemDisplayParameters, output) ->
                        CREATIVE_TAB_ITEMS.forEach(blockLike -> output.accept(blockLike.get())))
                    .build()
    );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        CREATIVE_TAB_ITEMS.add(itemLike);
        return itemLike;
    }

    public static <T extends Block> RegistryObject<T> addBlockToTab(RegistryObject<T> blockLike) {
        CREATIVE_TAB_ITEMS.add(blockLike);
        return blockLike;
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
