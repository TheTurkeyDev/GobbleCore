package com.theprogrammingturkey.gobblecore.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigLoader
{
	public static Configuration config;
	
	public static final String genCat = "General Settings";
	public static final String bhCat = "Block Highlight Settings";
	public static final String aiCat = "Armor Info Settings";

	public static final String thicknessAmount = "Highlight Line Thickness";
	public static final String defaultHighlight = "includeDefaultHighlight";
	public static final String dimHighlight = "highlightAffectedByLight";

	public static final String guiHighlight = "Gui Slot Highlighting";

	public static final String redAmount = "Highlight red amount";
	public static final String greenAmount = "Highlight green amount";
	public static final String blueAmount = "Highlight blue amount";
	public static final String alphaAmount = "Highlight alpha amount";

	public static final String buttonAnimation = "Button Animations";
	public static final String buttonAnimationType = "Button Animation Type";
	public static final String buttonAnimationSpeed = "Button Animation Speed";

	public static final String armorHUD = "Armor Gui Hud";

	public static void loadConfigSettings(File file)
	{
		config = new Configuration(file);

		config.load();
		config.setCategoryComment(genCat, "General overall settings");
		config.setCategoryComment(bhCat, "Settings related to block highlighting");
		config.setCategoryComment(aiCat, "Settings related to armor info");
		config.save();

		refreshSettings();
	}

	public static void refreshSettings()
	{
		config.load();

		GobbleCoreSettings.includeDefaultHighlight = config.getBoolean(defaultHighlight, bhCat, false, "Set to true to include the default thin black outline with the custom outline highlight");
		GobbleCoreSettings.highlightColorR = config.getFloat(redAmount, bhCat, 0F, 0F, 1F, "Red color value to be mixed into the the block hightlight overall color");
		GobbleCoreSettings.highlightColorG = config.getFloat(greenAmount, bhCat, 0F, 0F, 1F, "Green color value to be mixed into the the block hightlight overall color");
		GobbleCoreSettings.highlightColorB = config.getFloat(blueAmount, bhCat, 0F, 0F, 1F, "Blue color value to be mixed into the the block hightlight overall color");
		GobbleCoreSettings.highlightColorA = config.getFloat(alphaAmount, bhCat, 0.4F, 0F, 1F, "Alpha amount for block hightlight overall. I.e. line clearness");
		GobbleCoreSettings.highlightLineThickness = config.getFloat(thicknessAmount, bhCat, 2F, 1F, 10F, "How thick the highlight line should be.");
		GobbleCoreSettings.highlightAffectedByLight = config.getBoolean(dimHighlight, bhCat, false, "Set to true for the block highlight to dim to match the blocks light level");

		GobbleCoreSettings.armorGuiHud = config.getBoolean(armorHUD, aiCat, false, "Set to true to enable the gui hud for armor info ingame");

		config.save();
	}

	public static void saveBlockHighlightSettings(float a, float r, float g, float b, float thickness, boolean override, boolean dim)
	{
		config.load();

		Property prop = config.get(bhCat, defaultHighlight, false);
		prop.set(override);
		GobbleCoreSettings.includeDefaultHighlight = override;

		prop = config.get(bhCat, redAmount, 0F);
		prop.set(r);
		GobbleCoreSettings.highlightColorR = r;

		prop = config.get(bhCat, greenAmount, 0F);
		prop.set(g);
		GobbleCoreSettings.highlightColorG = g;

		prop = config.get(bhCat, blueAmount, 0F);
		prop.set(b);
		GobbleCoreSettings.highlightColorB = b;

		prop = config.get(bhCat, alphaAmount, 1F);
		prop.set(a);
		GobbleCoreSettings.highlightColorA = a;

		prop = config.get(bhCat, thicknessAmount, 2F);
		prop.set(thickness);
		GobbleCoreSettings.highlightLineThickness = thickness;

		prop = config.get(bhCat, dimHighlight, false);
		prop.set(dim);
		GobbleCoreSettings.highlightAffectedByLight = dim;

		config.save();
	}

	public static void saveArmorInfoSettings(boolean enabled)
	{
		config.load();

		Property prop = config.get(aiCat, armorHUD, false);
		prop.set(enabled);
		GobbleCoreSettings.armorGuiHud = enabled;

		config.save();
	}
}