package mcDams.blocks.valves;

import mcDams.tileEntities.TileDamValve;
import mcDams.utils.ConversionUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TestValve extends Block {

	public TestValve() {
		super(Material.rock);

		setStepSound(Block.soundTypeStone);
		setHardness(3.5F);
		setResistance(10F);
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
		return false;
	}

	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileDamValve();
	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase player, ItemStack stack) {

		if (!world.isRemote) {
			ForgeDirection facing = ConversionUtils.getFacingDirectionOfEntity(player);
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity != null && entity instanceof TileDamValve) {
				((TileDamValve) entity).setOrientation(facing);
			}
		}

	}

	

}
