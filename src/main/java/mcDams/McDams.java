package mcDams;

import javax.print.attribute.standard.MediaSize.Engineering;

import buildcraft.api.fuels.IronEngineCoolant;
import buildcraft.api.fuels.IronEngineCoolant.Coolant;
import buildcraft.api.power.PowerHandler;
import mcDams.blocks.turbines.TestTurbine;
import mcDams.blocks.valves.TestValve;
import mcDams.blocks.walls.TestDamWall;
import mcDams.guis.GuiHandler;
import mcDams.tileEntities.TileDamValve;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = McDams.MODID, version = McDams.VERSION, name = "Dams")
public class McDams {
	public static final String MODID = "mcDams";
	public static final String VERSION = "0.0.1";

	@Instance(value = "mcDams")
	public static McDams instance;
	
	public static Block testDamBlock;
	public static Block testValveBlock;
	public static Block testTurbine;

	public static final int guiTurbine = 0;
	//TEST 
	
	public static CreativeTabs McDamTab = new CreativeTabs("damtab") {
		public Item getTabIconItem() {
			return Items.bed;
		}
	};

	@EventHandler
	public void init(FMLInitializationEvent event) {

		// System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());

		
		GameRegistry.registerTileEntity(TileDamValve.class, "mcDamsValve");
		
		//NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		testDamBlock = new TestDamWall().setBlockName("testdam")
				.setCreativeTab(McDamTab)
				.setBlockTextureName(MODID + ":" + "testDam");
		GameRegistry.registerBlock(testDamBlock, "testDamblock");

		testValveBlock = new TestValve().setBlockName("testvalve")
				.setCreativeTab(McDamTab)
				.setBlockTextureName(MODID + ":" + "testValve");
		GameRegistry.registerBlock(testValveBlock, "testValveBlock");
		testTurbine = new TestTurbine().setBlockName("testturbine")
				.setCreativeTab(McDamTab)
				.setBlockTextureName(MODID + ":" + "testturbine");
		GameRegistry.registerBlock(testTurbine, "testturbineBlock");

	}
}
