package mcDams.blocks.basic;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class DamPart extends Block implements ITileEntityProvider{

	protected DamPart() {
		super(Material.rock);

		setStepSound(Block.soundTypeStone);
		setHardness(3.5F);
		setResistance(10F);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase player, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, player, stack);
	}

	private List<DamPart> getNeighbourDamParts() {
		return Lists.newArrayList();
	}
}
