package mcDams.containers;

import mcDams.slots.TurbineSlot;
import mcDams.tileEntities.TileDamTurbine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class TurbineContainer extends Container {

	
	private TileDamTurbine turbineentity;
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}
	
	public TurbineContainer(InventoryPlayer inventory, TileDamTurbine entity) {
		this.turbineentity = entity;

		this.addSlotToContainer(new TurbineSlot(entity, 0, 62, 19));


		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}

		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}

		//inventory.openChest();

	}


	
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.turbineentity.isUseableByPlayer(entityplayer);
	}

	@Override
	public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot) {
		return super.func_94530_a(par1ItemStack, par2Slot);
	}

	@Override
	public ItemStack slotClick(int par1, int par2, int par3,
			EntityPlayer par4EntityPlayer) {
		return super.slotClick(par1, par2, par3, par4EntityPlayer);
	}

	@Override
	protected boolean mergeItemStack(ItemStack par1ItemStack, int par2,
			int par3, boolean par4) {

		//if (!(par1ItemStack.getItem() instanceof AbstractCookieItem)){
		//	return false;
		//}

		return super.mergeItemStack(par1ItemStack, par2, par3, par4);
	}

	//copied method to deal with shift click, does not work but does prevent crash
	@Override

	    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	    {
	        ItemStack itemstack = null;
	        Slot slot = (Slot)this.inventorySlots.get(par2);

	        if (slot != null && slot.getHasStack())
	        {
	            ItemStack itemstack1 = slot.getStack();
	            itemstack = itemstack1.copy();

	            if (par2 < this.turbineentity.getSizeInventory())
	            {
	                if (!this.mergeItemStack(itemstack1, this.turbineentity.getSizeInventory(), this.inventorySlots.size(), true))
	                {
	                    return null;
	                }
	            }
	            else if (!this.mergeItemStack(itemstack1, 0, this.turbineentity.getSizeInventory(), false))
	            {
	                return null;
	            }

	            if (itemstack1.stackSize == 0)
	            {
	                slot.putStack((ItemStack)null);
	            }
	            else
	            {
	                slot.onSlotChanged();
	            }
	        }

	        return itemstack;
	    }
	
	
}
