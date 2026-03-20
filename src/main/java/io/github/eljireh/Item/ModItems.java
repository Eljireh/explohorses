package io.github.eljireh.Item;

import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.Item.Custom.HorseyNitroItem;
import io.github.eljireh.entity.ModEntities;
import io.github.eljireh.sound.ModSounds;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
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

    public static final RegistryObject<Item> BAD_APPLE = ModCreativeModTab.addToTab(ITEMS.register("bad_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BAD_APPLE))));

    public static final RegistryObject<Item> HORSEY_NITRO = ModCreativeModTab.addToTab(ITEMS.register("horsey_nitro",
            () -> new HorseyNitroItem(new Item.Properties())));

    public static final RegistryObject<Item> HIDE_CAP = ModCreativeModTab.addToTab(ITEMS.register("hide_cap",
            () -> new ArmorItem(ModArmorMaterials.HIDE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(15)))));

    public static final RegistryObject<Item> HIDE_TUNIC = ModCreativeModTab.addToTab(ITEMS.register("hide_tunic",
            () -> new ArmorItem(ModArmorMaterials.HIDE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(15)))));

    public static final RegistryObject<Item> HIDE_PANTS = ModCreativeModTab.addToTab(ITEMS.register("hide_pants",
            () -> new ArmorItem(ModArmorMaterials.HIDE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15)))));

    public static final RegistryObject<Item> HIDE_BOOTS = ModCreativeModTab.addToTab(ITEMS.register("hide_boots",
            () -> new ArmorItem(ModArmorMaterials.HIDE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(15)))));

    public static final RegistryObject<Item> AWESOME_GOD_DISC = ModCreativeModTab.addToTab(ITEMS.register("music_disc_awesome_god",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.AWESOME_GOD_KEY).stacksTo(1))));

    public static final RegistryObject<Item> LOOK_AT_MY_CLEAN_HORSE_DISC = ModCreativeModTab.addToTab(ITEMS.register("music_disc_look_at_my_clean_horse",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.LOOK_AT_MY_CLEAN_HORSE_KEY).stacksTo(1))));

    public static final RegistryObject<Item> BRISTLY_HORSE_SPAWN_EGG = ModCreativeModTab.addToTab(ITEMS.register("bristly_horse_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BRISTLY_HORSE, 0x454545, 0x3c3c3c, new Item.Properties())));

    public static final RegistryObject<Item> SAND_BOMB_HORSE_SPAWN_EGG = ModCreativeModTab.addToTab(ITEMS.register("sand_bomb_horse_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SAND_BOMB_HORSE, 0xb21300, 0xcbbd93, new Item.Properties())));

    public static final RegistryObject<Item> BAD_APPLE_SPAWN_EGG = ModCreativeModTab.addToTab(ITEMS.register("bad_apple_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BAD_APPLE_HORSE, 0xffffff, 0x000000, new Item.Properties())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
