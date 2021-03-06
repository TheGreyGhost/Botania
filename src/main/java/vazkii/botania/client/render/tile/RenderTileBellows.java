/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Aug 28, 2015, 5:30:41 PM (GMT)]
 */
package vazkii.botania.client.render.tile;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.state.BotaniaStateProps;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.client.model.ModelBellows;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TileBellows;

import javax.annotation.Nullable;

public class RenderTileBellows extends TileEntityRenderer<TileBellows> {
	private static final ResourceLocation texture = new ResourceLocation(LibResources.MODEL_BELLOWS);
	private static final ModelBellows model = new ModelBellows();

	@Override
	public void render(@Nullable TileBellows bellows, double d0, double d1, double d2, float f, int digProgress) {
		if (bellows != null)
			if (!bellows.getWorld().isBlockLoaded(bellows.getPos())
					|| bellows.getWorld().getBlockState(bellows.getPos()).getBlock() != ModBlocks.bellows)
				return;

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color4f(1F, 1F, 1F, 1F);
		GlStateManager.translated(d0, d1, d2);

		Minecraft.getInstance().textureManager.bindTexture(texture);

		GlStateManager.translatef(0.5F, 1.5F, 0.5F);
		GlStateManager.scalef(1F, -1F, -1F);
		float angle = 0;
		if(bellows != null) {
			switch(bellows.getBlockState().get(BotaniaStateProps.CARDINALS)) {
				case SOUTH: break;
				case NORTH: angle = 180F; break;
				case EAST: angle = 270F; break;
				case WEST: angle = 90F; break;
			}
		}
		GlStateManager.rotatef(angle, 0F, 1F, 0F);
		model.render(Math.max(0.1F, 1F - (bellows == null ? 0 : bellows.movePos + bellows.moving * f + 0.1F)));
		GlStateManager.color3f(1F, 1F, 1F);
		GlStateManager.scalef(1F, -1F, -1F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.popMatrix();
	}

}
