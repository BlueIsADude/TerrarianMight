package net.bluethedude.terrarianmight.entity.client;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.entity.custom.SpazmatismEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SpazmatismEntityModel<T extends SpazmatismEntity> extends SinglePartEntityModel<T> {

    public static final EntityModelLayer SPAZ = new EntityModelLayer(Identifier.of(TerrarianMight.MOD_ID, "spaz"), "main");
	private final ModelPart head;

	public SpazmatismEntityModel(ModelPart root) {
		this.head = root.getChild(EntityModelPartNames.HEAD);
	}

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 20.0F, 0.0F));

        head.addChild("tail_1", ModelPartBuilder.create().uv(-1, 16).cuboid(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 4.0F, 0.0F, 0.0F, -0.7854F));
        head.addChild("tail_2", ModelPartBuilder.create().uv(-1, 16).mirrored().cuboid(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 7.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.7854F));

        return TexturedModelData.of(modelData, 32, 32);
    }

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		head.render(matrices, vertexConsumer, light, overlay, color);
	}

    @Override
    public ModelPart getPart() {
        return head;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * (float) (Math.PI / 180.0);
        this.head.yaw = headYaw * (float) (Math.PI / 180.0);
    }
}