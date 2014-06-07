package mcDams.blocks;

import java.util.Random;

import cpw.mods.fml.common.MinecraftDummyContainer;
import mcDams.McDams;
import mcDams.McDamsConfigs;
import mcDams.blocks.basic.DamPart;
import mcDams.tileEntities.TileDamWall;
import mcDams.tileEntities.basic.TileDamPart;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ForgeInternalHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.network.ForgeNetworkHandler;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.server.command.ForgeCommand;
import net.minecraftforge.transformers.ForgeAccessTransformer;

/**
 * todo javadoc
 */

public class TestDamWall extends DamPart {
	public TestDamWall() {
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
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileDamWall();
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		super.onBlockPreDestroy(world, x, y, z, meta);

		boolean generateWater = McDams
				.getConfigValue(McDamsConfigs.GENERATE_WATER_ON_BREACH) != null
				&& (Boolean) McDams
						.getConfigValue(McDamsConfigs.GENERATE_WATER_ON_BREACH);

		// If destroyed by a player and there is another dammpart above.. yeah
		// destroy it also, dam breach!
		// Just to annoy players :-P
		Block toDestroy = world.getBlock(x, y + 1, z);
		if (toDestroy != null && toDestroy instanceof DamPart) {
			toDestroy.dropBlockAsItem(world, x, y + 1, z, 0, 0);
			if (generateWater) {
				world.setBlock(x, y + 1, z, Blocks.water);
			} else {
				world.setBlockToAir(x, y + 1, z);
			}
		}

		Random rnd = new Random(new java.util.Date().getTime());

		Block aboveSurrounding = world.getBlock(x, y + 1, z);

		toDestroy = world.getBlock(x + 1, y + 1, z);
		if (rnd.nextBoolean() && toDestroy != null
				&& toDestroy instanceof DamPart) {
			toDestroy.dropBlockAsItem(world, x + 1, y + 1, z, 0, 0);
			if (generateWater) {
				world.setBlock(x + 1, y + 1, z, Blocks.water);
			} else {
				world.setBlockToAir(x + 1, y + 1, z);
			}
		}

		toDestroy = world.getBlock(x - 1, y + 1, z);
		if (rnd.nextBoolean() && toDestroy != null
				&& toDestroy instanceof DamPart) {
			toDestroy.dropBlockAsItem(world, x - 1, y + 1, z, 0, 0);
			if (generateWater) {
				world.setBlock(x - 1, y + 1, z, Blocks.water);
			} else {
				world.setBlockToAir(x - 1, y + 1, z);
			}
		}

		toDestroy = world.getBlock(x, y + 1, z + 1);
		if (rnd.nextBoolean() && toDestroy != null
				&& toDestroy instanceof DamPart) {
			toDestroy.dropBlockAsItem(world, x, y + 1, z + 1, 0, 0);
			if (generateWater) {
				world.setBlock(x, y + 1, z + 1, Blocks.water);
			} else {
				world.setBlockToAir(x, y + 1, z + 1);
			}
		}

		toDestroy = world.getBlock(x, y + 1, z - 1);
		if (rnd.nextBoolean() && toDestroy != null
				&& toDestroy instanceof DamPart) {
			toDestroy.dropBlockAsItem(world, x, y + 1, z - 1, 0, 0);
			if (generateWater) {
				world.setBlock(x, y + 1, z - 1, Blocks.water);
			} else {
				world.setBlockToAir(x, y + 1, z - 1);
			}
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z,
			int meta) {
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);

	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase player, ItemStack stack) {
		// thats stolen from k4unl.minecraft.Hydraulicraft
		int rotation = ((MathHelper
				.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
		ForgeDirection facing = ForgeDirection.getOrientation(rotation);
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity != null && entity instanceof TileDamPart) {
			if (((TileDamPart) entity).checkValidMultiblock(world, x, y, z)) {
				System.out.println("Valid multiblock");
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileDamWall();
	}

}