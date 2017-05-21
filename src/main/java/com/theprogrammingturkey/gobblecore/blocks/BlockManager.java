package com.theprogrammingturkey.gobblecore.blocks;

import java.util.ArrayList;
import java.util.List;

import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.util.CustomEntry;

public class BlockManager
{
	private static List<CustomEntry<IBlockHandler, BlockLoader>> blockHandlers = new ArrayList<CustomEntry<IBlockHandler, BlockLoader>>();

	public static void registerBlockHandler(IBlockHandler handler, IModCore mod)
	{
		BlockLoader loader = new BlockLoader(mod);
		blockHandlers.add(new CustomEntry<IBlockHandler, BlockLoader>(handler, loader));
		handler.registerBlocks(loader);
	}

	public static void registerModels()
	{
		for(CustomEntry<IBlockHandler, BlockLoader> handler : blockHandlers)
		{
			handler.getKey().registerModels(handler.getValue());
		}
	}
}