package com.theprogrammingturkey.gobblecore.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;
import com.theprogrammingturkey.gobblecore.blocks.BaseBlock;

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
	private boolean shiftToShowLore = true;

	public BaseItemBlock(Block block)
	{
		this(block, 64);
	}

	public BaseItemBlock(Block block, int maxStack)
	{
		super(block);
		this.block = block;
		this.setMaxStackSize(maxStack);
		if(block instanceof BaseBlock)
			lore = ((BaseBlock) block).getLore();
	}

	public void addLore(String info)
	{
		lore.add(info);
	}

	public void setShiftToShowLore(boolean toggle)
	{
		this.shiftToShowLore = toggle;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean bool)
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || !this.shiftToShowLore || lore.size() == 0)
			list.addAll(lore);
		else
			list.add("Shift for info");
	}

	public Block getBlock()
	{
		return block;
	}
}