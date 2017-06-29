/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkage.inventory.player;

import keelfy.darkage.items.Armor;
import keelfytools.KeelfyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.NoppesUtilPlayer;

/**
 * @author keelfy
 */
public class DAPlayerInventory implements IInventory {
	public static final int INV_SIZE = 41;
	public ItemStack[] inventoryStacks;
	private final String tagName = "DAPlayerInventory";
	private final EntityPlayer player;

	public DAPlayerInventory(EntityPlayer player) {
		this.player = player;
		inventoryStacks = new ItemStack[INV_SIZE];
	}

	@Override
	public int getSizeInventory() {
		return INV_SIZE;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventoryStacks[slot];
	}

	public ItemStack hasSwordInSlot(int type) {
		if (type == 2) {
			return getStackInSlot(1);

		} else if (type == 1) {
			return getStackInSlot(2);
		}
		return null;
	}

	public void dropAllItems() {
		int i;

		for (i = 0; i < player.inventory.mainInventory.length; ++i) {
			ItemStack is = player.inventory.mainInventory[i];

			if (is != null) {
				if (NoppesUtilPlayer.compareItems(is, player.inventory.getStackInSlot(1), false, false)
						|| NoppesUtilPlayer.compareItems(is, player.inventory.getStackInSlot(2), false, false)
						|| NoppesUtilPlayer.compareItems(is, player.inventory.armorInventory[0], false, false)
						|| NoppesUtilPlayer.compareItems(is, player.inventory.armorInventory[1], false, false)
						|| NoppesUtilPlayer.compareItems(is, player.inventory.armorInventory[2], false, false)
						|| NoppesUtilPlayer.compareItems(is, player.inventory.armorInventory[3], false, false)) {
					continue;
				}

				if (is.hasTagCompound() && is.getTagCompound().hasKey("QuestOnly")
						&& is.getTagCompound().getBoolean("QuestOnly")) {
					continue;
				}

				this.player.func_146097_a(player.inventory.mainInventory[i], true, false);
				this.player.inventory.mainInventory[i] = null;
			}
		}
	}

	public void damageArmor() {
		if (KeelfyUtils.isServerSide()) {
			for (ItemStack is : player.inventory.armorInventory) {
				if (is != null && is.getItem() instanceof Armor) {
					((Armor) is.getItem()).damage(is, 1);
				}
			}
		}
	}

	public int getFirstEmptyStack() {
		if (KeelfyUtils.isServerSide()) {
			for (int i = 12; i < player.inventory.mainInventory.length; i++) {
				if (player.inventory.mainInventory[i] == null)
					return i;
			}
		}
		return -1;
	}

	public boolean hasItem(ItemStack is) {
		if (KeelfyUtils.isServerSide()) {
			int i = this.getSlotByItemStack(is);
			return i >= 0;
		}
		return false;
	}

	public int getSlotByItemStack(ItemStack item) {
		if (KeelfyUtils.isServerSide()) {
			for (int i = 0; i < this.player.inventory.mainInventory.length; ++i) {
				if (this.player.inventory.mainInventory[i] != null
						&& ItemStack.areItemStacksEqual(player.inventory.mainInventory[i], item)) {
					return i;
				} else if (inventoryStacks[i] != null && ItemStack.areItemStacksEqual(inventoryStacks[i], item)) {
					return i;
				}
			}
		}
		return -1;
	}

	public int getPosOfItemDefaultInventory(Item item) {
		if (KeelfyUtils.isServerSide()) {
			for (int i = 0; i < this.player.inventory.mainInventory.length; ++i) {
				if (this.player.inventory.mainInventory[i] != null
						&& player.inventory.mainInventory[i].getItem() == item) {
					return i;
				}
			}
		}
		return -1;
	}

	public int getPosOfItem(Item item) {
		if (KeelfyUtils.isServerSide()) {
			for (int i = 0; i < this.player.inventory.mainInventory.length; ++i) {
				if (this.player.inventory.mainInventory[i] != null
						&& this.player.inventory.mainInventory[i].getItem() == item) {
					return i;
				} else if (inventoryStacks[i] != null && inventoryStacks[i].getItem() == item) {
					return i;
				}
			}
		}
		return -1;
	}

