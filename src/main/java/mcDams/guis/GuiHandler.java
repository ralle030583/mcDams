package mcDams.guis;

import cpw.mods.fml.common.network.IGuiHandler;
import mcDams.McDams;
import mcDams.containers.TurbineContainer;
import mcDams.tileEntities.Tileturbine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		if (entity != null) {

			switch (ID) {
			//case Cookies.guiGiftBox:
			case McDams.guiTurbine:
				if (entity instanceof Tileturbine) {
					return new TurbineContainer(player.inventory,
							(Tileturbine) entity);
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

				if (entity instanceof Tileturbine) {
					return new TurbineGUI(player.inventory,
							(Tileturbine) entity);

				}

			}
		}

		return null;
	}
	
	
}
