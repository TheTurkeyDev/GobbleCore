package com.theprogrammingturkey.gobblecore.items;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseItemBlock extends ItemBlock
{
	private Block block;
	private List<String> lore = Lists.newArrayList();

	public BaseItemBlock(Block block)
	{
		this(block, 64);
	}

	public BaseItemBlock(Block block, int maxStack)
	{
		super(block);
		this.block = block;
		this.setMaxStackSize(maxStack);
	}

	public void addLore(String info)
	{
		lore.add(info);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		list.addAll(lore);
	}

	public Block getBlock()
	{
		return block;
	}
}
