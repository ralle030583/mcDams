package mcDams;

import javax.print.attribute.standard.MediaSize.Engineering;

import buildcraft.api.fuels.IronEngineCoolant;
import buildcraft.api.fuels.IronEngineCoolant.Coolant;
import buildcraft.api.power.PowerHandler;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = McDams.MODID, version = McDams.VERSION, name="Dams")
public class McDams
{
    public static final String MODID = "mcDams";
    public static final String VERSION = "0.0.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
        // kentben was here
        //
         
    }
}
