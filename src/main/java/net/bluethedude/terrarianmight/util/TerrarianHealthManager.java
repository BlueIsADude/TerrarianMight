package net.bluethedude.terrarianmight.util;

import net.bluethedude.terrarianmight.TerrarianConfig;
import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.item.custom.LifeCrystalItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class TerrarianHealthManager {

    public static final Identifier BASE_HEALTH_MODIFIER = Identifier.of(TerrarianMight.MOD_ID, "base_health");

    public static void registerHealthManager() {

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!(entity instanceof PlayerEntity player))
                return;

            var attr = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

            assert attr != null;
            if (!attr.hasModifier(BASE_HEALTH_MODIFIER)) {
                final var health = TerrarianConfig.baseHealth;

                EntityAttributeModifier modifier = new EntityAttributeModifier(BASE_HEALTH_MODIFIER, health - 20,
                        EntityAttributeModifier.Operation.ADD_VALUE);
                attr.addPersistentModifier(modifier);
                player.setHealth(health);
            }
        });

        ServerPlayerEvents.COPY_FROM.register(((oldPlayer, newPlayer, arg2) -> {
            var oldAttr = oldPlayer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

            assert oldAttr != null;
            if (oldAttr.hasModifier(LifeCrystalItem.CRYSTAL_HEALTH_MODIFIER) && TerrarianConfig.crystalPermanentHealth) {
                final var oldModifier = oldAttr.getModifier(LifeCrystalItem.CRYSTAL_HEALTH_MODIFIER);
                assert oldModifier != null;
                int maxHealth = (int) oldModifier.value() + 20;

                Objects.requireNonNull(newPlayer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).addPersistentModifier(oldModifier);
                newPlayer.setHealth(maxHealth);
            }
            if (oldAttr.hasModifier(BASE_HEALTH_MODIFIER)) {
                final var oldModifier = oldAttr.getModifier(BASE_HEALTH_MODIFIER);
                assert oldModifier != null;
                int maxHealth = (int) oldModifier.value() + 20;

                Objects.requireNonNull(newPlayer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).addPersistentModifier(oldModifier);
                newPlayer.setHealth(maxHealth);
            }
        }));
    }
}
