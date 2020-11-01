package com.mraof.minestuck.inventory;

import com.mraof.minestuck.block.MSBlocks;
import com.mraof.minestuck.inventory.slot.InputSlot;
import com.mraof.minestuck.inventory.slot.OutputSlot;
import com.mraof.minestuck.item.MSItems;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class UraniumCookerContainer extends MachineContainer
{
	
	private static final int uraniumInputX = 38;
	private static final int uraniumInputY = 51;
	private static final int itemInputX = 38;
	private static final int itemInputY = 22;
	private static final int itemOutputX = 117;
	private static final int itemOutputY = 35;
	
	private final IntReferenceHolder fuelHolder;
	
	public UraniumCookerContainer(int windowId, PlayerInventory playerInventory, PacketBuffer buffer)
	{
		this(MSContainerTypes.URANIUM_COOKER, windowId, playerInventory, new ItemStackHandler(3), new IntArray(3), IntReferenceHolder.single(), IWorldPosCallable.DUMMY, buffer.readBlockPos());
	}
	
	public UraniumCookerContainer(int windowId, PlayerInventory playerInventory, IItemHandler inventory, IIntArray parameters, IntReferenceHolder fuelHolder, IWorldPosCallable position, BlockPos machinePos)
	{
		this(MSContainerTypes.URANIUM_COOKER, windowId, playerInventory, inventory, parameters, fuelHolder, position, machinePos);
	}
	
	public UraniumCookerContainer(ContainerType<? extends UraniumCookerContainer> type, int windowId, PlayerInventory playerInventory, IItemHandler inventory, IIntArray parameters, IntReferenceHolder fuelHolder, IWorldPosCallable position, BlockPos machinePos)
	{
		super(type, windowId, parameters, position, machinePos);
		
		assertItemHandlerSize(inventory, 3);
		this.fuelHolder = fuelHolder;
		
		addSlot(new SlotItemHandler(inventory, 0, itemInputX, itemInputY));
		addSlot(new InputSlot(inventory, 1, uraniumInputX, uraniumInputY, MSItems.RAW_URANIUM));
		addSlot(new OutputSlot(inventory, 2, itemOutputX, itemOutputY));
		trackInt(fuelHolder);
		
		bindPlayerInventory(playerInventory);
	}
	
	@Override
	protected Block getValidBlock()
	{
		return MSBlocks.URANIUM_COOKER;
	}
	
	protected void bindPlayerInventory(PlayerInventory playerInventory)
	{
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				addSlot(new Slot(playerInventory, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
		
		for (int i = 0; i < 9; i++)
			addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
	}
	
	@Nonnull
	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int slotNumber)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(slotNumber);
		int allSlots = this.inventorySlots.size();
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstackOrig = slot.getStack();
			itemstack = itemstackOrig.copy();
			boolean result = false;
			
			if(slotNumber == 0)    //Shift-clicking from the item input
			{
				result = mergeItemStack(itemstackOrig, 3, allSlots, false);    //Send into the inventory
				
			} else if(slotNumber == 1)    //Shift-clicking from the Uranium input
			{
				result = mergeItemStack(itemstackOrig, 3, allSlots, false);    //Send into the inventory
			} else if(slotNumber == 2)    //Shift-clicking from the output slot
			{
				if(itemstackOrig.getItem() == MSItems.RAW_URANIUM)
					result = mergeItemStack(itemstackOrig, 0, 1, false);    //Send the uranium back to the uranium input
				else
					result = mergeItemStack(itemstackOrig, 3, allSlots, false);    //Send the non-uranium to the inventory
				
			} else    //Shift-clicking from the inventory
			{
				if(itemstackOrig.getItem() == MSItems.RAW_URANIUM)
				{
					result = mergeItemStack(itemstackOrig, 1, 2, false);    //Send the uranium to the uranium input
				} else
				{
					result = mergeItemStack(itemstackOrig, 0, 1, false);    //Send the non-uranium to the other input
				}
			}
			
			if(!result)
				return ItemStack.EMPTY;
			
			if(!itemstackOrig.isEmpty())
				slot.onSlotChanged();
		}
		
		return itemstack;
	}
	
	public int getFuel()
	{
		return fuelHolder.get();
	}
}