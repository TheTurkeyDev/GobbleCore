package com.theprogrammingturkey.gobblecore.network;

import java.util.HashMap;
import java.util.Map;

import com.theprogrammingturkey.gobblecore.IModCore;

public class NetworkManager
{
	private static Map<INetworkHandler, NetworkLoader> networkHandlers = new HashMap<INetworkHandler, NetworkLoader>();

	public static void registerNetworkHandler(INetworkHandler handler, IModCore mod)
	{
		networkHandlers.put(handler, new NetworkLoader(mod));
	}

	public static void registerPackets()
	{
		for(INetworkHandler handler : networkHandlers.keySet())
			handler.registerPacket(networkHandlers.get(handler));
	}
}
