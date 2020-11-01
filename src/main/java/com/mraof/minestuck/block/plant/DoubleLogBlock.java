package com.mraof.minestuck.block.plant;

import com.mraof.minestuck.block.MSProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;

public class DoubleLogBlock extends FlammableLogBlock
{
	public static final EnumProperty<Direction.Axis> AXIS_2 = MSProperties.AXIS_2;
	
	public DoubleLogBlock(MaterialColor axisColor, Properties properties)
	{
		super(axisColor, properties);
		setDefaultState(getStateContainer().getBaseState().with(AXIS, Direction.Axis.Y).with(AXIS_2, Direction.Axis.Y));
	}
	
	public DoubleLogBlock(MaterialColor axisColor, int flammability, int encouragement, Properties properties)
	{
		super(axisColor, flammability, encouragement, properties);
		setDefaultState(getStateContainer().getBaseState().with(AXIS, Direction.Axis.Y).with(AXIS_2, Direction.Axis.Y));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		super.fillStateContainer(builder);
		builder.add(AXIS_2);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return super.getStateForPlacement(context).with(AXIS_2, context.getNearestLookingDirection().getAxis());
	}
}