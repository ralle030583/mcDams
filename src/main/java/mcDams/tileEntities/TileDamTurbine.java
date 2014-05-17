package mcDams.tileEntities;

import mcDams.blocks.basic.DamPartType;
import mcDams.items.AbstractRotor;
import mcDams.tileEntities.basic.TileDamPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public class TileDamTurbine extends TileDamPart implements ISidedInventory{

	
	private ItemStack[] slots = new ItemStack[1];
	private String inventoryName;
	private int transferCooldown = -1;
	
	
	public TileDamTurbine() {
		super(DamPartType.TURBINE);
	}

	@Override
	public int getSizeInventory() {
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.slots[i];
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

	//@Override
	//public String getInvName() {
		//return this.isInvNameLocalized() ? this.inventoryName
		//		: "container.hopper";
	//	return this.isInvNameLocalized() ? this.inventoryName
//				: "GiftBox";
//	}

	//@Override
	//public boolean isInvNameLocalized() {
	//	return this.inventoryName != null && this.inventoryName.length() > 0;

	//}

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
		// Only Cookies
		if (itemstack.getItem() instanceof AbstractRotor) {
			return true;
		}
		return false;
	}


	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO Auto-generated method stub
		return new int[4];
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
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

}
