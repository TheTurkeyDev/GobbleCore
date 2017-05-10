package com.theprogrammingturkey.gobblecore.blocks;

import net.minecraft.client.renderer.ItemModelMesher;

public interface IBlockHandler
{
	public void registerBlocks(BlockLoader loader);

	public void registerModels(BlockLoader loader, ItemModelMesher mesher);
}
