package net.kwzii.firstmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            BlockPos posClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
//            boolean foundBlock = false;

            for(int i = 0; i <= posClicked.getY() + 64; i++) {
                BlockState state = pContext.getLevel().getBlockState(posClicked.below(i));

                if(isValuableBlock(state)) {
//                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, player) == 1)
                        player.sendSystemMessage(Component.literal("BEEP BEEP BEEP!"));
//                    else
//                        player.sendSystemMessage(Component.literal("BEEP BEEP BEEP!"));
                    // Just done as proof of concept, for other items
//                    outputValuableCoords(posClicked.below(i), player, state.getBlock());
//                    foundBlock = true;
                    break;
                }
            }

//            if(!foundBlock) {
//                player.sendSystemMessage(Component.literal("No Valuables Found!"));
//            }
        }

            // Use item durability
        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }


    private void outputValuableCoords(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId())
                + " at Y = " + blockPos.getY() + "!"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.IRON_ORE) || state.is(Blocks.COPPER_ORE)
                || state.is(Blocks.GOLD_ORE) || state.is(Blocks.ANCIENT_DEBRIS);
    }
}
