package net.kwzii.firstmod.block.entity;

import net.kwzii.firstmod.item.ModItems;
import net.kwzii.firstmod.screen.BettingTerminalMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BettingTerminalBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(2);
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 50;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public BettingTerminalBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BETTING_TERMINAL_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch(i) {
                    case 0 -> BettingTerminalBlockEntity.this.progress;
                    case 1 -> BettingTerminalBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch(i) {
                    case 0 -> BettingTerminalBlockEntity.this.progress = i1;
                    case 1 -> BettingTerminalBlockEntity.this.maxProgress = i1;
                };
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.firstmod.betting_terminal_block");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new BettingTerminalMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("betting_terminal.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("betting_terminal.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (hasRecipe()) {
            progress++;
            setChanged(pLevel, pPos, pState);

            if (progress >= maxProgress) {
                completeBet();
                progress = 0;
            }
        } else {
            progress = 0;
        }
    }

    private void completeBet() {
        ItemStack result = new ItemStack(ModItems.SAPPHIRE.get(), 1);   // todo change to money
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        boolean hasMoney = this.itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.RAW_SAPPHIRE.get();    // todo change to money
        ItemStack result = new ItemStack(ModItems.SAPPHIRE.get());  // todo change to money

        return hasMoney && viableAmountOfOutput(result.getCount()) && openOutputSlot(result.getItem());
    }

    private boolean openOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item) || this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty();   // todo change to money
    }

    private boolean viableAmountOfOutput(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
