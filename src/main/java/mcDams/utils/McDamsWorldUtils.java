package mcDams.utils;

import mcDams.tileEntities.basic.TileDamPart;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public final class McDamsWorldUtils {
	
	
	
	public static boolean isSurroundedByOtherDammPart(World world, int x, int y, int z){
		
		if (getEntityFromDirection(world, x, y, z, ForgeDirection.NORTH) instanceof TileDamPart){
			return true;
		}
		if (getEntityFromDirection(world, x, y, z, ForgeDirection.SOUTH) instanceof TileDamPart){
			return true;
		}
		if (getEntityFromDirection(world, x, y, z, ForgeDirection.EAST) instanceof TileDamPart){
			return true;
		}
		if (getEntityFromDirection(world, x, y, z, ForgeDirection.WEST) instanceof TileDamPart){
			return true;
		}
		if (getEntityFromDirection(world, x, y, z, ForgeDirection.UP) instanceof TileDamPart){
			return true;
		}
		if (getEntityFromDirection(world, x, y, z, ForgeDirection.DOWN) instanceof TileDamPart){
			return true;
		}
		
		return false;
	}
	
	public static TileEntity getEntityFromDirection(World world, int x, int y, int z, ForgeDirection direction){
		int x1 = x;
		int y1 = y;
		int z1 = z;
		
		if (direction.equals(ForgeDirection.NORTH)){
			z1--;
		}
		if (direction.equals(ForgeDirection.SOUTH)){
			z1++;
		}
		if (direction.equals(ForgeDirection.EAST)){
			x1++;
		}
		if (direction.equals(ForgeDirection.WEST)){
			x1--;
		}
		if (direction.equals(ForgeDirection.UP)){
			y1++;
		}
		if (direction.equals(ForgeDirection.DOWN)){
			y1--;
		}
		
		return world.getTileEntity(x1, y1, z1);
	}
	
}
