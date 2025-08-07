package net.bluethedude.terrarianmight.item.custom;

import net.bluethedude.terrarianmight.TerrarianConfig;
import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class LifeFruitItem extends Item {
    public static final Identifier HEALTH_MODIFIER_ID = Identifier.of(TerrarianMight.MOD_ID, "crystal_boost");

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        final var attribute = user.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        boolean hasModifier = Objects.requireNonNull(attribute).hasModifier(HEALTH_MODIFIER_ID);
        int maxHealth = hasModifier ? (int) Objects.requireNonNull(attribute.getModifier(HEALTH_MODIFIER_ID)).value() + 20 : 20;

        ItemStack itemStack = user.getStackInHand(hand);
        FoodComponent foodComponent = itemStack.get(DataComponentTypes.FOOD);
        if (foodComponent != null && TerrarianConfig.fruitNeedsMaxHealth) {
            if (user.canConsume(foodComponent.canAlwaysEat())) {
                if (maxHealth >= TerrarianConfig.crystalMaxHealth) {
                    user.setCurrentHand(hand);
                    return TypedActionResult.consume(itemStack);
                } else {
                    user.sendMessage(Text.translatable("alert.terrarianmight.life_fruit_disallowed").formatted(Formatting.RED), true);
                    return TypedActionResult.fail(itemStack);
                }
            } else {
                return TypedActionResult.fail(itemStack);
            }
        } else if (foodComponent != null) {
            if (user.canConsume(foodComponent.canAlwaysEat())) {
                user.setCurrentHand(hand);
                return TypedActionResult.consume(itemStack);
            } else {
                return TypedActionResult.fail(itemStack);
            }
        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (user instanceof PlayerEntity playerEntity) {
            if (!world.isClient) {
                playerEntity.getItemCooldownManager().set(this, TerrarianConfig.fruitCooldown);
            }
            playerEntity.playSoundToPlayer(TerrarianSoundEvents.ITEM_LIFE_FRUIT_FINISH, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
        return itemStack;
    }

    public LifeFruitItem(Settings settings) {
        super(settings);
    }
}
