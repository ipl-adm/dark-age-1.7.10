package noppes.npcs;

import java.io.File;
import java.util.Set;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import foxz.command.CommandNoppes;
import keelfyutils.KUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockVine;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import noppes.npcs.config.ConfigLoader;
import noppes.npcs.config.ConfigProp;
import noppes.npcs.controllers.BankController;
import noppes.npcs.controllers.ChunkController;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.GlobalDataController;
import noppes.npcs.controllers.LinkedNpcController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.controllers.SpawnController;
import noppes.npcs.controllers.TransportController;
import noppes.npcs.enchants.EnchantInterface;
import noppes.npcs.entity.EntityChairMount;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCGolem;
import noppes.npcs.entity.EntityNpcCrystal;
import noppes.npcs.entity.EntityNpcDragon;
import noppes.npcs.entity.EntityNpcPony;
import noppes.npcs.entity.EntityNpcSlime;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.entity.old.EntityNPCDwarfFemale;
import noppes.npcs.entity.old.EntityNPCDwarfMale;
import noppes.npcs.entity.old.EntityNPCElfFemale;
import noppes.npcs.entity.old.EntityNPCElfMale;
import noppes.npcs.entity.old.EntityNPCEnderman;
import noppes.npcs.entity.old.EntityNPCFurryFemale;
import noppes.npcs.entity.old.EntityNPCFurryMale;
import noppes.npcs.entity.old.EntityNPCHumanFemale;
import noppes.npcs.entity.old.EntityNPCHumanMale;
import noppes.npcs.entity.old.EntityNPCOrcFemale;
import noppes.npcs.entity.old.EntityNPCOrcMale;
import noppes.npcs.entity.old.EntityNPCVillager;
import noppes.npcs.entity.old.EntityNpcEnderchibi;
import noppes.npcs.entity.old.EntityNpcMonsterFemale;
import noppes.npcs.entity.old.EntityNpcMonsterMale;
import noppes.npcs.entity.old.EntityNpcNagaFemale;
import noppes.npcs.entity.old.EntityNpcNagaMale;
import noppes.npcs.entity.old.EntityNpcSkeleton;

@Mod(modid = "customnpcs", name = "CustomNpcs Witcher Edition", version = "1.7.10d", dependencies = "required-after:witchercore")
public class CustomNpcs {

	@ConfigProp(info = "Disable Chat Bubbles")
	public static boolean EnableChatBubbles = true;

	private static int NewEntityStartId = 0;

	@ConfigProp(info = "Navigation search range for NPCs. Not recommended to increase if you have a slow pc or on a server")
	public static int NpcNavRange = 32;

	@ConfigProp(info = "Set to true if you want the dialog command option to be able to use op commands like tp etc")
	public static boolean NpcUseOpCommands = false;

	@ConfigProp
	public static boolean InventoryGuiEnabled = true;

	@ConfigProp
	public static boolean DisableExtraItems = false;
	@ConfigProp
	public static boolean DisableExtraBlock = false;

	public static boolean PixelMonEnabled = false;

	public static long ticks;

	@SidedProxy(clientSide = "noppes.npcs.client.ClientProxy", serverSide = "noppes.npcs.CommonProxy")
	public static CommonProxy proxy;

	@ConfigProp(info = "Enables CustomNpcs startup update message")
	public static boolean EnableUpdateChecker = true;

	public static CustomNpcs instance;

	public static boolean FreezeNPCs = false;

	@ConfigProp(info = "Only ops can create and edit npcs")
	public static boolean OpsOnly = false;

	@ConfigProp(info = "Default interact line. Leave empty to not have one")
	public static String DefaultInteractLine = "Hello @p";

	@ConfigProp
	public static boolean DisableEnchants = false;
	@ConfigProp(info = "Start Id for enchants. IDs can only range from 0-256")
	public static int EnchantStartId = 100;

	@ConfigProp(info = "Number of chunk loading npcs that can be active at the same time")
	public static int ChuckLoaders = 20;

	public static File Dir;

	@ConfigProp(info = "Set to false if you want to disable guns")
	public static boolean GunsEnabled = true;

	@ConfigProp(info = "Enables leaves decay")
	public static boolean LeavesDecayEnabled = true;

	@ConfigProp(info = "Enables Vine Growth")
	public static boolean VineGrowthEnabled = true;

	@ConfigProp(info = "Enables Ice Melting")
	public static boolean IceMeltsEnabled = true;

	@ConfigProp(info = "Normal players can use soulstone on animals")
	public static boolean SoulStoneAnimals = true;

	public static FMLEventChannel Channel;
	public static FMLEventChannel ChannelPlayer;

	public static ConfigLoader Config;

	public CustomNpcs() {
		instance = this;
	}

	@EventHandler
	public void load(final FMLPreInitializationEvent ev) {
		Channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("CustomNPCs");
		ChannelPlayer = NetworkRegistry.INSTANCE.newEventDrivenChannel("CustomNPCsPlayer");

		MinecraftServer server = MinecraftServer.getServer();
		String dir = "";
		if (server != null) {
			dir = new File(".").getAbsolutePath();
		} else {
			dir = Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
		}
		Dir = new File(dir, "customnpcs");

		if (!Dir.exists()) {
			Dir.mkdir();
		}

		Config = new ConfigLoader(this.getClass(), new File(dir, "config"), "CustomNpcs");
		Config.loadConfig();

		if (NpcNavRange < 16) {
			NpcNavRange = 16;
		}
		EnchantInterface.load();
		CustomItems.load();

		proxy.load();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);

