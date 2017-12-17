package com.theprogrammingturkey.gobblecore.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;
import com.theprogrammingturkey.gobblecore.blocks.BaseBlock;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
	public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || !this.shiftToShowLore || lore.size() == 0)
			tooltip.addAll(lore);
		else if(lore.size() > 0)
			tooltip.add("Shift for info");
	}

	public Block getBlock()
	{
		return block;
	}
}