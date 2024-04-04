package net.kwzii.firstmod.datagen;

import net.kwzii.firstmod.FirstMod;
import net.kwzii.firstmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FirstMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.DICE);
        simpleItem(ModItems.DOUBLE_DICE);

        simpleItem(ModItems.DUDEY34);
        simpleItem(ModItems.MAXWELL);

        simpleItem(ModItems.RAW_SAPPHIRE);
        simpleItem(ModItems.SAPPHIRE);

        simpleItem(ModItems.METAL_DETECTOR);

        simpleItem(ModItems.DOLLAR_BILL);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FirstMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
