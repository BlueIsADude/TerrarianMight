package net.bluethedude.terrarianmight.item.custom;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.TerrarianConfig;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class LifeCrystalItem extends Item {
    public static final Identifier HEALTH_MODIFIER_ID = Identifier.of(TerrarianMight.MOD_ID, "crystal_boost");
    public LifeCrystalItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("tooltip.terrarianmight.life_crystal.use").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("tooltip.terrarianmight.life_crystal.stats", TerrarianConfig.crystalHealthGain).formatted(Formatting.BLUE));

        super.appendTooltip(stack, context, tooltip, type);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        final var stack = user.getStackInHand(hand);
        if (world.isClient) {
            return TypedActionResult.fail(stack);
        }
        final var attribute = user.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

        boolean hasModifier = Objects.requireNonNull(attribute).hasModifier(HEALTH_MODIFIER_ID);
        int maxHealth = hasModifier ? (int) Objects.requireNonNull(attribute.getModifier(HEALTH_MODIFIER_ID)).value() + 20 : 20;

        if (maxHealth >= TerrarianConfig.crystalMaxHealth) {
            user.sendMessage(Text.translatable("alert.terrarianmight.life_crystal_limit").formatted(Formatting.RED), true);
            return TypedActionResult.fail(stack);
        }

        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD,
                user.getPos().getX(),
                user.getPos().getY() + 1.2,
                user.getPos().getZ(),
                10, 0, 0, 0, 0.1);
        user.getItemCooldownManager().set(this, TerrarianConfig.crystalCooldown);
        user.playSoundToPlayer(TerrarianSoundEvents.ITEM_LIFE_CRYSTAL_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
        stack.decrementUnlessCreative(1, user);

        int increment = Math.min(TerrarianConfig.crystalHealthGain, TerrarianConfig.crystalMaxHealth - maxHealth);
        maxHealth += increment;

        int modifierValue = maxHealth - 20;

        EntityAttributeModifier modifier = new EntityAttributeModifier(HEALTH_MODIFIER_ID, modifierValue, EntityAttributeModifier.Operation.ADD_VALUE);
        attribute.overwritePersistentModifier(modifier);

        return TypedActionResult.success(stack);
    }
}
