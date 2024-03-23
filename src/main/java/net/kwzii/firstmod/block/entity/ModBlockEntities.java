package net.kwzii.firstmod.block.entity;

import net.kwzii.firstmod.FirstMod;
import net.kwzii.firstmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FirstMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<BettingTerminalBlockEntity>> BETTING_TERMINAL_BE =
            BLOCK_ENTITIES.register("betting_terminal_be",
                    () -> BlockEntityType.Builder.of(BettingTerminalBlockEntity::new,
                            ModBlocks.BETTING_TERMINAL_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
