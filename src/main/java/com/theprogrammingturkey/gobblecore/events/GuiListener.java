package com.theprogrammingturkey.gobblecore.events;

import com.theprogrammingturkey.gobblecore.config.GobbleCoreSettings;
import com.theprogrammingturkey.gobblecore.util.RenderUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiListener
{
	@SubscribeEvent
	public void onGameRender(RenderGameOverlayEvent.Post e)
	{
		if(GobbleCoreSettings.armorGuiHud)
		{
			System.out.println("HERHERHERHERER");
			Minecraft mc = Minecraft.getMinecraft();
			if(mc.getRenderViewEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) mc.getRenderViewEntity();
				RenderItem irender = mc.getRenderItem();
				FontRenderer frender = mc.fontRendererObj;
				ScaledResolution scaledresolution = new ScaledResolution(mc);
				int height = scaledresolution.getScaledHeight() - (scaledresolution.getScaledHeight() / 6);
				int i = 0;
				int offset = 0;
				float percent = 1;
				int color = 0xFFFFFF;
				GlStateManager.pushMatrix();
				// System.out.println(RenderUtil.colorIntFromRGBA(0.1f, 0.1f, 0.1f, 0.04f));
				boolean drawn = false;

				for(ItemStack stack : player.inventory.armorInventory)
				{
					if(stack != null)
					{
						if(!drawn)
						{
							this.drawGradientRect(0, height - 30, 75, height + 45, 169416985, 169416985);
							drawn = true;
						}
						offset = (16 * i) - 24;
						percent = ((float) stack.getMaxDamage() - (float) stack.getItemDamage()) / (float) stack.getMaxDamage();
						irender.renderItemAndEffectIntoGUI(stack, 5, height - offset);
						color = RenderUtil.colorIntFromRGBA(1 - percent, percent, 0f, 1f);
						// color = percent > 0.5 ? 0xFFFFFF : percent > 0.25 ? 0xFFFF00 : percent > 0.1 ? 0xFFA500 : 0xFF0000;
						frender.drawString("" + (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage(), 25, height - (offset - 4), color);
					}
					i++;
				}
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.popMatrix();
				mc.getTextureManager().bindTexture(Gui.ICONS);
			}
		}
	}

	protected void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor)
	{
		float f = (float) (startColor >> 24 & 255) / 255.0F;
		float f1 = (float) (startColor >> 16 & 255) / 255.0F;
		float f2 = (float) (startColor >> 8 & 255) / 255.0F;
		float f3 = (float) (startColor & 255) / 255.0F;
		float f4 = (float) (endColor >> 24 & 255) / 255.0F;
		float f5 = (float) (endColor >> 16 & 255) / 255.0F;
		float f6 = (float) (endColor >> 8 & 255) / 255.0F;
		float f7 = (float) (endColor & 255) / 255.0F;
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.shadeModel(7425);
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		vertexbuffer.pos((double) right, (double) top, 0).color(f1, f2, f3, f).endVertex();
		vertexbuffer.pos((double) left, (double) top, 0).color(f1, f2, f3, f).endVertex();
		vertexbuffer.pos((double) left, (double) bottom, 0).color(f5, f6, f7, f4).endVertex();
		vertexbuffer.pos((double) right, (double) bottom, 0).color(f5, f6, f7, f4).endVertex();
		tessellator.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
	}
}
