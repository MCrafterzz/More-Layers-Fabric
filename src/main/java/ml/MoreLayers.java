package ml;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameMode;

public class MoreLayers implements ModInitializer {

	public static ItemGroup itemGroup;

	public static Block acacia_leaves_layer, andesite_layer, birch_leaves_layer, coarse_dirt_layer, cobblestone_layer,
			dark_oak_leaves_layer, diorite_layer, dirt_layer, granite_layer, grass_block_layer, grass_path_layer,
			gravel_layer, jungle_leaves_layer, mossy_cobblestone_layer, mycelium_layer, oak_leaves_layer, podzol_layer,
			red_sand_layer, red_sandstone_layer, sand_layer, sandstone_layer, spruce_leaves_layer, stone_layer, hay_block_layer;

	@Override
	public void onInitialize() {
		itemGroup = FabricItemGroupBuilder.create(new Identifier("ml", "layers"))
				.appendItems(itemStacks -> Registry.ITEM.forEach(item -> {
					if (Registry.ITEM.getId(item).getNamespace().equals("ml")
							|| Registry.ITEM.getId(item).getPath().equals("snow")) {
						itemStacks.add(new ItemStack(item));
					}
				})).icon(() -> new ItemStack(grass_block_layer)).build();

		registerBlocks();

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (!world.isClient && player.isSneaking() == true) {
				if (((ServerPlayerEntity) player).interactionManager.getGameMode() == GameMode.SURVIVAL
						|| ((ServerPlayerEntity) player).interactionManager.getGameMode() == GameMode.CREATIVE) {
					Block block = world.getBlockState(hitResult.getBlockPos()).getBlock();
					if (block instanceof SnowBlock || block instanceof BlockLayer) {
						if (player.getStackInHand(hand).getItem() instanceof ShovelItem) {
							int newHeight = world.getBlockState(hitResult.getBlockPos()).get(BlockLayer.LAYERS) - 1;

							if (newHeight == 0) {
								world.breakBlock(hitResult.getBlockPos(), true);
							} else {
								world.setBlockState(hitResult.getBlockPos(), world
										.getBlockState(hitResult.getBlockPos()).with(BlockLayer.LAYERS, newHeight));
								Block.dropStack(world, hitResult.getBlockPos(), new ItemStack(block.asItem()));
							}

							player.getStackInHand(hand).damage(1, player, null);
							return ActionResult.SUCCESS;
						} else {
							return ActionResult.PASS;
						}
					} else {
						if (player.getStackInHand(hand).getItem() instanceof ShovelItem) {
							if (block.getTranslationKey().equals("block.minecraft.grass_block")) {
								world.setBlockState(hitResult.getBlockPos(),
										grass_block_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.snow_block")) {
								world.setBlockState(hitResult.getBlockPos(),
										Blocks.SNOW.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.grass_path")) {
								world.setBlockState(hitResult.getBlockPos(),
										grass_path_layer.getDefaultState().with(BlockLayer.LAYERS, 7));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.dirt")) {
								world.setBlockState(hitResult.getBlockPos(),
										dirt_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.mycelium")) {
								world.setBlockState(hitResult.getBlockPos(),
										mycelium_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.podzol")) {
								world.setBlockState(hitResult.getBlockPos(),
										podzol_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.coarse_dirt")) {
								world.setBlockState(hitResult.getBlockPos(),
										coarse_dirt_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.sand")) {
								world.setBlockState(hitResult.getBlockPos(),
										sand_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.red_sand")) {
								world.setBlockState(hitResult.getBlockPos(),
										red_sand_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.sandstone")) {
								world.setBlockState(hitResult.getBlockPos(),
										sandstone_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.red_sandstone")) {
								world.setBlockState(hitResult.getBlockPos(),
										red_sandstone_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.cobblestone")) {
								world.setBlockState(hitResult.getBlockPos(),
										cobblestone_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.mossy_cobblestone")) {
								world.setBlockState(hitResult.getBlockPos(),
										mossy_cobblestone_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.stone")) {
								world.setBlockState(hitResult.getBlockPos(),
										stone_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.diorite")) {
								world.setBlockState(hitResult.getBlockPos(),
										diorite_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.granite")) {
								world.setBlockState(hitResult.getBlockPos(),
										granite_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.andesite")) {
								world.setBlockState(hitResult.getBlockPos(),
										andesite_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.oak_leaves")) {
								world.setBlockState(hitResult.getBlockPos(),
										oak_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.birch_leaves")) {
								world.setBlockState(hitResult.getBlockPos(),
										birch_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.spruce_leaves")) {
								world.setBlockState(hitResult.getBlockPos(),
										spruce_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.jungle_leaves")) {
								world.setBlockState(hitResult.getBlockPos(),
										jungle_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.dark_oak_leaves")) {
								world.setBlockState(hitResult.getBlockPos(),
										dark_oak_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.acacia_leaves")) {
								world.setBlockState(hitResult.getBlockPos(),
										acacia_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block.getTranslationKey().equals("block.minecraft.hay_block")) {
								world.setBlockState(hitResult.getBlockPos(),
										hay_block_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							}
							return ActionResult.PASS;
						}
					}
				} else {
					return ActionResult.PASS;
				}
			} else {
				return ActionResult.PASS;
			}
			return ActionResult.PASS;
		});
	}

	private void registerBlocks() {
		dirt_layer = registerBlock(Blocks.DIRT);
		coarse_dirt_layer = registerBlock(Blocks.COARSE_DIRT);
		podzol_layer = registerBlock(Blocks.PODZOL);
		mycelium_layer = registerBlock(Blocks.MYCELIUM);
		grass_block_layer = registerBlock(Blocks.GRASS_BLOCK);
		grass_path_layer = registerBlock(Blocks.GRASS_PATH);
		sand_layer = registerBlock(Blocks.SAND);
		red_sand_layer = registerBlock(Blocks.RED_SAND);
		sandstone_layer = registerBlock(Blocks.SANDSTONE);
		red_sandstone_layer = registerBlock(Blocks.RED_SANDSTONE);
		gravel_layer = registerBlock(Blocks.GRAVEL);
		hay_block_layer = registerBlock(Blocks.HAY_BLOCK);
		
		cobblestone_layer = registerBlock(Blocks.COBBLESTONE);
		mossy_cobblestone_layer = registerBlock(Blocks.MOSSY_COBBLESTONE);
		stone_layer = registerBlock(Blocks.STONE);
		granite_layer = registerBlock(Blocks.GRANITE);
		diorite_layer = registerBlock(Blocks.DIORITE);
		andesite_layer = registerBlock(Blocks.ANDESITE);

		oak_leaves_layer = registerBlock(Blocks.OAK_LEAVES, BlockRenderLayer.CUTOUT);
		birch_leaves_layer = registerBlock(Blocks.BIRCH_LEAVES, BlockRenderLayer.CUTOUT);
		jungle_leaves_layer = registerBlock(Blocks.JUNGLE_LEAVES, BlockRenderLayer.CUTOUT);
		spruce_leaves_layer = registerBlock(Blocks.SPRUCE_LEAVES, BlockRenderLayer.CUTOUT);
		dark_oak_leaves_layer = registerBlock(Blocks.DARK_OAK_LEAVES, BlockRenderLayer.CUTOUT);
		acacia_leaves_layer = registerBlock(Blocks.ACACIA_LEAVES, BlockRenderLayer.CUTOUT);
	}

	private Block registerBlock(Block block) {
		return registerBlock(block, BlockRenderLayer.CUTOUT);
	}

	private Block registerBlock(Block block, BlockRenderLayer blockRenderLayer) {
		Block block2 = new BlockLayer(block, blockRenderLayer);
		Registry.register(Registry.BLOCK,
				new Identifier("ml", block.getTranslationKey().replace("block.minecraft.", "") + "_layer"), block2);
		BlockItem blockItem = new BlockItem(block2, new Item.Settings().group(itemGroup));
		Registry.register(Registry.ITEM,
				new Identifier("ml", block.getTranslationKey().replace("block.minecraft.", "") + "_layer"), blockItem);
		return block2;
	}
}
