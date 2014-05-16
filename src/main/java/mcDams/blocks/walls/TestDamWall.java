package mcDams.blocks.walls;

import mcDams.McDams;
import mcDams.blocks.basic.DamPart;
import mcDams.blocks.basic.IDamPart;
import mcDams.blocks.valves.IValve;
import mcDams.blocks.valves.TestValve;
import mcDams.tileEntities.TileDamValve;
import mcDams.tileEntities.TileDamWall;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * todo javadoc
 */

public class TestDamWall extends DamPart implements IWall{
	public TestDamWall() {
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

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileDamWall();
	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase player, ItemStack stack) {
		// thats stolen from k4unl.minecraft.Hydraulicraft
		int rotation = ((MathHelper
				.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
		ForgeDirection facing = ForgeDirection.getOrientation(rotation);
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity != null && entity instanceof TileDamValve) {
			((TileDamValve) entity).setOrientation(facing);
		}

		if (checkValidMultiblock(world, x, y, z)) {
			// valid multiblock
			System.out.println("Valid multiblock");

		}

	}

	private boolean checkValidMultiblock(World world, int x, int y, int z) {

		if (isNextToWall(world, x, y, z)) {

			if (isValidLayout(world, x, y, z)) {
				return true;
			}
		}
		return false;

	}

	private boolean isNextToWall(World world, int x, int y, int z) {

		Block testblock;

		// above

		if (checkBlockMatch(world, x, y + 1, z)) {
			return true;
		}
		// below
		if (checkBlockMatch(world, x, y - 1, z)) {
			return true;
		}
		// X+1
		if (checkBlockMatch(world, x + 1, y, z)) {
			return true;
		}
		// X-1
		if (checkBlockMatch(world, x - 1, y, z)) {
			return true;
		}
		// Y+1
		if (checkBlockMatch(world, x, y + 1, z)) {
			return true;
		}
		// Y-1
		if (checkBlockMatch(world, x, y - 1, z)) {
			return true;
		}

		return false;
	}

	private boolean checkBlockMatch(World world, int x, int y, int z) {
		Block testblock = world.getBlock(x, y, z);
		// // if
		// //
		// (testblock.getUnlocalizedName().equals(this.getUnlocalizedName())){
		// String tempMe = testblock.getUnlocalizedName();
		// String tempYou = this.getUnlocalizedName();
		// if (tempMe.equals(tempYou)) {
		// return true;
		// }
		// // check for valve, maybe not a good way to do it in this function
		// if (testblock.getUnlocalizedName() == McDams.testValveBlock
		// .getUnlocalizedName()) {
		// return true;
		// }
		// @Ben: :-P and even easier check: with an interface
		if (testblock instanceof IDamPart) {
			return true;
		}
		return false;
	}

	private boolean isValidLayout(World world, int x, int y, int z) {

		// 3x2 check only for testing
		// X axis

		// Am i on the top row?

		boolean topRow = false;
		int topPossition = 0;

		if (checkBlockMatch(world, x, y - 1, z)) {
			// there is a valid block below - I am on top row

			if (checkBlockMatch(world, x - 1, y, z)) {
				// there is a valid on x - 1 - there is a block to my left

				if (checkBlockMatch(world, x - 2, y, z)) {
					// there is a valid block x-2 - I am top right - top row
					// valid
					topRow = true;
					topPossition = 2;
				}

				if (checkBlockMatch(world, x + 1, y, z)) {
					// there is a valid block x+1 - I am top middle - top row
					// valid
					topRow = true;
					topPossition = 1;
				}

			}

			if (checkBlockMatch(world, x + 1, y, z)) {
				// there is a valid on x + 1 - there is a block to my right

				if (checkBlockMatch(world, x + 2, y, z)) {
					// there is a valid block x+2 - I am top left - top row
					// valid
					topRow = true;
					topPossition = 0;
				}

				if (checkBlockMatch(world, x - 1, y, z)) {
					// there is a valid block x-1 - I am top middle - top row
					// valid
					topRow = true;
					topPossition = 1;
				}

			}

		}

		// if there was a valid top row then check for valid bottom row

		if (topRow) {
			// get the location of bottom left block
			int bleft = x - topPossition;

			// maybe clean these 3 into a better if

			// check first block is me
			if (checkBlockMatch(world, bleft, y - 1, z)) {

				// first block ok, is middle block ok?
				if (checkBlockMatch(world, bleft + 1, y - 1, z)) {

					// first 2 block ok, is last block ok?
					if (checkBlockMatch(world, bleft + 2, y - 1, z)) {

						// all fine so valid possition

						return true;

					}

				}

			}

		}

		return false;

	}

	@Override
	public boolean isPartOfDam() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IValve getValve() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTierLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isBase() {
		// TODO Auto-generated method stub
		return false;
	}

}