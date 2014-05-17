package mcDams.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mcDams.McDams;
import net.minecraft.item.Item;

public abstract class  AbstractRotor extends Item{
	
	
	protected abstract String getName();
	protected abstract String getIngameName();
	
	
	public AbstractRotor (int par1){
		super();
		this.setUnlocalizedName(this.getName());
		this.setCreativeTab(McDams.McDamTab);
		this.setMaxStackSize(1);
		
		
	}
	
	public AbstractRotor register(){
		GameRegistry.registerItem(this,this.getUnlocalizedName());
		LanguageRegistry.addName(this, this.getIngameName());
		this.registerRecipes();
		return this;
		
		
	}
	
	protected void registerRecipes(){};

}
