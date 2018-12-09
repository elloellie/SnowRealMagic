package snownee.snow;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
        modid = SnowRealMagic.MODID,
        name = SnowRealMagic.NAME,
        version = "@VERSION_INJECT@",
        acceptedMinecraftVersions = "[1.12, 1.13)"
)
@EventBusSubscriber
public class SnowRealMagic
{
    public static final String MODID = "snowrealmagic";
    public static final String NAME = "Snow! Real Magic!";

    private static Logger logger;

    public static Block BLOCK = Blocks.SNOW_LAYER;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        BLOCK = new BlockSnowLayer();
        event.getRegistry().register(BLOCK);
        if (ModConfig.placeSnowInBlock)
        {
            GameRegistry.registerTileEntity(TileSnowLayer.class, new ResourceLocation(MODID, "snow"));
        }
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new ItemSnowLayer(BLOCK).setRegistryName(BLOCK.getRegistryName()));
    }

    @SubscribeEvent
    public static void onEntityRegister(RegistryEvent.Register<EntityEntry> event)
    {
        event.getRegistry().register(EntityEntryBuilder.create().entity(EntityFallingSnow.class)
                .id(new ResourceLocation(MODID, "snow"), 0).name(MODID + ".snow").tracker(160, 20, true).build());
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        if (ModConfig.placeSnowInBlock)
        {
            ModelLoader.setCustomStateMapper(BLOCK, new StateMap.Builder().ignore(BlockSnowLayer.TILE).build());
            ClientRegistry.bindTileEntitySpecialRenderer(TileSnowLayer.class, new TESRSnowLayer());
        }
    }
}
