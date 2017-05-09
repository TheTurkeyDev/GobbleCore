package com.theprogrammingturkey.gobblecore.blocks;

import com.theprogrammingturkey.gobblecore.GobbleCore;
import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.items.BaseItemBlock;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockManager
{
	private static ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

	private static IModCore subMod = GobbleCore.instance;

	private static CreativeTabs tab = null;

	public static void setCreativeTab(CreativeTabs tab)
	{
		BlockManager.tab = tab;
	}

	public static void registerBlock(Block block, String name)
	{
		block.setRegistryName(subMod.getModID(), name);
		block.setCreativeTab(tab);
		GameRegistry.register(block);
		GameRegistry.register(new BaseItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	public static void registerBlock(Block block, Class<? extends TileEntity> tileEntityClass, String name)
	{
		registerBlock(block, name);
		GameRegistry.registerTileEntity(tileEntityClass, "tile_" + name);
	}

	public static void registerBlockModel(Block b, int meta, String name)
	{
		mesher.register(Item.getItemFromBlock(b), meta, new ModelResourceLocation(subMod.getModID() + ":" + name, "inventory"));
	}
}
