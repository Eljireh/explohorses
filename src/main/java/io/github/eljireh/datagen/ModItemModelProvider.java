package io.github.eljireh.datagen;

import io.github.eljireh.Block.ModBlocks;
import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.Item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExplosiveHorses.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.AMETRINE.get());
        basicItem(ModItems.FLINT_AND_AMETRINE.get());
        basicItem(ModItems.HORSEY_NITRO.get());
        basicItem(ModItems.IRON_CARROT.get());
        basicItem(ModItems.SCRATCHED_HIDE.get());
        basicItem(ModItems.LOOK_AT_MY_CLEAN_HORSE_DISC.get());
        simpleBlockItem(ModBlocks.AMETRINE_DOOR);

        basicItem(ModItems.HIDE_CAP.get());
        basicItem(ModItems.HIDE_TUNIC.get());
        basicItem(ModItems.HIDE_PANTS.get());
        basicItem(ModItems.HIDE_BOOTS.get());


    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(ExplosiveHorses.MOD_ID, "item/" + item.getId().getPath()));
    }
}
