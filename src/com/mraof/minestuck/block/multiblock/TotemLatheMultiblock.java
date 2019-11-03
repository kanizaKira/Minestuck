package com.mraof.minestuck.block.multiblock;

import com.mraof.minestuck.block.TotemLatheBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.IForgeRegistry;

public class TotemLatheMultiblock extends MachineMultiblock
{
	public Block CARD_SLOT, BOTTOM_LEFT, BOTTOM_RIGHT, BOTTOM_CORNER;
	public Block MIDDLE, WHEEL, ROD, DOWEL_ROD;
	public Block TOP_CORNER, TOP, CARVER;
	
	@Override
	public Block getMainBlock()
	{
		return MIDDLE;
	}
	
	@Override
	public void registerBlocks(IForgeRegistry<Block> registry)
	{
		registry.register(CARD_SLOT = new TotemLatheBlock.Slot(this, TotemLatheBlock.CARD_SLOT_SHAPE, Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_card_slot"));
		registry.register(BOTTOM_LEFT = new TotemLatheBlock(this, TotemLatheBlock.BOTTOM_LEFT_SHAPE, new BlockPos(1, 0, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_bottom_left"));
		registry.register(BOTTOM_RIGHT = new TotemLatheBlock(this, TotemLatheBlock.BOTTOM_RIGHT_SHAPE, new BlockPos(2, 0, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_bottom_right"));
		registry.register(BOTTOM_CORNER = new TotemLatheBlock(this, TotemLatheBlock.BOTTOM_CORNER_SHAPE, new BlockPos(3, 0, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_bottom_corner"));
		registry.register(MIDDLE = new TotemLatheBlock(this, TotemLatheBlock.MIDDLE_SHAPE, new BlockPos(0, -1, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_middle"));
		registry.register(WHEEL = new TotemLatheBlock.Rod(this, TotemLatheBlock.WHEEL_SHAPE, new BlockPos(3, -1, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_wheel"));
		registry.register(ROD = new TotemLatheBlock.Rod(this, TotemLatheBlock.ROD_SHAPE, new BlockPos(1, -1, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_rod"));
		registry.register(DOWEL_ROD = new TotemLatheBlock.DowelRod(this, TotemLatheBlock.ROD_SHAPE, new BlockPos(2, -1, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_dowel_rod"));
		registry.register(TOP_CORNER = new TotemLatheBlock(this, TotemLatheBlock.TOP_CORNER_SHAPE, new BlockPos(0, -2, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_top_corner"));
		registry.register(TOP = new TotemLatheBlock(this, TotemLatheBlock.TOP_SHAPE, new BlockPos(1, -2, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_top"));
		registry.register(CARVER = new TotemLatheBlock(this, TotemLatheBlock.CARVER_SHAPE, new BlockPos(2, -2, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F).noDrops()).setRegistryName("totem_lathe_carver"));
		
	}
}