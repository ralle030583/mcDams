package mcDams.slots;

import mcDams.items.AbstractRotor;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class TurbineSlot extends Slot {

	public TurbineSlot(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		if (par1ItemStack.getItem() instanceof AbstractRotor){
			return true;
		}
		return false;
	}

}
