package mcDams;

import javax.print.attribute.standard.MediaSize.Engineering;

import buildcraft.api.fuels.IronEngineCoolant;
import buildcraft.api.fuels.IronEngineCoolant.Coolant;
import buildcraft.api.power.PowerHandler;
import mcDams.blocks.TestDam;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = McDams.MODID, version = McDams.VERSION, name="Dams")
public class McDams
{
    public static final String MODID = "mcdams";
    public static final String VERSION = "0.0.1";
    
    
    public static Block testDamBlock;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {

        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());

 
        testDamBlock = new TestDam().setBlockName("testDam");
        
		GameRegistry.registerBlock(testDamBlock,  "testDamblockname");

      
    }
}
