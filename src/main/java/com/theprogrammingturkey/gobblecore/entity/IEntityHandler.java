package com.theprogrammingturkey.gobblecore.entity;

public interface IEntityHandler
{
	public void registerEntities(EntityLoader loader);

	public void registerRenderings(EntityLoader loader);
}