package noppes.npcs.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.util.NBTJsonUtil;

public class ServerCloneController {
	public static ServerCloneController Instance;

	public ServerCloneController() {
		loadClones();
	}

	private void loadClones() {
		try {
			File file = new File(CustomNpcs.getSaveDirectory(true), "clonednpcs.dat");
			if (file.exists()) {
				Map<Integer, Map<String, NBTTagCompound>> clones = loadOldClones(file);
				file.delete();
				file = new File(CustomNpcs.getSaveDirectory(true), "clonednpcs.dat_old");
				if (file.exists()) {
					file.delete();
				}

				for (int tab : clones.keySet()) {
					Map<String, NBTTagCompound> map = clones.get(tab);
					for (String name : map.keySet()) {
						saveClone(tab, name, map.get(name));
					}
				}
			}
		} catch (Exception e) {
			LogWriter.except(e);
		}
	}

	public File getDir() {
		File dir = new File(CustomNpcs.getSaveDirectory(true), "clones");
		if (!dir.exists()) {
			dir.mkdir();
		}
		return dir;
	}

	private Map<Integer, Map<String, NBTTagCompound>> loadOldClones(final File file) throws Exception {
		Map<Integer, Map<String, NBTTagCompound>> clones = new HashMap();
		NBTTagCompound nbttagcompound1 = CompressedStreamTools.readCompressed(new FileInputStream(file));
		NBTTagList list = nbttagcompound1.getTagList("Data", 10);
		if (list == null)
			return clones;
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound compound = list.getCompoundTagAt(i);
			if (!compound.hasKey("ClonedTab")) {
				compound.setInteger("ClonedTab", 1);
			}

			Map<String, NBTTagCompound> tab = clones.get(compound.getInteger("ClonedTab"));
			if (tab == null) {
				clones.put(compound.getInteger("ClonedTab"), tab = new HashMap());
			}

			String name = compound.getString("ClonedName");
			int number = 1;
			while (tab.containsKey(name)) {
				number++;
				name = String.format("%s%s", compound.getString("ClonedName"), number);
			}
			compound.removeTag("ClonedName");
			compound.removeTag("ClonedTab");
			compound.removeTag("ClonedDate");
			cleanTags(compound);
			tab.put(name, compound);
		}
		return clones;
	}

	public NBTTagCompound getCloneData(final ICommandSender player, final String name, final int tab) {
		File file = new File(new File(getDir(), tab + ""), name + ".json");
		if (!file.exists()) {
			if (player != null) {
				player.addChatMessage(new ChatComponentText("Could not find clone file"));
			}
			return null;
		}
		try {
			return NBTJsonUtil.LoadFile(file);
		} catch (Exception e) {
			LogWriter.except(e);
			if (player != null) {
				player.addChatMessage(new ChatComponentText(e.getMessage()));
			}
		}
		return null;
	}

	public void saveClone(final int tab, final String name, final NBTTagCompound compound) {
		try {
			File dir = new File(getDir(), tab + "");
			if (!dir.exists()) {
				dir.mkdir();
			}
			String filename = name + ".json";

			File file = new File(dir, filename + "_new");
			File file2 = new File(dir, filename);

			NBTJsonUtil.SaveFile(file, compound);
			if (file2.exists()) {
				file2.delete();
			}
			file.renameTo(file2);
		} catch (Exception e) {
			LogWriter.except(e);
		}
	}

	public List<String> getClones(final int tab) {
		List<String> list = new ArrayList();
		File dir = new File(getDir(), tab + "");
		if (!dir.exists() || !dir.isDirectory())
			return list;
		for (String file : dir.list()) {
			if (file.endsWith(".json")) {
				list.add(file.substring(0, file.length() - 5));
			}
		}
		return list;
	}

	public boolean removeClone(final String name, final int tab) {
		File file = new File(new File(getDir(), tab + ""), name + ".json");
		if (!file.exists())
			return false;
		file.delete();
		return true;
	}

	public String addClone(final NBTTagCompound nbttagcompound, final String name, final int tab) {
		cleanTags(nbttagcompound);
		saveClone(tab, name, nbttagcompound);
		return name;
	}

	public void cleanTags(final NBTTagCompound nbttagcompound) {
		if (nbttagcompound.hasKey("ItemGiverId")) {
			nbttagcompound.setInteger("ItemGiverId", 0);
		}
		if (nbttagcompound.hasKey("TransporterId")) {
			nbttagcompound.setInteger("TransporterId", -1);
		}

		nbttagcompound.removeTag("StartPosNew");
		nbttagcompound.removeTag("StartPos");
		nbttagcompound.removeTag("MovingPathNew");
		nbttagcompound.removeTag("Pos");
		nbttagcompound.removeTag("Riding");

		if (!nbttagcompound.hasKey("ModRev")) {
			nbttagcompound.setInteger("ModRev", 1);
		}

		if (nbttagcompound.hasKey("TransformRole")) {
			NBTTagCompound adv = nbttagcompound.getCompoundTag("TransformRole");
			adv.setInteger("TransporterId", -1);
			nbttagcompound.setTag("TransformRole", adv);
		}

		if (nbttagcompound.hasKey("TransformJob")) {
			NBTTagCompound adv = nbttagcompound.getCompoundTag("TransformJob");
			adv.setInteger("ItemGiverId", 0);
			nbttagcompound.setTag("TransformJob", adv);
		}

		if (nbttagcompound.hasKey("TransformAI")) {
			NBTTagCompound adv = nbttagcompound.getCompoundTag("TransformAI");
			adv.removeTag("StartPosNew");
			adv.removeTag("StartPos");
			adv.removeTag("MovingPathNew");
			nbttagcompound.setTag("TransformAI", adv);
		}
	}
}
