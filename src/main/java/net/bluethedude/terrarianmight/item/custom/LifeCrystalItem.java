package net.bluethedude.terrarianmight.item.custom;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.TerrarianConfig;
import net.bluethedude.terrarianmight.particle.TerrarianParticleTypes;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class LifeCrystalItem extends Item {
    public static final Identifier CRYSTAL_HEALTH_MODIFIER = Identifier.of(TerrarianMight.MOD_ID, "crystal_health");
    public LifeCrystalItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("tooltip.terrarianmight.life_crystal.use").formatted(Formatting.GRAY));
        if (!TerrarianConfig.crystalPermanentHealth) {
            tooltip.add(Text.translatable("tooltip.terrarianmight.life_crystal.stats", TerrarianConfig.crystalHealthGain).formatted(Formatting.BLUE));
        } else {
            tooltip.add(Text.translatable("tooltip.terrarianmight.life_crystal.stats_permanent", TerrarianConfig.crystalHealthGain).formatted(Formatting.BLUE));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        final var attribute = user.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        boolean hasModifier = Objects.requireNonNull(attribute).hasModifier(CRYSTAL_HEALTH_MODIFIER);
        int maxHealth = hasModifier ? (int) Objects.requireNonNull(attribute.getModifier(CRYSTAL_HEALTH_MODIFIER)).value() + 20 : 20;

        if (maxHealth >= TerrarianConfig.crystalMaxHealth + 20) {
            user.sendMessage(Text.translatable("alert.terrarianmight.life_crystal_limit").formatted(Formatting.RED), true);
            return TypedActionResult.fail(itemStack);
        }
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                TerrarianSoundEvents.ITEM_LIFE_CRYSTAL_USE,
                SoundCategory.PLAYERS,
                1.0F,
                1.0F
        );
        if (!world.isClient) {
            ((ServerWorld) world).spawnParticles(TerrarianParticleTypes.LIFE_HEART,
                    user.getX(),
                    user.getY() + 0.75,
                    user.getZ(),
                    10, 0.3, 0.3, 0.3, 0.1
            );
            int increment = Math.min(TerrarianConfig.crystalHealthGain, TerrarianConfig.crystalMaxHealth + 20 - maxHealth);
            maxHealth += increment;

            int modifierValue = maxHealth - 20;

            EntityAttributeModifier modifier = new EntityAttributeModifier(CRYSTAL_HEALTH_MODIFIER, modifierValue, EntityAttributeModifier.Operation.ADD_VALUE);
            attribute.overwritePersistentModifier(modifier);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        user.getItemCooldownManager().set(this, TerrarianConfig.crystalCooldown);
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack);
    }
}
