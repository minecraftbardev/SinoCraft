package cx.rain.mc.forgemod.sinocraft.structure.tree;

import cx.rain.mc.forgemod.sinocraft.world.gen.Features;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class TreePeach extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        // Fixme: Maybe issues here.
        //return Feature.TREE.withConfiguration(Features.TREE_PEACH_CONFIG);
        return null;
    }
}