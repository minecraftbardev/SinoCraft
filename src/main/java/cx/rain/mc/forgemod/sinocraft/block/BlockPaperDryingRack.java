package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.api.base.BlockActivatable;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockPaperDryingRack extends BlockActivatable {
    public static IntegerProperty LEVEL = IntegerProperty.create("level",0,4);
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    public BlockPaperDryingRack() {
        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(3.0F)
                .sound(SoundType.WOOD).notSolid());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(LEVEL, FACING);
    }

    @Override
    public boolean ticksRandomly(BlockState p_149653_1_) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LEVEL)!=0)
            world.setBlockState(pos,state.with(LEVEL,Math.min(state.get(LEVEL)+random.nextInt(2) + 1, 4)));
    }

    @Override
    public ActionResultType clientActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(LEVEL) == 4 && (player.getHeldItem(handIn) == ItemStack.EMPTY || player.getHeldItem(handIn).getItem() == Items.XUAN_PAPER.get())){
            worldIn.playSound(player,pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS,1.0f,1.0f);
            return ActionResultType.SUCCESS;
        }
        else if (state.get(LEVEL) == 0 && player.getHeldItem(handIn).getItem() == Items.BUCKET_WOOD_PULP.get()) {
            worldIn.playSound(player,pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS,1.0f,1.0f);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    public ActionResultType serverActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(LEVEL) == 4 && (player.getHeldItem(handIn) == ItemStack.EMPTY || player.getHeldItem(handIn).getItem() == Items.XUAN_PAPER.get())){
            worldIn.setBlockState(pos,state.with(LEVEL, 0));
            player.inventory.addItemStackToInventory(new ItemStack(Items.XUAN_PAPER.get(), worldIn.getRandom().nextInt(2) + 1));
            return ActionResultType.SUCCESS;
        }
        else if (state.get(LEVEL) == 0 && player.getHeldItem(handIn).getItem() == Items.BUCKET_WOOD_PULP.get()) {
            worldIn.setBlockState(pos,state.with(LEVEL, 1) );
            player.setHeldItem(handIn,new ItemStack(net.minecraft.item.Items.BUCKET));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
}
