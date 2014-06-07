package mcDams.utils;

import java.util.HashSet;
import java.util.Set;

import mcDams.blocks.basic.DamPartType;
import mcDams.tileEntities.TileDamFloodGate;
import mcDams.tileEntities.basic.TileDamPart;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.TileFluidHandler;

public final class McDamsWorldUtils {
	
	
	
	public static boolean isSurroundedByOtherDammPart(World world, int x, int y, int z){
		
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
			if (getEntityFromDirection(world, x, y, z, direction) instanceof TileDamPart){
				return true;
			}
		}
	
		return false;
	}
	
	public static boolean isValidPosition(World world, int x, int y, int z, DamPartType type){
		Set<TileDamPart> floodGates = new HashSet<TileDamPart>();
		boolean errorConnetionDifferentDams = false;
		
		// Checking surround Positions for a DammPart
		TileEntity tmp = null;
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
			if ((tmp = getEntityFromDirection(world, x, y, z, direction)) instanceof TileDamPart){
				floodGates.add(((TileDamPart) tmp).getConnectedValve());
			}
		}

		// 2 Different Dams found, abort
        if (floodGates.size() > 1){
        	return false;
        }
		
		// No Connected Damm, so only a new Dam is allowed
		if (floodGates.isEmpty()){
			if (type == DamPartType.FLOODGATE ){
				return true;
			}
			return false;
		} 

		final TileDamFloodGate floodGate = (TileDamFloodGate) floodGates.iterator().next();
		final ForgeDirection orientation = floodGate.getOrientation();
		
		// There is a Dam, lets check the orientation!
		if (type == DamPartType.WALL) {
			// Needs to be in line, and same level or above valve
			if (floodGate.yCoord > y){
				return false;
			}
			
			// If facing North or South, Part must be on same x coord
			if (orientation == ForgeDirection.NORTH || orientation == ForgeDirection.SOUTH){
				if (floodGate.xCoord != x){
					return false;
				}
			}
			// If facing East/West Part must be on same z coord
			if (orientation == ForgeDirection.EAST || orientation == ForgeDirection.WEST){
				if (floodGate.zCoord != z){
					return false;
				}
			}
		}
		
		if (type == DamPartType.TURBINE) {
			// Needs to be infront and on buttom
			// TODO
		}
		
		return true;
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
