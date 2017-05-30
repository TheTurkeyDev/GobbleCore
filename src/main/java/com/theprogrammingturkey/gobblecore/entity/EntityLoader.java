package com.theprogrammingturkey.gobblecore.entity;

import com.theprogrammingturkey.gobblecore.GobbleCore;
import com.theprogrammingturkey.gobblecore.IModCore;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityLoader
{
	private IModCore subMod = GobbleCore.instance;

	private int id = 0;

	public EntityLoader(IModCore mod)
	{
		this.subMod = mod;
	}

	public void registerEntity(String name, Class<? extends Entity> entityClass, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(subMod.getModID(), name), entityClass, name, id++, subMod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	public void registerEntityWithEgg(String name, Class<? extends Entity> entityClass, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(subMod.getModID(), name), entityClass, name, id++, subMod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}

	public <T extends Entity> void registerEntityRendering(Class<T> entityClass, Class<? extends Render<T>> renderer)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, new IRenderFactory<T>()
		{
			@Override
			public Render<? super T> createRenderFor(RenderManager manager)
			{
				try
				{
					return renderer.getConstructor(RenderManager.class).newInstance(manager);
				} catch(Exception e)
				{
					e.printStackTrace();
					return null;
				}
			}
		});
	}

}
