package io.github.eljireh.datagen;

import io.github.eljireh.Block.ModBlocks;
import io.github.eljireh.ExplosiveHorses;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ExplosiveHorses.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.AMETRINE_BLOCK);
        //blockWithItem(ModBlocks.HORSEY_TNT);
        doorBlockWithRenderType(ModBlocks.AMETRINE_DOOR.get(),
                modLoc("block/ametrine_door_bottom"), modLoc("block/ametrine_door_top"), "cutout");
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}