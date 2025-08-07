package net.bluethedude.terrarianmight.block.custom;

import com.mojang.serialization.MapCodec;
import net.bluethedude.terrarianmight.TerrarianConfig;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class BuddingLifeCrystalBlock extends Block {
    public static final MapCodec<BuddingLifeCrystalBlock> CODEC = createCodec(BuddingLifeCrystalBlock::new);
    public static final int GROW_CHANCE = TerrarianConfig.crystalGrowthChance;
    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public MapCodec<BuddingLifeCrystalBlock> getCodec() {
        return CODEC;
    }

    public BuddingLifeCrystalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(GROW_CHANCE) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            Block block = null;
            if (canGrowIn(blockState)) {
                block = TerrarianBlocks.SMALL_LIFE_CRYSTAL_BUD;
            } else if (blockState.isOf(TerrarianBlocks.SMALL_LIFE_CRYSTAL_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = TerrarianBlocks.MEDIUM_LIFE_CRYSTAL_BUD;
            } else if (blockState.isOf(TerrarianBlocks.MEDIUM_LIFE_CRYSTAL_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = TerrarianBlocks.LARGE_LIFE_CRYSTAL_BUD;
            } else if (blockState.isOf(TerrarianBlocks.LARGE_LIFE_CRYSTAL_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = TerrarianBlocks.LIFE_CRYSTAL_CLUSTER;
            }

            if (block != null) {
                BlockState blockState2 = block.getDefaultState()
                        .with(AmethystClusterBlock.FACING, direction)
                        .with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }
        }
    }

    public static boolean canGrowIn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }
}