	public boolean consumeInventoryItem(Item item) {
		if (KeelfyUtils.isServerSide()) {
			int i = this.getPosOfItem(item);
			if (i > 0) {
				return false;
			} else {
				if (--this.player.inventory.mainInventory[i].stackSize <= 0) {
					this.player.inventory.mainInventory[i] = null;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (KeelfyUtils.isServerSide()) {
			if (this.inventoryStacks[slot] != null) {
				ItemStack is;

				if (this.inventoryStacks[slot].stackSize <= amount) {
					is = this.inventoryStacks[slot];
					this.inventoryStacks[slot] = null;
					return is;
				} else {
					is = this.inventoryStacks[slot].splitStack(amount);

					if (this.inventoryStacks[slot].stackSize == 0) {
						this.inventoryStacks[slot] = null;
					}

					return is;
				}
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (KeelfyUtils.isServerSide()) {
			ItemStack stack = this.getStackInSlot(slot);
			if (stack != null) {
				this.setInventorySlotContents(slot, (ItemStack) null);
			}
			return stack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		if (KeelfyUtils.isServerSide()) {
			this.inventoryStacks[slot] = itemstack;

			if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
				itemstack.stackSize = this.getInventoryStackLimit();
			}
		}
	}

	@Override
	public void markDirty() {
	}

	@Override
	public String getInventoryName() {
		return tagName;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return false;
	}

	public void writeToNBT(NBTTagCompound compound) {
		if (KeelfyUtils.isServerSide()) {
			NBTTagList tagList = new NBTTagList();

			for (int i = 0; i < this.inventoryStacks.length; ++i) {
				if (this.inventoryStacks[i] != null) {
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setByte("Slot", (byte) i);
					this.inventoryStacks[i].writeToNBT(nbt);
					tagList.appendTag(nbt);
				}
			}
			compound.setTag(tagName, tagList);
		}
	}

	public void readFromNBT(NBTTagCompound compound) {
		NBTTagList tagList = compound.getTagList(tagName, 10);
		this.inventoryStacks = new ItemStack[INV_SIZE];

		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound nbt = tagList.getCompoundTagAt(i);
			int j = nbt.getByte("Slot") & 255;
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbt);

			if (itemstack != null)
				this.inventoryStacks[j] = itemstack;
		}
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	public boolean addItemStackToInventory(ItemStack is) {
		if (KeelfyUtils.isServerSide()) {
			if (this.player == null) {
				return false;
			} else if (is != null && is.stackSize != 0 && is.getItem() != null) {
				int throwable;
				if (is.isItemDamaged()) {
					throwable = this.getFirstEmptyStack();
					if (throwable >= 12) {
						this.player.inventory.mainInventory[throwable] = ItemStack.copyItemStack(is);
						this.player.inventory.mainInventory[throwable].animationsToGo = 5;
						is.stackSize = 0;
						return true;
					} else if (this.player.capabilities.isCreativeMode) {
						is.stackSize = 0;
						return true;
					} else {
						return false;
					}
				} else {
					do {
						throwable = is.stackSize;
						is.stackSize = this.storePartialItemStack(is);
					} while (is.stackSize > 0 && is.stackSize < throwable);

					if (is.stackSize == throwable && this.player.capabilities.isCreativeMode) {
						is.stackSize = 0;
						return true;
					} else {
						return is.stackSize < throwable;
					}
				}
			} else {
				return false;
			}
		}
		return false;
	}

	private int storePartialItemStack(ItemStack p_70452_1_) {
		if (KeelfyUtils.isServerSide()) {
			Item item = p_70452_1_.getItem();
			int i = p_70452_1_.stackSize;
			int j;
			if (p_70452_1_.getMaxStackSize() == 1) {
				j = this.getFirstEmptyStack();
				if (j < 0) {
					return i;
				} else {
					if (this.player.inventory.mainInventory[j] == null) {
						this.player.inventory.mainInventory[j] = ItemStack.copyItemStack(p_70452_1_);
					}

					return 0;
				}
			} else {
				j = this.storeItemStack(p_70452_1_);
				if (j < 0) {
					j = this.getFirstEmptyStack();
				}

				if (j < 0) {
					return i;
				} else {
					if (this.player.inventory.mainInventory[j] == null) {
						this.player.inventory.mainInventory[j] = new ItemStack(item, 0, p_70452_1_.getItemDamage());
						if (p_70452_1_.hasTagCompound()) {
							this.player.inventory.mainInventory[j]
									.setTagCompound((NBTTagCompound) p_70452_1_.getTagCompound().copy());
						}
					}

					int k = i;
					if (i > this.player.inventory.mainInventory[j].getMaxStackSize()
							- this.player.inventory.mainInventory[j].stackSize) {
						k = this.player.inventory.mainInventory[j].getMaxStackSize()
								- this.player.inventory.mainInventory[j].stackSize;
					}

					if (k > this.getInventoryStackLimit() - this.player.inventory.mainInventory[j].stackSize) {
						k = this.getInventoryStackLimit() - this.player.inventory.mainInventory[j].stackSize;
					}

					if (k == 0) {
						return i;
					} else {
						i -= k;
						this.player.inventory.mainInventory[j].stackSize += k;
						this.player.inventory.mainInventory[j].animationsToGo = 5;
						return i;
					}
				}
			}
		}
		return 0;
	}

	private int storeItemStack(ItemStack p_70432_1_) {
		if (KeelfyUtils.isServerSide()) {
			for (int i = 12; i < this.player.inventory.mainInventory.length; ++i) {
				if (this.player.inventory.mainInventory[i] != null
						&& this.player.inventory.mainInventory[i].getItem() == p_70432_1_.getItem()
						&& this.player.inventory.mainInventory[i].isStackable()
						&& this.player.inventory.mainInventory[i].stackSize < this.player.inventory.mainInventory[i]
								.getMaxStackSize()
						&& this.player.inventory.mainInventory[i].stackSize < this.getInventoryStackLimit()
						&& (!this.player.inventory.mainInventory[i].getHasSubtypes()
								|| this.player.inventory.mainInventory[i].getItemDamage() == p_70432_1_.getItemDamage())
						&& ItemStack.areItemStackTagsEqual(this.player.inventory.mainInventory[i], p_70432_1_)) {
					this.player.inventory.markDirty();
					return i;
				}
			}
		}
		return -1;
	}
}
