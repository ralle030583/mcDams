package mcDams.blocks.valves;

import mcDams.tileEntities.TileDamValve;
import mcDams.utils.ConversionUtils;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TestValve extends Block implements ITileEntityProvider{

	private static final String TILE_MAPPING = "mcDamValve";
	
	public TestValve() {
		super(Material.rock);

		setStepSound(Block.soundTypeStone);
		setHardness(3.5F);
		setResistance(10F);
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
		return false;
	}

	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
		return false;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase player, ItemStack stack) {

		if (!world.isRemote) {
			ForgeDirection facing = ConversionUtils.getFacingDirectionOfEntity(player);
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity != null && entity instanceof TileDamValve) {
				((TileDamValve) entity).setOrientation(facing);
			}
		}
	}
	
	
	/**
	 * TODO Discuss with Ben if we should limit placing of damm valve in some way.
	 * Pherhaps disallow placing in water so ppl dont start building dams under water at all?
	 */
	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_,
			int p_149742_3_, int p_149742_4_) {
		return super
				.canPlaceBlockAt(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metaData) {
		if (!world.isRemote){ // No need clientside, or?
			return new TileDamValve();
		}
		return null;
	}
	

}
