package net.bluethedude.terrarianmight.block.custom;

import com.mojang.serialization.MapCodec;
import net.bluethedude.terrarianmight.world.TerrarianConfiguredFeatures;
import net.minecraft.block.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class GlowingMossBlock extends Block implements Fertilizable {
    public static final MapCodec<MossBlock> CODEC = createCodec(MossBlock::new);

    @Override
    public MapCodec<MossBlock> getCodec() {
        return CODEC;
    }

    public GlowingMossBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.getRegistryManager()
                .getOptional(RegistryKeys.CONFIGURED_FEATURE)
                .flatMap(key -> key.getEntry(TerrarianConfiguredFeatures.GLOWING_MOSS_PATCH_BONEMEAL))
                .ifPresent(entry -> entry.value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos.up()));
    }

    @Override
    public Fertilizable.FertilizableType getFertilizableType() {
        return Fertilizable.FertilizableType.NEIGHBOR_SPREADER;
    }
}
