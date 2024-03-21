package net.kwzii.firstmod.item;

import net.kwzii.firstmod.FirstMod;
import net.kwzii.firstmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FIRSTMOD_TAB = CREATIVE_MODE_TABS.register("firstmod_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MAXWELL.get()))
            .title(Component.translatable("creativetab.firstmod_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                    // Add items to mod tab
                for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
                    output.accept(item.get());
                }
                output.accept(Items.WET_SPONGE);

                    // Add blocks to mod tab
                for(RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()) {
                    output.accept(block.get());
                }
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