		MinecraftForge.EVENT_BUS.register(new ServerEventsHandler());
		MinecraftForge.EVENT_BUS.register(new ScriptController());

		FMLCommonHandler.instance().bus().register(new ServerTickHandler());

		PixelMonEnabled = Loader.isModLoaded("pixelmon");

		registerNpc(EntityNPCHumanMale.class, "npchumanmale");
		registerNpc(EntityNPCVillager.class, "npcvillager");
		registerNpc(EntityNpcPony.class, "npcpony");
		registerNpc(EntityNPCHumanFemale.class, "npchumanfemale");
		registerNpc(EntityNPCDwarfMale.class, "npcdwarfmale");
		registerNpc(EntityNPCFurryMale.class, "npcfurrymale");
		registerNpc(EntityNpcMonsterMale.class, "npczombiemale");
		registerNpc(EntityNpcMonsterFemale.class, "npczombiefemale");
		registerNpc(EntityNpcSkeleton.class, "npcskeleton");
		registerNpc(EntityNPCDwarfFemale.class, "npcdwarffemale");
		registerNpc(EntityNPCFurryFemale.class, "npcfurryfemale");
		registerNpc(EntityNPCOrcMale.class, "npcorcfmale");
		registerNpc(EntityNPCOrcFemale.class, "npcorcfemale");
		registerNpc(EntityNPCElfMale.class, "npcelfmale");
		registerNpc(EntityNPCElfFemale.class, "npcelffemale");
		registerNpc(EntityNpcCrystal.class, "npccrystal");
		registerNpc(EntityNpcEnderchibi.class, "npcenderchibi");
		registerNpc(EntityNpcNagaMale.class, "npcnagamale");
		registerNpc(EntityNpcNagaFemale.class, "npcnagafemale");
		registerNpc(EntityNpcSlime.class, "NpcSlime");
		registerNpc(EntityNpcDragon.class, "NpcDragon");
		registerNpc(EntityNPCEnderman.class, "npcEnderman");
		registerNpc(EntityNPCGolem.class, "npcGolem");
		registerNpc(EntityCustomNpc.class, "CustomNpc");

		registerNewEntity(EntityChairMount.class, "CustomNpcChairMount", 64, 10, false);
		registerNewEntity(EntityProjectile.class, "throwableitem", 64, 3, true);

		new RecipeController();

		ForgeChunkManager.setForcedChunkLoadingCallback(this, new ChunkController());

		new CustomNpcsPermissions();
	}

	@EventHandler
	public void setAboutToStart(final FMLServerAboutToStartEvent event) {
		if (KUtils.PROTECT_SERVER) {
			ChunkController.instance.clear();
			new QuestController();
			new PlayerDataController();
			new FactionController();
			new TransportController();
			new GlobalDataController();
			new SpawnController();
			new LinkedNpcController();
			ScriptController.Instance.loadStoredData();
			ScriptController.HasStart = false;

			Set<String> names = Block.blockRegistry.getKeys();
			for (String name : names) {
				Block block = (Block) Block.blockRegistry.getObject(name);
				if (block instanceof BlockLeavesBase) {
					block.setTickRandomly(LeavesDecayEnabled);
				}
				if (block instanceof BlockVine) {
					block.setTickRandomly(VineGrowthEnabled);
				}
				if (block instanceof BlockIce) {
					block.setTickRandomly(IceMeltsEnabled);
				}
			}
		}
	}

	// Loading items in the about to start event was corrupting items with a damage
	// value
	@EventHandler
	public void started(final FMLServerStartedEvent event) {
		if (KUtils.PROTECT_SERVER) {
			RecipeController.instance.load();
			new DialogController();
			new BankController();
			QuestController.instance.load();
			ScriptController.HasStart = true;
			ServerCloneController.Instance = new ServerCloneController();
		}
	}

	@EventHandler
	public void stopped(final FMLServerStoppedEvent event) {
		if (KUtils.PROTECT_SERVER) {
			ServerCloneController.Instance = null;
		}
	}

	@EventHandler
	public void serverstart(final FMLServerStartingEvent event) {
		if (KUtils.PROTECT_SERVER) {
			event.registerServerCommand(new CommandNoppes());
		}
	}

	private void registerNpc(final Class<? extends Entity> cl, final String name) {
		EntityList.stringToClassMapping.put(name, cl);
		EntityRegistry.registerModEntity(cl, name, NewEntityStartId++, this, 80, 3, true);
	}

	private void registerNewEntity(final Class<? extends Entity> cl, final String name, final int range,
			final int update, final boolean velocity) {
		EntityRegistry.registerModEntity(cl, name, NewEntityStartId++, this, range, update, velocity);
	}

	public static File getSaveDirectory(final boolean isGlobal) {
		final MinecraftServer server = MinecraftServer.getServer();
		File saves = new File(".");

		if (server != null) {
			if (!server.isDedicatedServer()) {
				saves = new File(Minecraft.getMinecraft().mcDataDir, "CustomNPCs");
			} else {
				saves = new File(saves, "CustomNPCs");
			}

			if (isGlobal) {
				saves = new File(saves, "Global Data");
			} else {
				saves = new File(saves, "Worlds Data/" + server.getFolderName().trim());
			}

			if (!saves.exists()) {
				saves.mkdir();
			}

			return saves;
		}
		return null;
	}

}
