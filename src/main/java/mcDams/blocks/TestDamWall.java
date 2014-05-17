package mcDams.blocks;

import mcDams.blocks.basic.DamPart;
import mcDams.tileEntities.TileDamWall;
import mcDams.tileEntities.basic.TileDamPart;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * todo javadoc
 */

public class TestDamWall extends DamPart {
	public TestDamWall() {
		super();
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
		return new TileDamWall();
	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase player, ItemStack stack) {
		// thats stolen from k4unl.minecraft.Hydraulicraft
		int rotation = ((MathHelper
				.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
		ForgeDirection facing = ForgeDirection.getOrientation(rotation);
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity != null && entity instanceof TileDamPart) {
			if (((TileDamPart)entity).checkValidMultiblock(world, x, y, z)){
				System.out.println("Valid multiblock");
			}
		}
	}

	

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileDamWall();
	}

}