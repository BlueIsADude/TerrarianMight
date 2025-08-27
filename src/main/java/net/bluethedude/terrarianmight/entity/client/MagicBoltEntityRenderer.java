package net.bluethedude.terrarianmight.entity.client;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.entity.custom.MagicBoltEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class MagicBoltEntityRenderer extends EntityRenderer<MagicBoltEntity> {

    protected MagicBoltEntityModel model;
    public static final EntityModelLayer MAGIC_BOLT = new EntityModelLayer(Identifier.of(TerrarianMight.MOD_ID, "magic_bolt"), "main");

    public MagicBoltEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new MagicBoltEntityModel(context.getPart(MAGIC_BOLT));
    }

    @Override
    public Identifier getTexture(MagicBoltEntity entity) {
        return Identifier.of(TerrarianMight.MOD_ID, "textures/entity/projectiles/magic_bolt/amethyst.png");

    }

    protected int getBlockLight(MagicBoltEntity magicBoltEntity, BlockPos blockPos) {
        return 15;
    }

    @Override
    public void render(MagicBoltEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumers, this.model.getLayer(this.getTexture(entity)), false, false
        );
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
