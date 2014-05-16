package mcDams.blocks.basic;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class DamPart extends Block {

	protected DamPart(Material mat) {
		super(mat);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase player, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, player, stack);
		
		List<DamPart> parts = getNeighbourDamParts();
		
		if (parts.isEmpty()){
			
		}
	}

	private List<DamPart> getNeighbourDamParts() {
		return null;
	}
}
