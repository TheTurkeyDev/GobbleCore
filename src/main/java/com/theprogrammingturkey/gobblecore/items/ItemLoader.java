package com.theprogrammingturkey.gobblecore.items;

import com.theprogrammingturkey.gobblecore.IModCore;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemLoader
{
	private IModCore subMod;

	private CreativeTabs tab;

	public ItemLoader(IModCore subMod)
	{
		this.subMod = subMod;
	}

	public void registerItem(BaseItem item)
	{
		this.registerItem(item, item.getItemName());
	}

	public void registerItem(BaseItemFood item)
	{
		this.registerItem(item, item.getItemName());
	}

	public void registerItem(Item item, String name)
	{
		item.setRegistryName(subMod.getModID(), name);
		item.setCreativeTab(tab);
		GameRegistry.register(item);
	}

	public void registerItemModel(ItemModelMesher mesher, BaseItem item, int meta)
	{
		this.registerItemModel(mesher, item, meta, item.getItemName());
	}

	public void registerItemModel(ItemModelMesher mesher, BaseItemFood item, int meta)
	{
		this.registerItemModel(mesher, item, meta, item.getItemName());
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