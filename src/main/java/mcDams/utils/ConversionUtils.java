package mcDams.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;

public class ConversionUtils {

	
	/**
	 * Lets get the Facing Direction of a Entity...
	 * @param entity
	 * @return direction
	 */
	public static ForgeDirection getFacingDirectionOfEntity(EntityLivingBase entity) {
		// Formula from K-4UNL
		int side = MathHelper
				.floor_double(entity.rotationYaw / 90F + 0.5D) & 3; 
		
		ForgeDirection facing = ForgeDirection.UNKNOWN;
		
		switch (side) {
		case 0:
			facing = ForgeDirection.SOUTH;
			break;
		case 1:
			facing = ForgeDirection.WEST;
			break;
		case 2: 
			facing = ForgeDirection.NORTH;
			break;
		case 3: 
			facing = ForgeDirection.EAST;
			break;
 
		default:
			facing = ForgeDirection.UNKNOWN;;
			break;
		}
		return facing;
	}
}
