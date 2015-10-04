package rtg.world.biome.realistic.thaumcraft;

import cpw.mods.fml.common.Loader;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigTC;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceGrassland;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.TerrainSmallSupport;

public class RealisticBiomeTCBase extends RealisticBiomeBase
{	
	public RealisticBiomeTCBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
	/*
	THAUMCRAFT BIOMES
	
	118: "Tainted Land"
	119: "Magical Forest"
	*/
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("Thaumcraft") && ConfigTC.generateTCBiomes)
		{
			BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
			
			for(int i = 0; i < 256; i++)
			{
				if(b[i] != null)
				{
					BiomeGenBase tcBiome = b[i];
					String biomeName = tcBiome.biomeName;
					String biomeClass = tcBiome.getBiomeClass().getName();
					
					if (biomeName == "Tainted Land")
					{
						if (ConfigTC.generateTCTaintedLand) {
							BiomeBase.addBiome(
								new RealisticBiomeTCTaintedLand(tcBiome),
								BiomeBase.BiomeCategory.SMALL
							);
						}
					}
					else if (biomeName == "Magical Forest")
					{
						if (ConfigTC.generateTCMagicalForest) {
							BiomeBase.addBiome(
								new RealisticBiomeBase(
									tcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
									new TerrainSmallSupport(),
									new SurfaceGrassland(tcBiome.topBlock, tcBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
								),
								BiomeBase.BiomeCategory.SMALL
							);
						}
					}
				}
			}
		}
	}
}