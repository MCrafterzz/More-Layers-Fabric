package ml;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameMode;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;

public class BlockLayer extends Block {

	public static final IntProperty LAYERS;
	protected static final VoxelShape[] LAYERS_TO_SHAPE;
	public BlockRenderLayer blockRenderLayer;

	protected BlockLayer(Block block, BlockRenderLayer blockRenderLayer) {
		super(Block.Settings.copy(block));
		this.blockRenderLayer = blockRenderLayer;
		this.setDefaultState((BlockState) ((BlockState) this.stateFactory.getDefaultState()).with(LAYERS, 1));
	}

	public boolean canPlaceAtSide(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1,
			BlockPlacementEnvironment blockPlacementEnvironment_1) {
		switch (blockPlacementEnvironment_1) {
		case LAND:
			return (Integer) blockState_1.get(LAYERS) < 5;
		case WATER:
			return false;
		case AIR:
			return false;
		default:
			return false;
		}
	}

	public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1,
			EntityContext entityContext) {
		return LAYERS_TO_SHAPE[(Integer) blockState_1.get(LAYERS)];
	}

	public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1,
			EntityContext entityContext) {
		return LAYERS_TO_SHAPE[(Integer) blockState_1.get(LAYERS)];
	}

	public boolean method_9526(BlockState blockState_1) {
		return true;
	}

	public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
		BlockState blockState_2 = viewableWorld_1.getBlockState(blockPos_1.down());
		Block block_1 = blockState_2.getBlock();

		return Block.isFaceFullSquare(blockState_2.getCollisionShape(viewableWorld_1, blockPos_1.down()), Direction.UP)
				|| blockState_2.matches(BlockTags.LEAVES) || block_1 == this && (Integer) blockState_2.get(LAYERS) == 8;
	}

	public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2,
			IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
		return !blockState_1.canPlaceAt(iWorld_1, blockPos_1) ? Blocks.AIR.getDefaultState()
				: super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1,
						blockPos_2);
	}

	public boolean canReplace(BlockState blockState_1, ItemPlacementContext itemPlacementContext_1) {
		int int_1 = (Integer) blockState_1.get(LAYERS);
		if (itemPlacementContext_1.getStack().getItem() == this.asItem() && int_1 < 8) {
			if (itemPlacementContext_1.canReplaceExisting()) {
				return itemPlacementContext_1.getPlayerFacing() == Direction.UP;
			} else {
				return true;
			}
		} else {
			return int_1 == 1;
		}
	}

	public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
		BlockState blockState_1 = itemPlacementContext_1.getWorld().getBlockState(itemPlacementContext_1.getBlockPos());
		if (blockState_1.getBlock() == this) {
			int int_1 = (Integer) blockState_1.get(LAYERS);
			int newHeight = int_1 + 1;

			if (itemPlacementContext_1.getPlayer().isSneaking() == true) {
				newHeight += 3;
				newHeight = Math.min(8, newHeight);

				if (itemPlacementContext_1.getWorld().isClient == false
						&& ((ServerPlayerEntity) itemPlacementContext_1.getPlayer()).interactionManager
								.getGameMode() == GameMode.SURVIVAL) {
					newHeight = Math.min(itemPlacementContext_1.getStack().getCount() + int_1, newHeight);
					itemPlacementContext_1.getStack().decrement(newHeight - int_1 - 1);
				}
			}
			return (BlockState) blockState_1.with(LAYERS, newHeight);
		} else {
			if (itemPlacementContext_1.getPlayer().isSneaking() == true) {
				int newHeight = 4;
				if (itemPlacementContext_1.getWorld().isClient == false
						&& ((ServerPlayerEntity) itemPlacementContext_1.getPlayer()).interactionManager
								.getGameMode() == GameMode.SURVIVAL) {
					newHeight = Math.min(itemPlacementContext_1.getStack().getCount(), newHeight);
					itemPlacementContext_1.getStack().decrement(newHeight - 1);
				}

				return super.getPlacementState(itemPlacementContext_1).with(LAYERS, newHeight);
			} else {
				return super.getPlacementState(itemPlacementContext_1);
			}
		}
	}

	protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
		stateFactory$Builder_1.add(LAYERS);
	}

	static {
		LAYERS = Properties.LAYERS;
		LAYERS_TO_SHAPE = new VoxelShape[] { VoxelShapes.empty(),
				Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
				Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
				Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
				Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
				Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
				Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
				Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
				Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };
	}

	public boolean isFullBoundsCubeForCulling(BlockState blockState_1) {
		return blockRenderLayer == BlockRenderLayer.SOLID ? true : false;
	}

	public BlockRenderLayer getRenderLayer() {
		return blockRenderLayer;
	}
}
