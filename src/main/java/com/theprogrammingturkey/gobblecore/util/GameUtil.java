package com.theprogrammingturkey.gobblecore.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GameUtil
{
	private static Random rand = new Random();

	public static ItemStack getItemStack(String mod, String itemName, int size)
	{
		return getItemStack(mod, itemName, size, 0);
	}

	public static ItemStack getItemStack(String mod, String itemName, int size, int meta)
	{
		Item item = Item.REGISTRY.getObject(new ResourceLocation(mod, itemName));
		return item == null ? null : new ItemStack(item, size, meta);
	}

	public static Block getBlock(String mod, String blockName)
	{
		return Block.REGISTRY.getObject(new ResourceLocation(mod, blockName));
	}

	public static boolean isBlockUnbreakable(World world, BlockPos pos)
	{
		return world.getBlockState(pos).getBlockHardness(world, pos) == -1;
	}

	public static Block getRandomBlock()
	{
		int size = Block.REGISTRY.getKeys().size();
		int randomblock = rand.nextInt(size);
		Block b = Block.REGISTRY.getObjectById(randomblock);
		int iteration = 0;
		while(b == null)
		{
			iteration++;
			randomblock = rand.nextInt(size);
			if(iteration > 100)
				b = Blocks.COBBLESTONE;
			else
				b = Block.REGISTRY.getObjectById(randomblock);
		}
		return b;
	}

	public static ItemStack getSpawnEggForMob(String entity)
	{
		ItemStack stack = new ItemStack(Items.SPAWN_EGG);
		NBTTagCompound nbttagcompound = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		nbttagcompound1.setString("id", entity);
		nbttagcompound.setTag("EntityTag", nbttagcompound1);
		stack.setTagCompound(nbttagcompound);
		return stack;
	}

	public static boolean isPlayerOnline(EntityPlayer player)
	{
		if(player == null)
			return false;

		for(EntityPlayerMP playerMP : player.world.getMinecraftServer().getPlayerList().getPlayers())
			if(playerMP.getUniqueID().equals(player.getUniqueID()))
				return true;

		return false;
	}
}
