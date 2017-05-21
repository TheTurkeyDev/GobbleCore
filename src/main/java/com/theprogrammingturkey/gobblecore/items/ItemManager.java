package com.theprogrammingturkey.gobblecore.items;

import java.util.ArrayList;
import java.util.List;

import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.util.CustomEntry;

public class ItemManager
{
	private static List<CustomEntry<IItemHandler, ItemLoader>> itemHandlers = new ArrayList<CustomEntry<IItemHandler, ItemLoader>>();

	public static void registerItemHandler(IItemHandler handler, IModCore mod)
	{
		ItemLoader loader = new ItemLoader(mod);
		itemHandlers.add(new CustomEntry<IItemHandler, ItemLoader>(handler, loader));
		handler.registerItems(loader);
	}

	public static void registerModels()
	{
		for(CustomEntry<IItemHandler, ItemLoader> handler : itemHandlers)
			handler.getKey().registerModels(handler.getValue());
	}
}