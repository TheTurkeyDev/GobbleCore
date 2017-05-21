package com.theprogrammingturkey.gobblecore.managers;

import java.util.ArrayList;
import java.util.List;

import com.theprogrammingturkey.gobblecore.proxy.IBaseProxy;

public class ProxyManager
{
	public static List<IBaseProxy> proxies = new ArrayList<IBaseProxy>();

	public static void registerModProxy(IBaseProxy proxy)
	{
		proxies.add(proxy);

		proxy.registerGuis();
		proxy.registerEvents();
		proxy.registerRenderings();
	}
}
