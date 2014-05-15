package mcDams.tileEntities;

import java.util.ArrayList;
import java.util.List;

import buildcraft.api.power.IPowerEmitter;
import mcDams.blocks.basic.IDamPart;
import mcDams.blocks.turbines.ITurbine;
import mcDams.blocks.valves.IValve;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * TileEntity for Damm Valves.
 * 
 * @author Ralle030583
 */
public class TileDamValve extends TileEntity {
	
	private List<IDamPart> registeredDamParts = new ArrayList<IDamPart>();
	private ForgeDirection orientation;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
	}

	/**
	 * Delivers the Orientation of the Dam.
	 * @return ForgeDirection 
	 */
	public ForgeDirection getOrientation(){
		return this.orientation;
	};
	
	/**
	 * Register a part to a Dam and delivieres if it was successfull
	 * @return
	 */
	public boolean registerPart(IDamPart part){
		if (part instanceof IValve){
			// We want only one Valve for each Dam, Noob!
			return false;
		}
		return this.registeredDamParts.add(part);
	}
	
	/**
	 * Deliveres the registered Turbines. 
	 * @return
	 */
	public List<ITurbine> getTurbines() {
		List<ITurbine> turbines = new ArrayList<ITurbine>();
		for (IDamPart part : this.registeredDamParts) {
			if (part instanceof ITurbine) {
				turbines.add((ITurbine)part);
			}
		}
		return turbines;
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound p_145839_1_) {
		orientation = ForgeDirection.getOrientation(p_145839_1_.getInteger("orientation"));
		
		// TODO registeredDamParts 
		super.readFromNBT(p_145839_1_);
	}
	 
	@Override
	public void writeToNBT(NBTTagCompound p_145841_1_) {
		p_145841_1_.setInteger("orientation", this.orientation.ordinal());
		
		// TODO registeredDamParts
		super.writeToNBT(p_145841_1_);
	}

	public void setOrientation(ForgeDirection orientation) {
		this.orientation = orientation; 
		
	}
	
}
