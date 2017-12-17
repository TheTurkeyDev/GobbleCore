package com.theprogrammingturkey.gobblecore.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseItem extends Item
{
	private String itemName = "gobble_unnamed";
	private List<String> lore = Lists.newArrayList();
	private boolean shiftToShowLore = true;

	public BaseItem(String name)
	{
		this(name, 64);
	}

	public BaseItem(String name, int maxStack)
	{
		itemName = name;
		this.setUnlocalizedName(name);
		this.setMaxStackSize(maxStack);
	}

	public String getItemName()
	{
		return this.itemName;
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
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || !this.shiftToShowLore)
			tooltip.addAll(lore);
		else if(lore.size() > 0)
			tooltip.add("Shift for info");
	}
}