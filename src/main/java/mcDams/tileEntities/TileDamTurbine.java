package mcDams.tileEntities;

import mcDams.blocks.basic.DamPartType;
import mcDams.items.AbstractRotor;
import mcDams.tileEntities.basic.TileDamPart;
import mcDams.utils.McDamsWorldUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;

public class TileDamTurbine extends TileDamPart implements ISidedInventory, IPowerEmitter{

	private int ticks;
	public boolean isActive = true;
	private ItemStack[] slots = new ItemStack[10];
	private String inventoryName;
	private int transferCooldown = -1;
	private int DamMaxPower = 0;
	private boolean IsConnectedToDam = false;
	
	
	public TileDamTurbine() {
		super(DamPartType.TURBINE);
	}

	@Override
	public int getSizeInventory() {
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (this.slots[i] != null){
			
			return this.slots[i];
			
		}
		else{
			return null;
		}
		}
		
		

	
	

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.slots[i] != null) {
			ItemStack itemstack;

			if (this.slots[i].stackSize <= j) {
				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;
			} else {
				itemstack = this.slots[i].splitStack(j);

				if (this.slots[i].stackSize == 0) {
					this.slots[i] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.slots[i] != null) {
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		} else {
			return null;
		}
	}


	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;

		if (itemstack != null
				&& itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}



	@Override
	public int getInventoryStackLimit() {

		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false
				: entityplayer.getDistanceSq(this.xCoord + 0.5D,
						this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;

	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
	
		if (itemstack.getItem() instanceof AbstractRotor) {
			return true;
		}
		return false;
	}


	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
	
		return new int[1];
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// TODO needs testing - Ben
		
		
		if (itemstack.getItem() instanceof AbstractRotor) {
			
			
			if(slots[0] == null){
			return true;	
			}
			else{
				return false;
			}
			
			
			
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
	
		return false;
	}

	/**
	 * Checks if all Slots are Empty.
	 * @return true if empty.
	 */
	public boolean isEmpty() {
		for (ItemStack stack : this.slots) {
			if (stack != null && stack.stackSize > 0){
				return false;
			}
		}
		return true;
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.inventoryName
				: "Turbine";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.inventoryName != null && this.inventoryName.length() > 0;
	}
	
	
	
	
	
	
	
public void updateEntity(){
		
		
	updateDamStatus();
	
	
	CheckInventory();
		
		if (isActive){
			if(this.getStackInSlot(0) != null){
			generatePower();
			DeteriorateRotor(1);
			
			}
			
		}
			
		
	}
	

private void CheckInventory(){
	
	if(this.getStackInSlot(0) == null){
		for(int i = 1; i < 10; i++){
			if(this.getStackInSlot(i) != null){
				ItemStack tstack;
				tstack = this.getStackInSlot(i);
				this.setInventorySlotContents(i, null);
			this.setInventorySlotContents(0,tstack);	

			break;
			}
			}
			
		}
		}
	
	
	
	
	






private void updateDamStatus(){
		if (IsConnectedToDam = true){
		isActive = true;
	}
	else{
		isActive = false;
	}
}

	
	private void generatePower() {
		TileEntity tmp = null;

		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
			if ((tmp = McDamsWorldUtils.getEntityFromDirection(this.worldObj, this.xCoord, this.yCoord, this.zCoord, direction)) instanceof IPowerReceptor){
				IPowerReceptor receptor = (IPowerReceptor) tmp;
				PowerReceiver test = receptor.getPowerReceiver(direction);
				double testamount = 450.0;
				Type tempType = PowerHandler.Type.ENGINE;
				
				if (test == null){
					return; // NPE ;-)
				}
				
				
				test.receiveEnergy(tempType, testamount, ForgeDirection
						.getOrientation(ForgeDirection.OPPOSITES[direction
								.ordinal()]));
				
				
				// TODO Can't tell if we need this or not:
				test.update();
				
			}
		}

	}
	
	
	public void isConnectedToDam(boolean ValidDam, int NewDamMaxPower){
		  this.DamMaxPower = NewDamMaxPower;
		 IsConnectedToDam = ValidDam;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void DeteriorateRotor(int damage){
		
		 ItemStack itemstack;
		  itemstack = this.getStackInSlot(0);
		
		
		
	     if(itemstack != null){
	    	 
				ItemStack newStack = new ItemStack(
						itemstack.getItem(), 1,
						(itemstack.getItemDamage() + 1));
				if (newStack.getItemDamage() >= newStack.getMaxDamage()) {
					newStack = null;
				}
	    
				this.setInventorySlotContents(0, newStack);
	}
	
	
	
	
	
	
	
	
	
	
	}

	@Override
	public boolean canEmitPowerFrom(ForgeDirection side) {
		
	
		
		return true;
	}


	
	
	
	
	

}
