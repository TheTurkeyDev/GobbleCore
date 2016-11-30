package com.theprogrammingturkey.gobblecore.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseFoodItem extends ItemFood
{
	private String itemName = "turkey_unnamed";
	private List<String> lore = new ArrayList<String>();

	public BaseFoodItem(int replenish, float satMult, String name)
	{
		this(replenish, satMult, false, name, 64);
	}

	public BaseFoodItem(int replenish, float satMult, boolean bool, String name, int maxStack)
	{
		super(replenish, satMult, bool);
		itemName = name;
		super.setUnlocalizedName(name);
		this.setMaxStackSize(maxStack);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		list.addAll(lore);
	}

	public void addLore(String s)
	{
		lore.add(s);
	}

	public String getItemName()
	{
		return itemName;
	}
}
