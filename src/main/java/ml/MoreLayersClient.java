package ml;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;

public class MoreLayersClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		registerBlockColour(MoreLayers.grass_block_layer, Blocks.GRASS_BLOCK);
		registerBlockColour(MoreLayers.oak_leaves_layer, Blocks.OAK_LEAVES);
		registerBlockColour(MoreLayers.birch_leaves_layer, Blocks.BIRCH_LEAVES);
		registerBlockColour(MoreLayers.spruce_leaves_layer, Blocks.SPRUCE_LEAVES);
		registerBlockColour(MoreLayers.dark_oak_leaves_layer, Blocks.DARK_OAK_LEAVES);
		registerBlockColour(MoreLayers.acacia_leaves_layer, Blocks.ACACIA_LEAVES);
		registerBlockColour(MoreLayers.jungle_leaves_layer, Blocks.JUNGLE_LEAVES);
	}

	public void registerBlockColour (Block block, Block biomeColouringBlock) {
		ColorProviderRegistry.BLOCK.register((block3, pos, world, layer) -> {
			BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(biomeColouringBlock);
			return provider == null ? -1 : provider.getColor(block3, pos, world, layer);
		}, block);

		ColorProviderRegistry.ITEM.register((item, layer) -> {
			if (biomeColouringBlock == Blocks.OAK_LEAVES) {
				return FoliageColors.getDefaultColor();
			} else {
				return GrassColors.getColor(0.5D, 1D);
			}
		}, block.asItem());
	}
	
}
