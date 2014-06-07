package mcDams.blocks.basic;

import mcDams.blocks.TestDamWall;
import mcDams.blocks.TestFloodGate;
import mcDams.blocks.TestTurbine;
import mcDams.tileEntities.TileDamFloodGate;
import mcDams.utils.McDamsWorldUtils;
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

	@Override
	public boolean canPlaceBlockAt(World world, int x,
			int y, int z) {
		// First of all default placing rules .. 
		if (super.canPlaceBlockAt(world, x, y, z)) {
			// A DammPart needs to be placed connected to a valve or another DamPart
			// on the right axis
			if (this instanceof TestFloodGate){
				// Floodgate is the first block, and a damm isnt allowed to have more than one
				return !McDamsWorldUtils.isSurroundedByOtherDammPart(world, x, y, z); 
			}
			return McDamsWorldUtils.isSurroundedByOtherDammPart(world, x, y, z);
		}
		return false;
	}
	
}
