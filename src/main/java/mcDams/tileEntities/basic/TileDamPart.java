package mcDams.tileEntities.basic;

import mcDams.blocks.basic.DamPartType;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class TileDamPart extends TileEntity {

	private TileDamPart connectedValve = null;
	private DamPartType type = null;

	public TileDamPart(DamPartType _type) {
		this.type = _type;
	}

	public boolean isTurbine(){
		return DamPartType.TURBINE.equals(this.type);
	}
	
	public boolean isWall(){
		return DamPartType.WALL.equals(this.type);
	}
	
	public boolean isValve(){
		return DamPartType.VALVE.equals(this.type);
	}
	
	public boolean isFloodGate(){
		return DamPartType.FLOODGATE.equals(this.type);
	}
	
	
	public void tryRegisterToDam(){
		this.connectedValve = null;
		// TODO 
	}
	
	public TileDamPart getConnectedValve() {
		return connectedValve;
	}
	
	public void setConnectedValve(TileDamPart connectedValve) {
		this.connectedValve = connectedValve;
	}

	public boolean checkValidMultiblock(World world, int x, int y, int z) {

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
		TileEntity test = world.getTileEntity(x, y, z);

		if (test instanceof TileDamPart) {
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
}
