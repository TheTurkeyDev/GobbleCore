package com.theprogrammingturkey.gobblecore.items;

import java.util.HashMap;
import java.util.Map;

import com.theprogrammingturkey.gobblecore.GobbleCore;
import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.util.CustomEntry;

import net.minecraftforge.fml.common.Loader;

public class ItemManager
{
	private static Map<IModCore, CustomEntry<IItemHandler, ItemLoader>> itemHandlers = new HashMap<IModCore, CustomEntry<IItemHandler, ItemLoader>>();

	public static void registerItemHandler(IItemHandler handler, IModCore mod)
	{
		itemHandlers.put(mod, new CustomEntry<IItemHandler, ItemLoader>(handler, new ItemLoader(mod)));
	}

	public static void registerItems()
	{
		for(IModCore mod : itemHandlers.keySet())
		{
			Loader.instance().setActiveModContainer(Loader.instance().getIndexedModList().get(mod.getModID()));
			CustomEntry<IItemHandler, ItemLoader> values = itemHandlers.get(mod);
			values.getKey().registerItems(values.getValue());
		}
		Loader.instance().setActiveModContainer(Loader.instance().getIndexedModList().get(GobbleCore.MODID));
	}

	public static void registerModels()
	{
		for(IModCore mod : itemHandlers.keySet())
		{
			CustomEntry<IItemHandler, ItemLoader> values = itemHandlers.get(mod);
			values.getKey().registerModels(values.getValue());
		}
	}
}