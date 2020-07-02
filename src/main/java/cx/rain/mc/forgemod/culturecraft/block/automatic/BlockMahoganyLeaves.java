package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.logging.Level;

@ModBlock(name = "mahogany_leaves")
public class BlockMahoganyLeaves extends Block {
    public BlockMahoganyLeaves() {
        super(Properties.create(Material.LEAVES)
                .harvestLevel(0)
                .sound(SoundType.CROP)
                .hardnessAndResistance(1,10));
    }
}
