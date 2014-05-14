package mcDams.blocks.valves;

import mcDams.tileEntities.TileDamValve;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TestValve extends Block{

	protected TestValve() {
		super(Material.rock);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileDamValve();
	}
}
