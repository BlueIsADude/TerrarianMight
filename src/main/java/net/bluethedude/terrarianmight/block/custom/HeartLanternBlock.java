package net.bluethedude.terrarianmight.block.custom;

import net.bluethedude.terrarianmight.TerrarianConfig;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.bluethedude.terrarianmight.particle.TerrarianParticleTypes;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HeartLanternBlock extends LanternBlock {

    @Override
    public ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Item item = stack.getItem();
        BlockState blockState = world.getBlockState(pos);
        Block block = null;
        if (stack.isOf(TerrarianItems.LIFE_SHARD) && TerrarianConfig.lanternRepair) {
            if (blockState.isOf(TerrarianBlocks.BROKEN_HEART_LANTERN)) {
                block = TerrarianBlocks.DAMAGED_HEART_LANTERN;
                stack.decrementUnlessCreative(1, player);
            } else if (blockState.isOf(TerrarianBlocks.DAMAGED_HEART_LANTERN)) {
                block = TerrarianBlocks.CHIPPED_HEART_LANTERN;
                stack.decrementUnlessCreative(1, player);
            } else if (blockState.isOf(TerrarianBlocks.CHIPPED_HEART_LANTERN)) {
                block = TerrarianBlocks.HEART_LANTERN;
                stack.decrementUnlessCreative(1, player);
            } else if (blockState.isOf(TerrarianBlocks.HEART_LANTERN)) {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }

            if (block != null) {
                BlockState blockState2 = block.getDefaultState()
                        .with(HANGING, blockState.get(HANGING))
                        .with(WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(pos, blockState2);
            }
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            player.playSoundToPlayer(TerrarianSoundEvents.BLOCK_HEART_LANTERN_REPAIR, SoundCategory.PLAYERS, 0.2f, 1.0f);
            return ItemActionResult.SUCCESS;
        }
        else {
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            BlockState blockState = world.getBlockState(pos);
            Block block = null;
            if (blockState.isOf(TerrarianBlocks.BROKEN_HEART_LANTERN)) {
                return ActionResult.FAIL;
            } else {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, TerrarianConfig.lanternDuration, TerrarianConfig.lanternAmplifier));
                ((ServerWorld) world).spawnParticles(TerrarianParticleTypes.LIFE_HEART,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        8, 0.25, 0.25, 0.25, 0);
            }
            if (blockState.isOf(TerrarianBlocks.HEART_LANTERN)) {
                player.playSoundToPlayer(TerrarianSoundEvents.BLOCK_HEART_LANTERN_CRACK1, SoundCategory.PLAYERS, 0.5f, 1.0f);
                block = TerrarianBlocks.CHIPPED_HEART_LANTERN;
            } else if (blockState.isOf(TerrarianBlocks.CHIPPED_HEART_LANTERN)) {
                player.playSoundToPlayer(TerrarianSoundEvents.BLOCK_HEART_LANTERN_CRACK2, SoundCategory.PLAYERS, 0.7f, 1.0f);
                block = TerrarianBlocks.DAMAGED_HEART_LANTERN;
            } else if (blockState.isOf(TerrarianBlocks.DAMAGED_HEART_LANTERN)) {
                player.playSoundToPlayer(TerrarianSoundEvents.BLOCK_HEART_LANTERN_CRACK3, SoundCategory.PLAYERS, 1.0f, 1.0f);
                block = TerrarianBlocks.BROKEN_HEART_LANTERN;
            }

            if (block != null) {
                BlockState blockState2 = block.getDefaultState()
                        .with(HANGING, blockState.get(HANGING))
                        .with(WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(pos, blockState2);
            }
        }
        return ActionResult.SUCCESS;
    }

    public HeartLanternBlock(Settings settings) {
        super(settings);
    }
}
