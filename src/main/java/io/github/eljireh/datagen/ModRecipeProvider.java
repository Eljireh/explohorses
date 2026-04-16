package io.github.eljireh.datagen;

import io.github.eljireh.Block.ModBlocks;
import io.github.eljireh.ExplosiveHorses;
import io.github.eljireh.Item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) { // Liste crafts

        // HORSEY TNT
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HORSEY_TNT.get())
                .pattern("XOX")
                .pattern("OXO")
                .pattern("XOX")
                .define('X', Items.GUNPOWDER)
                .define('O', ModItems.SCRATCHED_HIDE.get())
                .unlockedBy(getHasName(ModItems.SCRATCHED_HIDE.get()), has(ModItems.SCRATCHED_HIDE.get()))
                .unlockedBy(getHasName(Items.GUNPOWDER), has(Items.GUNPOWDER))
                .save(pRecipeOutput);

        // IRON CARROT
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_CARROT.get())
                .pattern(" O ")
                .pattern("OXO")
                .pattern(" O ")
                .define('X', Items.CARROT)
                .define('O', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.CARROT), has(Items.CARROT))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HIDE_CAP.get())
                .pattern("XXX")
                .pattern("X X")
                .define('X', ModItems.SCRATCHED_HIDE.get())
                .unlockedBy(getHasName(ModItems.SCRATCHED_HIDE.get()), has(ModItems.SCRATCHED_HIDE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HIDE_TUNIC.get())
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.SCRATCHED_HIDE.get())
                .unlockedBy(getHasName(ModItems.SCRATCHED_HIDE.get()), has(ModItems.SCRATCHED_HIDE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HIDE_PANTS.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', ModItems.SCRATCHED_HIDE.get())
                .unlockedBy(getHasName(ModItems.SCRATCHED_HIDE.get()), has(ModItems.SCRATCHED_HIDE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HIDE_BOOTS.get())
                .pattern("X X")
                .pattern("X X")
                .define('X', ModItems.SCRATCHED_HIDE.get())
                .unlockedBy(getHasName(ModItems.SCRATCHED_HIDE.get()), has(ModItems.SCRATCHED_HIDE.get()))
                .save(pRecipeOutput);

        // BAD APPLE
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HEALING_APPLE.get())
                .requires(Items.WHITE_DYE)
                .requires(Items.BLACK_DYE)
                .requires(Items.APPLE)
                .unlockedBy(getHasName(ModItems.HEALING_APPLE.get()), has(ModItems.HEALING_APPLE.get()))
                .save(pRecipeOutput);

        // AMETRINE BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETRINE_BLOCK.get())
                .pattern("XX")
                .pattern("XX")
                .define('X', ModItems.AMETRINE.get())
                .unlockedBy(getHasName(ModItems.AMETRINE.get()), has(ModItems.AMETRINE.get()))
                .save(pRecipeOutput);

        // AMETRINE DEPUIS BLOC
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AMETRINE.get(), 4)
                .requires(ModBlocks.AMETRINE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.AMETRINE_BLOCK.get()), has(ModBlocks.AMETRINE_BLOCK.get()))
                .save(pRecipeOutput, ExplosiveHorses.MOD_ID + ":ametrine_from_block");

        // BRIQUET D'AMETRINE
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLINT_AND_AMETRINE.get())
                .requires(ModItems.AMETRINE.get())
                .requires(Items.FLINT)
                .unlockedBy(getHasName(ModItems.AMETRINE.get()), has(ModItems.AMETRINE.get()))
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT))
                .save(pRecipeOutput);

        // PORTE EN AMETRINE
        // Note : Ai préféré Shaped plutôt que doorBuilder car plus personnalisable
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETRINE_DOOR.get(), 4)
                .pattern("XX ")
                .pattern("XX ")
                .pattern("XX ")
                .define('X', ModBlocks.AMETRINE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.AMETRINE_BLOCK.get()), has(ModBlocks.AMETRINE_BLOCK.get()))
                .save(pRecipeOutput);


    }
}
