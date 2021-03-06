package mcDams.guis;

import mcDams.McDams;
import mcDams.containers.TurbineContainer;
import mcDams.tileEntities.TileDamTurbine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		if (entity != null) {

			switch (ID) {
			//case Cookies.guiGiftBox:
			case McDams.guiTurbine:
				if (entity instanceof TileDamTurbine) {
					return new TurbineContainer(player.inventory,
							(TileDamTurbine) entity);
				}
			}
		}

		return null;
	}
	
	

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity entity = world.getTileEntity(x, y, z);

		if (entity != null) {

			switch (ID) {

			case McDams.guiTurbine:

				if (entity instanceof TileDamTurbine) {
					return new TurbineGUI(player.inventory,
							(TileDamTurbine) entity);

				}

			}
		}

		return null;
	}
	
	
}
