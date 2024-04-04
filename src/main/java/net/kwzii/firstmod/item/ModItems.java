package net.kwzii.firstmod.item;

import net.kwzii.firstmod.FirstMod;
import net.kwzii.firstmod.item.custom.DiceItem;
import net.kwzii.firstmod.item.custom.DoubleDiceItem;
import net.kwzii.firstmod.item.custom.FuelItem;
import net.kwzii.firstmod.item.custom.MetalDetectorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAXWELL = ITEMS.register("maxwell",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DUDEY34 = ITEMS.register("dudey34",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DOLLAR_BILL = ITEMS.register("dollar_bill",
            () -> new FuelItem(new Item.Properties(), 400));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> DICE = ITEMS.register("dice",
            () -> new DiceItem(new Item.Properties()));

    public static final RegistryObject<Item> DOUBLE_DICE = ITEMS.register("double_dice",
            () -> new DoubleDiceItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
