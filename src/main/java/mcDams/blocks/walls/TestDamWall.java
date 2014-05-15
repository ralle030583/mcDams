package mcDams.blocks.walls;

import mcDams.tileEntities.TileDamValve;
import mcDams.tileEntities.TileDamWall;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TestDamWall extends Block


{
public TestDamWall()
{
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
	return new TileDamWall();
}

public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack){
	// thats stolen from k4unl.minecraft.Hydraulicraft 
	int rotation = ((MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
	ForgeDirection facing = ForgeDirection.getOrientation(rotation);
	TileEntity entity = world.getTileEntity(x, y, z);
	if(entity != null && entity instanceof TileDamValve){
		((TileDamValve)entity).setOrientation(facing);
	}
}


}