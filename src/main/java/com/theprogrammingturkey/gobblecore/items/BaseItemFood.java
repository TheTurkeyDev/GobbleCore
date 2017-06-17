package com.theprogrammingturkey.gobblecore.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseItemFood extends ItemFood
{
	private String itemName = "gobble_unnamed";
	private List<String> lore = Lists.newArrayList();
	private boolean shiftToShowLore = true;

	public BaseItemFood(int replenish, float satMult, boolean bool, String name, String texture, int maxStack)
	{
		super(replenish, satMult, bool);
		super.setUnlocalizedName(name);
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
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean bool)
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || !this.shiftToShowLore)
			list.addAll(lore);
		else if(lore.size() > 0)
			list.add("Shift for info");
	}
}