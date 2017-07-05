package com.theprogrammingturkey.gobblecore.managers;

import com.theprogrammingturkey.gobblecore.GobbleCore;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingManager
{
	private static ResourceLocation group = new ResourceLocation("Gobble Core");
	private static ResourceLocation name = new ResourceLocation(GobbleCore.MODID, "Crafting Recipe");

	public static void register3x3CompressionRecipe(ItemStack ingredient, ItemStack result, boolean uncraftable)
	{
		GameRegistry.addShapedRecipe(name, group, result, "III", "III", "III", 'I', ingredient);
		GameRegistry.addShapelessRecipe(name, group, new ItemStack(ingredient.getItem(), 9, ingredient.getItemDamage()), Ingredient.func_193369_a(new ItemStack(result.getItem(), 1, result.getItemDamage())));
	}

	public static void register2x2CompressionRecipe(ItemStack ingredient, ItemStack result, boolean uncraftable)
	{
		GameRegistry.addShapedRecipe(name, group, result, "II", "II", 'I', ingredient);
		GameRegistry.addShapelessRecipe(name, group, new ItemStack(ingredient.getItem(), 4, ingredient.getItemDamage()), Ingredient.func_193369_a(new ItemStack(result.getItem(), 1, result.getItemDamage())));
	}

	public static void registerHelemetRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(name, group, result, "III", "I I", 'I', ingredient);
	}

	public static void registerChestplateRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(name, group, result, "I I", "III", "III", 'I', ingredient);
	}

	public static void registerLeggingsRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(name, group, result, "III", "I I", "I I", 'I', ingredient);
	}

	public static void registerBootsRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(name, group, result, "I I", "I I", 'I', ingredient);
	}

	public static void registerSwordRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(name, group, result, " I ", " I ", " S ", 'S', new ItemStack(Items.STICK), 'I', ingredient);
	}

	public static void registerPickaxeRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(name, group, result, "III", " S ", " S ", 'S', new ItemStack(Items.STICK), 'I', ingredient);
	}

	public static void registerAxeRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(name, group, result, "II ", "IS ", " S ", 'S', new ItemStack(Items.STICK), 'I', ingredient);
	}

	public static void registerShouvelRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(name, group, result, " I ", " S ", " S ", 'S', new ItemStack(Items.STICK), 'I', ingredient);
	}

	public static void registerHoeRecipe(ItemStack ingredient, ItemStack result)
	{
		GameRegistry.addShapedRecipe(name, group, result, "II ", " S ", " S ", 'S', new ItemStack(Items.STICK), 'I', ingredient);
	}
}
