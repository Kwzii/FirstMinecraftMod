package net.kwzii.firstmod.datagen;

import net.kwzii.firstmod.FirstMod;
import net.kwzii.firstmod.block.ModBlocks;
import net.kwzii.firstmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FirstMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.SAPPHIRE_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.BETTING_TERMINAL_BLOCK.get(),
                ModBlocks.GAMBLE_TABLE_BLOCK.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RAW_SAPPHIRE_BLOCK.get(),
                ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DUDEY34_BLOCK.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.SAPPHIRE_BLOCK.get());

//        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
//                .add();

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.RAW_SAPPHIRE_BLOCK.get(),
                ModBlocks.SAPPHIRE_BLOCK.get(), ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DUDEY34_BLOCK.get(),
                ModBlocks.BETTING_TERMINAL_BLOCK.get(), ModBlocks.GAMBLE_TABLE_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.GAMBLE_TABLE_BLOCK.get());

//        this.tag(BlockTags.MINEABLE_WITH_HOE).add

//        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add
    }
}
