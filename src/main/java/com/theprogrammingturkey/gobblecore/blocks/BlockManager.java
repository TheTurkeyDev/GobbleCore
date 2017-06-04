package com.theprogrammingturkey.gobblecore.blocks;

import java.util.HashMap;
import java.util.Map;

import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.util.CustomEntry;

public class BlockManager
{
	private static Map<IModCore, CustomEntry<IBlockHandler, BlockLoader>> blockHandlers = new HashMap<IModCore, CustomEntry<IBlockHandler, BlockLoader>>();

	public static void registerBlockHandler(IBlockHandler handler, IModCore mod)
	{
		blockHandlers.put(mod, new CustomEntry<IBlockHandler, BlockLoader>(handler, new BlockLoader(mod)));
	}

	public static void registerBlocks()
	{
		for(IModCore mod : blockHandlers.keySet())
		{
			CustomEntry<IBlockHandler, BlockLoader> values = blockHandlers.get(mod);
			values.getKey().registerBlocks(values.getValue());
		}
	}

	public static void registerModels()
	{
		for(IModCore mod : blockHandlers.keySet())
		{
			CustomEntry<IBlockHandler, BlockLoader> values = blockHandlers.get(mod);
			values.getKey().registerModels(values.getValue());
		}
	}
}