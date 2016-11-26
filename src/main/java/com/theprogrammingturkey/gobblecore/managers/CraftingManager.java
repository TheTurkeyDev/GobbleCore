package com.theprogrammingturkey.gobblecore.managers;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingManager
{

	public static void register3x3CompressionRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addRecipe(ingredient, "III", "III", "III", 'I', result);
	}

	public static void register2x2CompressionRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addRecipe(ingredient, "II", "II", 'I', result);
	}

	public static void registerHelemetRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(result, "III", "I I", 'I', ingredient);
	}

	public static void registerChestplateRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(result, "I I", "III", "III", 'I', ingredient);
	}

	public static void registerLeggingsRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(result, "III", "I I", "I I", 'I', ingredient);
	}

	public static void registerBootsRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(result, "I I", "I I", 'I', ingredient);
	}

	public static void registerSwordRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(result, " I ", " I ", " S ", 'S', new ItemStack(Items.STICK), 'I', ingredient);
	}

	public static void registerPickaxeRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(result, "III", " S ", " S ", 'S', new ItemStack(Items.STICK), 'I', ingredient);
	}

	public static void registerAxeRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(result, "II ", "IS ", " S ", 'S', new ItemStack(Items.STICK), 'I', ingredient);
	}

	public static void registerShouvelRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(result, " I ", " S ", " S ", 'S', new ItemStack(Items.STICK), 'I', ingredient);
	}

	public static void registerHoeRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(result, "II ", " S ", " S ", 'S', new ItemStack(Items.STICK), 'I', ingredient);
	}
}
