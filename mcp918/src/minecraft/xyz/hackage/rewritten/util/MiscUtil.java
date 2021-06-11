package xyz.hackage.rewritten.util;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;

public class MiscUtil {
	public static ArrayList<BlockPos> searchForBlock(String blockName , float range) {
        ArrayList<BlockPos> blocks = new ArrayList<>();

        EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;

        for (float x = -range; x < range - 1; x++) {
            for (float y = -range; y < range - 1; y++) {
                for (float z =  -range; z < range - 1; z++) {
                    BlockPos blockPos = new BlockPos(thePlayer.getPosition().getX() + x, thePlayer.getPosition().getY() + y,
                            thePlayer.getPosition().getZ() + z);
                    if (Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock().getUnlocalizedName().equals(blockName)) {
                        blocks.add(blockPos);
                    }
                }
            }
        }

        return blocks;
    }
}
