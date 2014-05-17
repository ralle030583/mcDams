package mcDams.guis;

import mcDams.containers.TurbineContainer;
import mcDams.tileEntities.TileDamTurbine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TurbineGUI extends GuiContainer {


	public static final ResourceLocation texture = new ResourceLocation("mcdams", "textures/gui/turbine.png");
	public TileDamTurbine turbine;




	public TurbineGUI(InventoryPlayer inventory, TileDamTurbine entity) {
		super(new TurbineContainer(inventory, entity));
		this.turbine = entity;
		this.xSize = 175;
		this.ySize = 166;
	}








	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		this.drawTexturedModalRect(this.guiLeft,this.guiTop,0,0, this.xSize, this.ySize);
	}
	}

