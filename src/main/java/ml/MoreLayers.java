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
			red_sand_layer, red_sandstone_layer, sand_layer, sandstone_layer, spruce_leaves_layer, stone_layer,
			hay_block_layer, white_concrete_powder_layer, orange_concrete_powder_layer, magenta_concrete_powder_layer,
			light_blue_concrete_powder_layer, yellow_concrete_powder_layer, lime_concrete_powder_layer,
			pink_concrete_powder_layer, gray_concrete_powder_layer, light_gray_concrete_powder_layer,
			cyan_concrete_powder_layer, purple_concrete_powder_layer, blue_concrete_powder_layer,
			brown_concrete_powder_layer, green_concrete_powder_layer, red_concrete_powder_layer,
			black_concrete_powder_layer;

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

		// Convert block to layers
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (!world.isClient && player.isSneaking() == true) {
				if (((ServerPlayerEntity) player).interactionManager.getGameMode() == GameMode.SURVIVAL
						|| ((ServerPlayerEntity) player).interactionManager.getGameMode() == GameMode.CREATIVE) {
					Block block = world.getBlockState(hitResult.getBlockPos()).getBlock();
					if (block instanceof SnowBlock || block instanceof BlockLayer || block instanceof BlockConcretePowderLayer) {
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
							if (block == Blocks.GRASS_BLOCK) {
								world.setBlockState(hitResult.getBlockPos(),
										grass_block_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.SNOW_BLOCK) {
								world.setBlockState(hitResult.getBlockPos(),
										Blocks.SNOW.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.GRASS_PATH) {
								world.setBlockState(hitResult.getBlockPos(),
										grass_path_layer.getDefaultState().with(BlockLayer.LAYERS, 7));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.DIRT) {
								world.setBlockState(hitResult.getBlockPos(),
										dirt_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.MYCELIUM) {
								world.setBlockState(hitResult.getBlockPos(),
										mycelium_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.PODZOL) {
								world.setBlockState(hitResult.getBlockPos(),
										podzol_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.COARSE_DIRT) {
								world.setBlockState(hitResult.getBlockPos(),
										coarse_dirt_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.SAND) {
								world.setBlockState(hitResult.getBlockPos(),
										sand_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.RED_SAND) {
								world.setBlockState(hitResult.getBlockPos(),
										red_sand_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.SANDSTONE) {
								world.setBlockState(hitResult.getBlockPos(),
										sandstone_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.RED_SANDSTONE) {
								world.setBlockState(hitResult.getBlockPos(),
										red_sandstone_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.COBBLESTONE) {
								world.setBlockState(hitResult.getBlockPos(),
										cobblestone_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.MOSSY_COBBLESTONE) {
								world.setBlockState(hitResult.getBlockPos(),
										mossy_cobblestone_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.STONE) {
								world.setBlockState(hitResult.getBlockPos(),
										stone_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.DIORITE) {
								world.setBlockState(hitResult.getBlockPos(),
										diorite_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.GRANITE) {
								world.setBlockState(hitResult.getBlockPos(),
										granite_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.ANDESITE) {
								world.setBlockState(hitResult.getBlockPos(),
										andesite_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.OAK_LEAVES) {
								world.setBlockState(hitResult.getBlockPos(),
										oak_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.BIRCH_LEAVES) {
								world.setBlockState(hitResult.getBlockPos(),
										birch_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.SPRUCE_LEAVES) {
								world.setBlockState(hitResult.getBlockPos(),
										spruce_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.JUNGLE_LEAVES) {
								world.setBlockState(hitResult.getBlockPos(),
										jungle_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.DARK_OAK_LEAVES) {
								world.setBlockState(hitResult.getBlockPos(),
										dark_oak_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.ACACIA_LEAVES) {
								world.setBlockState(hitResult.getBlockPos(),
										acacia_leaves_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.HAY_BLOCK) {
								world.setBlockState(hitResult.getBlockPos(),
										hay_block_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.WHITE_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										white_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.ORANGE_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										orange_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.MAGENTA_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										magenta_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.LIGHT_BLUE_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										light_blue_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.YELLOW_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										yellow_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.LIME_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										lime_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.PINK_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										pink_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.GRAY_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										gray_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.LIGHT_GRAY_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										light_gray_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.CYAN_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										cyan_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.PURPLE_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										purple_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.BLUE_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										blue_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.BROWN_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										brown_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.GREEN_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										green_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.RED_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										red_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
								player.getStackInHand(hand).damage(1, player, null);
							} else if (block == Blocks.BLACK_CONCRETE_POWDER) {
								world.setBlockState(hitResult.getBlockPos(),
										black_concrete_powder_layer.getDefaultState().with(BlockLayer.LAYERS, 8));
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
		sand_layer = registerConcretePowderBlock(Blocks.SAND);
		red_sand_layer = registerConcretePowderBlock(Blocks.RED_SAND);
		sandstone_layer = registerBlock(Blocks.SANDSTONE);
		red_sandstone_layer = registerBlock(Blocks.RED_SANDSTONE);
		gravel_layer = registerConcretePowderBlock(Blocks.GRAVEL);
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

		white_concrete_powder_layer = registerConcretePowderBlock(Blocks.WHITE_CONCRETE_POWDER);
		orange_concrete_powder_layer = registerConcretePowderBlock(Blocks.ORANGE_CONCRETE_POWDER);
		magenta_concrete_powder_layer = registerConcretePowderBlock(Blocks.MAGENTA_CONCRETE_POWDER);
		light_blue_concrete_powder_layer = registerConcretePowderBlock(Blocks.LIGHT_BLUE_CONCRETE_POWDER);
		yellow_concrete_powder_layer = registerConcretePowderBlock(Blocks.YELLOW_CONCRETE_POWDER);
		lime_concrete_powder_layer = registerConcretePowderBlock(Blocks.LIME_CONCRETE_POWDER);
		pink_concrete_powder_layer = registerConcretePowderBlock(Blocks.PINK_CONCRETE_POWDER);
		gray_concrete_powder_layer = registerConcretePowderBlock(Blocks.GRAY_CONCRETE_POWDER);
		light_gray_concrete_powder_layer = registerConcretePowderBlock(Blocks.LIGHT_GRAY_CONCRETE_POWDER);
		cyan_concrete_powder_layer = registerConcretePowderBlock(Blocks.CYAN_CONCRETE_POWDER);
		purple_concrete_powder_layer = registerConcretePowderBlock(Blocks.PURPLE_CONCRETE_POWDER);
		blue_concrete_powder_layer = registerConcretePowderBlock(Blocks.BLUE_CONCRETE_POWDER);
		brown_concrete_powder_layer = registerConcretePowderBlock(Blocks.BROWN_CONCRETE_POWDER);
		green_concrete_powder_layer = registerConcretePowderBlock(Blocks.GREEN_CONCRETE_POWDER);
		red_concrete_powder_layer = registerConcretePowderBlock(Blocks.RED_CONCRETE_POWDER);
		black_concrete_powder_layer = registerConcretePowderBlock(Blocks.BLACK_CONCRETE_POWDER);
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

	private Block registerConcretePowderBlock(Block block) {
		Block block2 = new BlockConcretePowderLayer(block);
		Registry.register(Registry.BLOCK,
				new Identifier("ml", block.getTranslationKey().replace("block.minecraft.", "") + "_layer"), block2);
		BlockItem blockItem = new BlockItem(block2, new Item.Settings().group(itemGroup));
		Registry.register(Registry.ITEM,
				new Identifier("ml", block.getTranslationKey().replace("block.minecraft.", "") + "_layer"), blockItem);
		return block2;
	}
}
