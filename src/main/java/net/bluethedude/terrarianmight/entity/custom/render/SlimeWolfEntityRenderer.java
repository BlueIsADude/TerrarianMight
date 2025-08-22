package net.bluethedude.terrarianmight.entity.custom.render;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.entity.custom.SlimeWolfEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SlimeWolfEntityRenderer extends MobEntityRenderer<SlimeWolfEntity, SlimeWolfEntityModel<SlimeWolfEntity>> {
    public SlimeWolfEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SlimeWolfEntityModel<>(context.getPart(SlimeWolfEntityModel.SLIME_WOLF)), 0.5F);
    }

    protected float getAnimationProgress(SlimeWolfEntity slimeWolfEntity, float f) {
        return slimeWolfEntity.getTailAngle();
    }

    public Identifier getTexture(SlimeWolfEntity slimeWolfEntity) {
        return Identifier.of(TerrarianMight.MOD_ID, "textures/entity/slime_wolf/slime_wolf.png");
    }
}
