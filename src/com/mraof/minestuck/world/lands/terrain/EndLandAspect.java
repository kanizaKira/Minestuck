package com.mraof.minestuck.world.lands.terrain;

import com.mraof.minestuck.block.MSBlocks;
import com.mraof.minestuck.entity.MSEntityTypes;
import com.mraof.minestuck.entity.consort.ConsortEntity;
import com.mraof.minestuck.world.biome.LandBiomeHolder;
import com.mraof.minestuck.world.biome.LandWrapperBiome;
import com.mraof.minestuck.world.biome.MSBiomes;
import com.mraof.minestuck.world.gen.feature.MSFeatures;
import com.mraof.minestuck.world.gen.feature.MSFillerBlockTypes;
import com.mraof.minestuck.world.gen.structure.blocks.StructureBlockRegistry;

import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

public class EndLandAspect extends TerrainLandAspect
{
	private static final Vec3d fogColor = new Vec3d(0.0D, 0.4D, 0.2D);
	private static final Vec3d skyColor = new Vec3d(0.3D, 0.1D, 0.5D);
	
	public EndLandAspect()
	{
		super();
	}
	
	@Override
	public void registerBlocks(StructureBlockRegistry registry)
	{
		registry.setGroundState(Blocks.END_STONE.getDefaultState(), MSFillerBlockTypes.END_STONE);
		registry.setBlockState("surface", MSBlocks.END_GRASS.getDefaultState());
		registry.setBlockState("upper", MSBlocks.COARSE_END_STONE.getDefaultState());
		registry.setBlockState("ocean", MSBlocks.ENDER.getDefaultState());
		registry.setBlockState("structure_primary", Blocks.END_STONE_BRICKS.getDefaultState());
		registry.setBlockState("structure_primary_decorative", Blocks.PURPUR_PILLAR.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
		registry.setBlockState("structure_primary_stairs", Blocks.PURPUR_STAIRS.getDefaultState());
		registry.setBlockState("structure_secondary", Blocks.PURPUR_BLOCK.getDefaultState());
		registry.setBlockState("structure_secondary_stairs", Blocks.STONE_BRICK_STAIRS.getDefaultState());
		registry.setBlockState("structure_planks", Blocks.BRICKS.getDefaultState());
		registry.setBlockState("structure_planks_slab", Blocks.BRICK_SLAB.getDefaultState());
		registry.setBlockState("village_path", MSBlocks.COARSE_END_STONE.getDefaultState());
		registry.setBlockState("village_fence", Blocks.NETHER_BRICK_FENCE.getDefaultState());
		registry.setBlockState("structure_wool_1", Blocks.GREEN_WOOL.getDefaultState());
		registry.setBlockState("structure_wool_3", Blocks.PURPLE_WOOL.getDefaultState());
	}
	
	@Override
	public String[] getNames()
	{
		return new String[] {"end", "dimension"};
	}
	
	@Override
	public void setBiomeSettings(LandBiomeHolder settings)
	{
		settings.category = Biome.Category.THEEND;
		settings.downfall = 0.0F;
		settings.temperature = 1.2F;
	}
	
	@Override
	public void setBiomeGenSettings(LandWrapperBiome biome, StructureBlockRegistry blocks)
	{
		if(biome.staticBiome == MSBiomes.LAND_NORMAL)
		{
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(MSFeatures.END_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
		} else if(biome.staticBiome == MSBiomes.LAND_ROUGH)
		{
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(MSFeatures.END_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(3, 0.1F, 1)));
		}
		
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(blocks.getGroundType(), Blocks.COARSE_DIRT.getDefaultState(), 16), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 128)));
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(blocks.getGroundType(), Blocks.GRAVEL.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 256)));
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(blocks.getGroundType(), MSBlocks.END_STONE_IRON_ORE.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(blocks.getGroundType(), MSBlocks.END_STONE_REDSTONE_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 32)));
		
	}
	
	@Override
	public Vec3d getFogColor() 
	{
		return fogColor;
	}
	
	@Override
	public Vec3d getSkyColor()
	{
		return skyColor;
	}
	
	@Override
	public EntityType<? extends ConsortEntity> getConsortType()
	{
		return MSEntityTypes.NAKAGATOR;
	}
}