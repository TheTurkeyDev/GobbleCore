package com.theprogrammingturkey.gobblecore.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseItemFood extends ItemFood
{
	private String itemName = "gobble_unnamed";
	private List<String> lore = Lists.newArrayList();
	private boolean shiftToShowLore = true;

	public BaseItemFood(String name, int replenish, float satMult, boolean bool)
	{
		this(name, replenish, satMult, bool, 64);
	}

	public BaseItemFood(String name, int replenish, float satMult, boolean bool, int maxStack)
	{
		super(replenish, satMult, bool);
		this.itemName = name;
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
	public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || !this.shiftToShowLore)
			tooltip.addAll(lore);
		else if(lore.size() > 0)
			tooltip.add("Shift for info");
	}
}