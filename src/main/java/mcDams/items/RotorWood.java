package mcDams.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RotorWood extends AbstractRotor{

	public RotorWood(int par1) {
		super(par1);
		this.setMaxDamage(30);
		
	}

	@Override
	protected String getName() {
		return "woodrotor";
	}

	@Override
	protected String getIngameName() {
		return "Wooden Rotor";
	}
	
	
	@Override
	protected void registerRecipes(){
		GameRegistry.addRecipe(new ItemStack(this), " x ","xxx"," x ", 'x', Item.getItemById(5));
		super.registerRecipes();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconregister){
		
		this.itemIcon = iconregister.registerIcon("mcdams:woodrotor");
	}
	

}
