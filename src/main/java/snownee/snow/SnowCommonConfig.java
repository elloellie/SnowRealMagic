package snownee.snow;

import net.minecraftforge.fml.ModList;
import snownee.kiwi.config.KiwiConfig;
import snownee.kiwi.config.KiwiConfig.Comment;

@KiwiConfig
public final class SnowCommonConfig {

    public static boolean placeSnowInBlock = true;
    public static boolean snowGravity = true;
    public static boolean snowAlwaysReplaceable = true;
    public static boolean snowAccumulationDuringSnowstorm = true;
    public static boolean snowAccumulationDuringSnowfall = false;
    public static boolean thinnerBoundingBox = true;
    public static boolean snowMakingIce = true;
    public static boolean snowOnIce = false;
    public static boolean snowNeverMelt = false;
    public static boolean snowMeltsInWarmBiomes = false;
    @Comment("Should snow melt if layers are more than 1")
    public static boolean snowNaturalMelt = !ModList.get().isLoaded("terraforged");
    public static boolean snowReduceFallDamage = true;
    public static boolean replaceWorldFeature = true;
    public static boolean sustainGrassIfLayerMoreThanOne = true;
    @Comment("If you want to uninstall this mod, you probably want to make snow-covered blocks back to normal via random tick.")
    public static boolean retainOriginalBlocks = false;

}
