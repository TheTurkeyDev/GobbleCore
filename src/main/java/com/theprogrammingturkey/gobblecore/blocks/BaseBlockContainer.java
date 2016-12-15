package com.theprogrammingturkey.gobblecore.blocks;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BaseBlockContainer extends BlockContainer
{
	private String blockName = "gobble_Unnamed";
	private List<String> lore = Lists.newArrayList();

	public BaseBlockContainer(String name)
	{
		this(name, Material.GROUND, 0.5f);
	}

	public BaseBlockContainer(String name, float hardness)
	{
		this(name, Material.GROUND, hardness);
	}

	public BaseBlockContainer(String name, Material material, float hardness)
	{
		super(material);
		this.blockName = name;
		this.setHardness(hardness);
		this.setUnlocalizedName(blockName);
	}

	public String getBlockName()
	{
		return this.blockName;
	}

	public void addLore(String info)
	{
		lore.add(info);
	}

	public List<String> getLore()
	{
		return this.lore;
	}
}
