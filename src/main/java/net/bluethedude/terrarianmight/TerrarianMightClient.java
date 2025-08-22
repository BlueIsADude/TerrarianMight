package net.bluethedude.terrarianmight;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.block.TerrarianWoodType;
import net.bluethedude.terrarianmight.entity.TerrarianBoats;
import net.bluethedude.terrarianmight.entity.TerrarianEntityTypes;
import net.bluethedude.terrarianmight.entity.custom.render.SlimeWolfEntityModel;
import net.bluethedude.terrarianmight.entity.custom.render.SlimeWolfEntityRenderer;
import net.bluethedude.terrarianmight.particle.LifeHeartParticle;
import net.bluethedude.terrarianmight.particle.TerrarianParticleTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;

public class TerrarianMightClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.SMALL_LIFE_CRYSTAL_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.MEDIUM_LIFE_CRYSTAL_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.LARGE_LIFE_CRYSTAL_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.LIFE_CRYSTAL_CLUSTER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.HEART_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.CHIPPED_HEART_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.DAMAGED_HEART_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.BROKEN_HEART_LANTERN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.YELLOW_WILLOW_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.YELLOW_WILLOW_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.POTTED_YELLOW_WILLOW_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(TerrarianBlocks.YELLOW_WILLOW_DOOR, RenderLayer.getCutout());

        ParticleFactoryRegistry.getInstance().register(TerrarianParticleTypes.LIFE_HEART, LifeHeartParticle.Factory::new);

        TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(TerrarianWoodType.YELLOW_WILLOW, TexturedRenderLayers.getSignTextureId(TerrarianWoodType.YELLOW_WILLOW));

        TerraformBoatClientHelper.registerModelLayers(TerrarianBoats.YELLOW_WILLOW_BOAT_ID, false);

        EntityModelLayerRegistry.registerModelLayer(SlimeWolfEntityModel.SLIME_WOLF, SlimeWolfEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(TerrarianEntityTypes.SLIME_WOLF, SlimeWolfEntityRenderer::new);
    }
}
