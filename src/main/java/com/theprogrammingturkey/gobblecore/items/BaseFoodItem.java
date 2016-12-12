package com.theprogrammingturkey.gobblecore.items;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseFoodItem extends ItemFood
{
	private String itemName = "turkey_unnamed";
	private List<String> lore = new ArrayList<String>();
	private boolean shiftToShowLore = true;

	public BaseFoodItem(int replenish, float satMult, String name)
	{
		this(replenish, satMult, false, name, 64);
	}
	
	public BaseFoodItem(int replenish, float satMult, String name, int maxStack)
	{
		this(replenish, satMult, false, name, maxStack);
	}

	public BaseFoodItem(int replenish, float satMult, boolean bool, String name, int maxStack)
	{
		super(replenish, satMult, bool);
		itemName = name;
		super.setUnlocalizedName(name);
		this.setMaxStackSize(maxStack);
	}

	public void setShiftToShowLore(boolean toggle)
	{
		this.shiftToShowLore = toggle;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean bool)
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)|| !this.shiftToShowLore)
			list.addAll(lore);
		else
			list.add("Shift for info");
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
