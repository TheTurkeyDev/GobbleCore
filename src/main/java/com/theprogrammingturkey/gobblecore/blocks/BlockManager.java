package com.theprogrammingturkey.gobblecore.blocks;

import java.util.HashMap;
import java.util.Map;

import com.theprogrammingturkey.gobblecore.IModCore;

public class BlockManager
{
	private static Map<IBlockHandler, BlockLoader> blockHandlers = new HashMap<IBlockHandler, BlockLoader>();

	public static void registerBlockHandler(IBlockHandler handler, IModCore mod)
	{
		blockHandlers.put(handler, new BlockLoader(mod));
	}

	public static void registerBlocks()
	{
		for(IBlockHandler handler : blockHandlers.keySet())
			handler.registerBlocks(blockHandlers.get(handler));
	}

	public static void registerModels()
	{
		for(IBlockHandler handler : blockHandlers.keySet())
			handler.registerModels(blockHandlers.get(handler));
	}
}