// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.bluethedude.terrarianmight.entity.client;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.entity.custom.MagicBoltEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MagicBoltEntityModel<T extends MagicBoltEntity> extends EntityModel<T> {

    private final ModelPart bolt;
    public static final EntityModelLayer MAGIC_BOLT = new EntityModelLayer(Identifier.of(TerrarianMight.MOD_ID, "magic_bolt"), "main");

	public MagicBoltEntityModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
		this.bolt = root.getChild("bolt");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("bolt", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );
        return TexturedModelData.of(modelData, 16, 16);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		bolt.render(matrices, vertexConsumer, light, overlay, color);
	}
}