package com.theprogrammingturkey.gobblecore.items;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseItem extends Item
{
	private String itemName = "gobble_unnamed";
	private List<String> lore = Lists.newArrayList();

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		list.addAll(lore);
	}
}
