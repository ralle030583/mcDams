package mcDams.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RotorIron extends AbstractRotor{

	public RotorIron(int par1) {
		super(par1);
		this.setMaxDamage(500);
		
	}

	@Override
	protected String getName() {
		return "ironrotor";
	}

	@Override
	protected String getIngameName() {
		return "Iron Rotor";
	}
	
	
	@Override
	protected void registerRecipes(){
		GameRegistry.addRecipe(new ItemStack(this), " x ","xxx"," x ", 'x', Item.getItemById(10));
		super.registerRecipes();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconregister){
		
		this.itemIcon = iconregister.registerIcon("mcdams:ironrotor");
	}
	

}
