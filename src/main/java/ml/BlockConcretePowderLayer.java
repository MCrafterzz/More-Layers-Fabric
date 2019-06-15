package ml;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameMode;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;

public class BlockConcretePowderLayer extends FallingBlock {
	public static final IntProperty LAYERS;
	protected static final VoxelShape[] LAYERS_TO_SHAPE;

	protected BlockConcretePowderLayer(Block block) {
		super(Block.Settings.copy(block));
		this.setDefaultState((BlockState) ((BlockState) this.stateFactory.getDefaultState()).with(LAYERS, 1));
	}

	public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1,
			EntityContext entityContext_1) {
		return LAYERS_TO_SHAPE[(Integer) blockState_1.get(LAYERS)];
	}

	public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1,
			EntityContext entityContext_1) {
		return LAYERS_TO_SHAPE[(Integer) blockState_1.get(LAYERS)];
	}

	public boolean hasSidedTransparency(BlockState blockState_1) {
		return true;
	}

	public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
		BlockState blockState_2 = viewableWorld_1.getBlockState(blockPos_1.down());
		Block block_1 = blockState_2.getBlock();
		if (block_1 != Blocks.BARRIER) {
			if (block_1 == this && blockState_2.get(LAYERS).intValue() < 8) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
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
				return itemPlacementContext_1.getSide() == Direction.UP;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
		BlockState blockState_1 = itemPlacementContext_1.getWorld().getBlockState(itemPlacementContext_1.getBlockPos());
		if (blockState_1.getBlock() == this) {
			int oldHeight = (Integer) blockState_1.get(LAYERS);
			int newHeight = oldHeight + 1;

			if (itemPlacementContext_1.isPlayerSneaking() == true) {
				newHeight += 3;
			}

			newHeight = Math.min(8, newHeight);

			if (itemPlacementContext_1.getWorld().isClient == false
					&& ((ServerPlayerEntity) itemPlacementContext_1.getPlayer()).interactionManager
							.getGameMode() == GameMode.SURVIVAL) {
				newHeight = Math.min(itemPlacementContext_1.getStack().getCount() + oldHeight, newHeight);
				itemPlacementContext_1.getStack().decrement(newHeight - oldHeight - 1);
			}

			return (BlockState) blockState_1.with(LAYERS, newHeight);
		} else {
			if (itemPlacementContext_1.getPlayer().isSneaking() == true
					&& (itemPlacementContext_1.canReplaceExisting() == true || itemPlacementContext_1.getWorld()
							.getBlockState(new BlockPos(itemPlacementContext_1.getHitPos())).isAir() == true)) {
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
}
