package mcDams.blocks.walls;

import mcDams.blocks.basic.IDamPart;
import mcDams.blocks.valves.IValve;

/**
 * Interface to define Damm Walls.
 * @author Ralle030583
 */
public interface IWall extends IDamPart{

	
	/**
	 * Return the Tier Level of the Wall.
	 * @return
	 */
	int getTierLevel();

	/**
	 * Lower Parts of a Damm has to be Base Parts. (idea for further releases)
	 * @return allowed to be buttom part of a dam
	 */
	boolean isBase();
	
}
