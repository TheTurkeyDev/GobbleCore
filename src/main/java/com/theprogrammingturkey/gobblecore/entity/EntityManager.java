package com.theprogrammingturkey.gobblecore.entity;

import java.util.HashMap;
import java.util.Map;

import com.theprogrammingturkey.gobblecore.IModCore;

public class EntityManager
{
	private static Map<IEntityHandler, EntityLoader> entityHandlers = new HashMap<IEntityHandler, EntityLoader>();

	public static void registerEntityHandler(IEntityHandler handler, IModCore mod)
	{
		entityHandlers.put(handler, new EntityLoader(mod));
	}

	public static void registerEntities()
	{
		for(IEntityHandler handler : entityHandlers.keySet())
			handler.registerEntities(entityHandlers.get(handler));
	}

	public static void registerRenderings()
	{
		for(IEntityHandler handler : entityHandlers.keySet())
			handler.registerRenderings(entityHandlers.get(handler));
	}
}
