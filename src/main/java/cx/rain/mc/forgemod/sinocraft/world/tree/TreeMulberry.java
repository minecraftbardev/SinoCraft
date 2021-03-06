package cx.rain.mc.forgemod.sinocraft.world.tree;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.Direction;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class TreeMulberry extends Tree {
    public static final BaseTreeFeatureConfig TREE_MULBERRY_CONFIG = new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.MULBERRY_LOG.get().getDefaultState().with(RotatedPillarBlock.AXIS,
                    Direction.Axis.Y)),
            new SimpleBlockStateProvider(ModBlocks.MULBERRY_LEAVES.get().getDefaultState()),
            new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(3), 3),
            new FancyTrunkPlacer(7, 2, 0),
            new TwoLayerFeature(1, 0, 1))
            .setIgnoreVines()
            .build();

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return Feature.TREE.withConfiguration(TREE_MULBERRY_CONFIG);
    }
}
