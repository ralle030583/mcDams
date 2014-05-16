package mcDams.guis;

import org.lwjgl.opengl.GL11;

import mcDams.blocks.turbines.TestTurbine;
import mcDams.containers.TurbineContainer;
import mcDams.tileEntities.Tileturbine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class TurbineGUI extends GuiContainer {


	public static final ResourceLocation texture = new ResourceLocation("McDams", "textures/gui/turbine.png");
	public Tileturbine turbine;




	public TurbineGUI(InventoryPlayer inventory, Tileturbine entity) {
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

