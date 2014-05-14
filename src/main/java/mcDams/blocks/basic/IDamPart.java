package mcDams.blocks.basic;

import mcDams.blocks.valves.IValve;

/**
 * Defines a block as possible Part of a damm and 
 * needed functionallity.
 * @author Ralle030583
 *
 */
public interface IDamPart {

	/**
	 * Says if this Wall is already Part of a Dam.
	 * @return true
	 */
	boolean isPartOfDam();
	
	/**
	 * If {@link #isPartOfDam()} , it return the main Part of the damm, the Valve.
	 * @return
	 */
	IValve getValve();
}
