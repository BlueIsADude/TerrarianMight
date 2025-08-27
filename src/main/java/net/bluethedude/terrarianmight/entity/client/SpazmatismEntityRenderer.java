package net.bluethedude.terrarianmight.entity.client;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.entity.custom.SpazmatismEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public class SpazmatismEntityRenderer extends MobEntityRenderer<SpazmatismEntity, SpazmatismEntityModel<SpazmatismEntity>> {
    private final Random random = Random.create();

    public SpazmatismEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SpazmatismEntityModel<>(context.getPart(SpazmatismEntityModel.SPAZ)), 0.5F);
    }

    public Vec3d getPositionOffset(SpazmatismEntity spazmatismEntity, float f) {
        if (spazmatismEntity.isAttacking()) {
            double d = 0.02 * spazmatismEntity.getScale();
            return new Vec3d(this.random.nextGaussian() * d, 0.0, this.random.nextGaussian() * d);
        } else {
            return super.getPositionOffset(spazmatismEntity, f);
        }
    }

    public Identifier getTexture(SpazmatismEntity spazmatismEntity) {
        return Identifier.of(TerrarianMight.MOD_ID, "textures/entity/twins/spazmatism.png");
    }
}
