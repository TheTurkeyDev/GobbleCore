package com.theprogrammingturkey.gobblecore.items;

import java.util.HashMap;
import java.util.Map;

import com.theprogrammingturkey.gobblecore.GobbleCore;
import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.util.CustomEntry;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemManager
{
	private static Map<IModCore, CustomEntry<IItemHandler, ItemLoader>> itemHandlers = new HashMap<IModCore, CustomEntry<IItemHandler, ItemLoader>>();

	public static void registerItemHandler(IItemHandler handler, IModCore mod)
	{
		itemHandlers.put(mod, new CustomEntry<IItemHandler, ItemLoader>(handler, new ItemLoader(mod)));
	}

	@SubscribeEvent
	public void onItemRegistry(RegistryEvent.Register<Item> e)
	{
		for(IModCore mod : itemHandlers.keySet())
		{
			Loader.instance().setActiveModContainer(Loader.instance().getIndexedModList().get(mod.getModID()));
			CustomEntry<IItemHandler, ItemLoader> values = itemHandlers.get(mod);
			values.getValue().setItemRegistry(e.getRegistry());
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