package net.bluethedude.terrarianmight.entity.client;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.entity.custom.RetinazerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
@Environment(EnvType.CLIENT)
public class RetinazerEntityRenderer extends MobEntityRenderer<RetinazerEntity, RetinazerEntityModel<RetinazerEntity>> {
    public RetinazerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new RetinazerEntityModel<>(context.getPart(RetinazerEntityModel.REZ)), 0.5F);
    }

    public Identifier getTexture(RetinazerEntity retinazerEntity) {
        return Identifier.of(TerrarianMight.MOD_ID, "textures/entity/twins/retinazer.png");
    }
}
