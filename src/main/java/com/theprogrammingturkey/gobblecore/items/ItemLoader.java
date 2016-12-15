package com.theprogrammingturkey.gobblecore.items;

import com.theprogrammingturkey.gobblecore.IModCore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemLoader
{
	private ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

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

	public void registerItem(BaseFoodItem item)
	{
		this.registerItem(item, item.getItemName());
	}

	public void registerItem(Item item, String name)
	{
		item.setRegistryName(subMod.getModID(), name);
		item.setCreativeTab(tab);
		GameRegistry.register(item);
	}

	public void registerItemModel(BaseItem item, int meta)
	{
		this.registerItemModel(item, meta, item.getItemName());
	}

	public void registerItemModel(BaseFoodItem item, int meta)
	{
		this.registerItemModel(item, meta, item.getItemName());
	}

	public void registerItemModel(Item item, int meta, String name)
	{
		mesher.register(item, meta, new ModelResourceLocation(subMod.getModID() + ":" + name, "inventory"));
	}

	public void setCreativeTab(CreativeTabs tab)
	{
		this.tab = tab;
	}
}
