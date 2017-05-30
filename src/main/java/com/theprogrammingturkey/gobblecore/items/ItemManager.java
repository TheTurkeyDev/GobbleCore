package com.theprogrammingturkey.gobblecore.items;

import java.util.HashMap;
import java.util.Map;

import com.theprogrammingturkey.gobblecore.IModCore;

public class ItemManager
{
	private static Map<IItemHandler, ItemLoader> itemHandlers = new HashMap<IItemHandler, ItemLoader>();

	public static void registerItemHandler(IItemHandler handler, IModCore mod)
	{
		itemHandlers.put(handler, new ItemLoader(mod));
	}

	public static void registerItems()
	{
		for(IItemHandler handler : itemHandlers.keySet())
			handler.registerItems(itemHandlers.get(handler));
	}

	public static void registerModels()
	{
		for(IItemHandler handler : itemHandlers.keySet())
			handler.registerModels(itemHandlers.get(handler));
	}
}