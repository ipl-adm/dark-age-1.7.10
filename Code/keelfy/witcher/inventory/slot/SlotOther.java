package keelfy.witcher.inventory.slot;

import keelfy.witcher.item.IFastUsable;
import keelfy.witcher.item.Armor;
import keelfy.witcher.item.Food;
import keelfy.witcher.item.Melee;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * @author keelfy
 */
public class SlotOther extends DASlot {

	public SlotOther(IInventory inventory, int id, int posX, int posY) {
		super(inventory, id, posX, posY, 64);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		boolean result = false;
		if(!(stack.getItem() instanceof Armor) && !(stack.getItem() instanceof Food) && !(stack.getItem() instanceof Melee) && !(stack.getItem() instanceof ItemBlock))
			result = true;
		return result;
	}
}
