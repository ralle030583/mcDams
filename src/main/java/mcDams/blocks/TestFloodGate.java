package mcDams.blocks;

import mcDams.blocks.basic.DamPart;
import mcDams.tileEntities.TileDamFloodGate;
import mcDams.utils.ConversionUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TestFloodGate extends DamPart implements ITileEntityProvider {

	private static final String TILE_MAPPING = "mcDamValve";

	public TestFloodGate() {
		super();
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
		return false;
	}

	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase player, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, player, stack);
		if (!world.isRemote) {
			ForgeDirection facing = ConversionUtils
					.getFacingDirectionOfEntity(player);
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity != null && entity instanceof TileDamFloodGate) {
				((TileDamFloodGate) entity).setOrientation(facing);
			}
		}
	}

	/**
	 * TODO Discuss with Ben if we should limit placing of damm valve in some
	 * way. Pherhaps disallow placing in water so ppl dont start building dams
	 * under water at all?
	 */
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metaData) {
		if (!world.isRemote) { // No need clientside, or?
			return new TileDamFloodGate();
		}
		return null;
	}
}
