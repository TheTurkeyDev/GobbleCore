package com.theprogrammingturkey.gobblecore.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BaseBlock extends Block
{
	private String blockName = "gobble_Unnamed";

	public BaseBlock(String name)
	{
		this(name, Material.GROUND, 0.5f);
	}

	public BaseBlock(String name, float hardness)
	{
		this(name, Material.GROUND, hardness);
	}

	public BaseBlock(String name, Material material, float hardness)
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
}
