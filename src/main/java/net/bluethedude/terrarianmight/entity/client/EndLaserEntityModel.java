// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.bluethedude.terrarianmight.entity.client;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.entity.custom.EndLaserEntity;
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
public class EndLaserEntityModel<T extends EndLaserEntity> extends EntityModel<T> {

    private final ModelPart laser;
    public static final EntityModelLayer END_LASER = new EntityModelLayer(Identifier.of(TerrarianMight.MOD_ID, "end_laser"), "main");

	public EndLaserEntityModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
		this.laser = root.getChild("laser");
	}

    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("laser", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );
        return TexturedModelData.of(modelData, 16, 16);
	}

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		laser.render(matrices, vertexConsumer, light, overlay, color);
	}
}