package net.bluethedude.terrarianmight.item.custom.util;

import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public abstract class AbstractSummonItem extends AbstractMagicItem {

    public AbstractSummonItem(Settings settings) {
        super(settings);
    }

    public int getSummonLifespan() {
        return 1;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity playerEntity) {
            if (!world.isClient) {
                this.spawnMinion(user.getWorld(), user.getX(), user.getY() + 0.5, user.getZ(), playerEntity, stack);
            }
            world.playSound(
                    null,
                    playerEntity.getX(),
                    playerEntity.getY(),
                    playerEntity.getZ(),
                    TerrarianSoundEvents.ITEM_MAGIC_ITEM_CAST_SPELL,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F)
            );
            if (!playerEntity.isCreative()) {
                setMana(stack, getMana(stack) - getManaCost());
            }
            playerEntity.swingHand(playerEntity.getActiveHand());
            playerEntity.getItemCooldownManager().set(this, getSummonLifespan());
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        return stack;
    }

    public void spawnMinion(World world, double x, double y, double z, PlayerEntity user, ItemStack stack) {}
}
