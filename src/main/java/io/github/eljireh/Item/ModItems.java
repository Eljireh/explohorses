package io.github.eljireh.Item;

import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.Item.Custom.HorseyNitroItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExplosiveHorses.MOD_ID);

    public static final RegistryObject<Item> AMETRINE = ModCreativeModTab.addToTab(ITEMS.register("ametrine",
            () -> new Item(new Item.Properties())));

    public static final RegistryObject<Item> SCRATCHED_HIDE = ModCreativeModTab.addToTab(ITEMS.register("scratched_hide",
            () -> new Item(new Item.Properties())));

    public static final RegistryObject<Item> FLINT_AND_AMETRINE = ModCreativeModTab.addToTab(ITEMS.register("flint_and_ametrine",
            () -> new FlintAndSteelItem(new Item.Properties().durability(100))));

    public static final RegistryObject<Item> IRON_CARROT = ModCreativeModTab.addToTab(ITEMS.register("iron_carrot",
            () -> new Item(new Item.Properties().food(ModFoodProperties.IRON_CARROT))));

    public static final RegistryObject<Item> HORSEY_NITRO = ModCreativeModTab.addToTab(ITEMS.register("horsey_nitro",
            () -> new HorseyNitroItem(new Item.Properties())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
