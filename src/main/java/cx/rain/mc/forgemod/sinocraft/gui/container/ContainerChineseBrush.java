package cx.rain.mc.forgemod.sinocraft.gui.container;

import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;

public class ContainerChineseBrush extends Container implements IIntArray {
    public IInventory inventory;
    public byte color;

    protected ContainerChineseBrush(int id, IInventory itemInventory, IInventory playerInventory) {
        super(ModContainers.CHINESE_BRUSH.get(), id);
        this.inventory = itemInventory;
        trackIntArray(this);
        this.addSlot(new Slot(inventory, 0, 14, 23) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem().equals(ModItems.XUAN_PAPER.get()) || stack == ItemStack.EMPTY;
            }
        });
        this.addSlot(new Slot(inventory, 1, 14, 66) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem().equals(ModItems.CHINA_INK.get()) || stack.getItem().equals(ModItems.INK_STONE.get()) || stack == ItemStack.EMPTY;
            }
        });
        layoutPlayerInventorySlots(playerInventory, 45, 155);
        color = 0;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.clearContainer(playerIn, playerIn.world, this.inventory);
    }

    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }


    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }

    public void incColor() {
        color = (byte) Math.max(inventory.getStackInSlot(1).getDamage(), Math.min(15, color + 1));
    }

    public void decColor() {
        color = (byte) Math.max(0, color - 1);
    }

    @Override
    public int get(int i) {
        return color;
    }

    @Override
    public void set(int i, int value) {
        color = (byte) value;
    }

    @Override
    public int size() {
        return 1;
    }
}
