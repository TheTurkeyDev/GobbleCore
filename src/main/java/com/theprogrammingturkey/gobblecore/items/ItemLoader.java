package com.theprogrammingturkey.gobblecore.items;

import com.theprogrammingturkey.gobblecore.IModCore;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemLoader
{
	private IModCore subMod;

	private CreativeTabs tab;
	private IForgeRegistry<Item> registry;

	public ItemLoader(IModCore subMod)
	{
		this.subMod = subMod;
	}

	public void setItemRegistry(IForgeRegistry<Item> registry)
	{
		this.registry = registry;
	}

	public void registerItem(Item item, String name)
	{
		item.setRegistryName(subMod.getModID(), name);
		item.setCreativeTab(tab);
		registry.register(item);
	}

	public void registerBlockItem(Block block)
	{
		Item item = new BaseItemBlock(block).setRegistryName(block.getRegistryName());
		item.setCreativeTab(tab);
		registry.register(item);
	}

	public void registerItemModel(ItemModelMesher mesher, Item item, int meta, String name)
	{
		mesher.register(item, meta, new ModelResourceLocation(subMod.getModID() + ":" + name, "inventory"));
	}

	public void setCreativeTab(CreativeTabs tab)
	{
		this.tab = tab;
	}
}