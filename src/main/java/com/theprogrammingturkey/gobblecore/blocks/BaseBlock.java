package com.theprogrammingturkey.gobblecore.blocks;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseBlock extends Block
{
	private String blockName = "gobble_Unnamed";
	private List<String> lore = Lists.newArrayList();
	private boolean beaconBase = false;
	private boolean witherProof = false;

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

	public void addLore(String info)
	{
		lore.add(info);
	}

	public List<String> getLore()
	{
		return this.lore;
	}

	public void setUseableBeaconBase(boolean usable)
	{
		this.beaconBase = usable;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
	{
		return this.beaconBase;
	}

	public void setWitherProof(boolean witherProof)
	{
		this.witherProof = witherProof;
	}

	public boolean isWitherProof()
	{
		return this.witherProof;
	}

	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity)
	{
		if(witherProof)
			return false;
		return super.canEntityDestroy(state, world, pos, entity);
	}

	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
	{
		if(!witherProof)
			super.onBlockDestroyedByExplosion(worldIn, pos, explosionIn);
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosion)
	{
		if(witherProof)
			return false;
		return super.canDropFromExplosion(explosion);
	}
}