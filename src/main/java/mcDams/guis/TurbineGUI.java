package mcDams.guis;

import mcDams.blocks.turbines.TestTurbine;
import mcDams.tileEntities.Tileturbine;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class TurbineGUI extends Gui {


	public static final ResourceLocation texture = new ResourceLocation("McDams", "textures/gui/turbine.png");
	public Tileturbine turbine;




	public TurbineGUI(InventoryPlayer inventory, Tileturbine entity) {
		//super(new GiftBoxContainer(inventory, entity));
		this.turbine = entity;
		//this.xSize = 175;
		//this.ySize = 166;
	}



//	@Override
//	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
//		GL11.glColor4f(1F, 1F, 1F, 1F);
		//Minecraft.getMinecraft().func_110434_K().func_110577_a(texture)
//		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

//		this.drawTexturedModalRect(this.guiLeft,this.guiTop,0,0, this.xSize, this.ySize);
	}
