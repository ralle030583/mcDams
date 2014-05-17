package mcDams.blocks;

import mcDams.McDams;
import mcDams.containers.TurbineContainer;
import mcDams.tileEntities.TileDamTurbine;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class TestTurbine extends BlockContainer {


	
	
	
	public TestTurbine() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileDamTurbine();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (!(tile instanceof TileDamTurbine)) {
			return false;
		}

		if (!world.isRemote) {

			FMLNetworkHandler.openGui(player, McDams.instance, 0, world, x, y,
					z);
		}

		return true;
	}

	
	
	
	
}

