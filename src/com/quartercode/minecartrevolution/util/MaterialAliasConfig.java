
package com.quartercode.minecartrevolution.util;

import com.quartercode.minecartrevolution.MinecartRevolution;
import com.quartercode.minecartrevolution.conf.FileConf;

public class MaterialAliasConfig extends Config {

    public MaterialAliasConfig() {

        super(FileConf.MATERIAL_ALIAS_CONF);
    }

    public int getId(String material) {

        try {
            return (int) Double.parseDouble(material);
        }
        catch (NumberFormatException e) {
            try {
                return getConfig().getInt(material);
            }
            catch (NumberFormatException e1) {
                MinecartRevolution.handleSilenceThrowable(e1);
                return 0;
            }
        }
    }

    @Override
    public void setDefaults() {

        addDefault("stone", 1);
        addDefault("sstone", 1);
        addDefault("smoothstone", 1);
        addDefault("rock", 1);
        addDefault("grass", 2);
        addDefault("dirt", 3);
        addDefault("cobb", 4);
        addDefault("cobble", 4);
        addDefault("cobblestone", 4);
        addDefault("cstone", 4);
        addDefault("wood", 5);
        addDefault("plank", 5);
        addDefault("planks", 5);
        addDefault("sapling", 6);
        addDefault("bedrock", 7);
        addDefault("sand", 12);
        addDefault("gravel", 13);
        addDefault("goldore", 14);
        addDefault("gold_ore", 14);
        addDefault("ironore", 15);
        addDefault("iron_ore", 15);
        addDefault("log", 17);
        addDefault("tree", 17);
        addDefault("leave", 18);
        addDefault("leaves", 18);
        addDefault("sponge", 19);
        addDefault("glass", 20);
        addDefault("lapisblock", 22);
        addDefault("lapis_block", 22);
        addDefault("dispenser", 23);
        addDefault("sandstone", 24);
        addDefault("sand_stone", 24);
        addDefault("noteblock", 25);
        addDefault("note_block", 25);
        addDefault("poweredrail", 27);
        addDefault("powered_rail", 27);
        addDefault("detectorrail", 28);
        addDefault("detector_rail", 28);
        addDefault("stickypiston", 29);
        addDefault("sticky_piston", 29);
        addDefault("deadbush", 32);
        addDefault("dead_bush", 32);
        addDefault("piston", 33);
        addDefault("wool", 35);
        addDefault("cloth", 35);
        addDefault("flower", 37);
        addDefault("yellow_flower", 37);
        addDefault("redflower", 38);
        addDefault("red_flower", 38);
        addDefault("rose", 38);
        addDefault("brownmushroom", 39);
        addDefault("brown_mushroom", 39);
        addDefault("redmushroom", 40);
        addDefault("red_mushroom", 40);
        addDefault("goldblock", 41);
        addDefault("gold_block", 41);
        addDefault("ironblock", 42);
        addDefault("iron_block", 42);
        addDefault("step", 44);
        addDefault("slab", 44);
        addDefault("half", 44);
        addDefault("halfstep", 44);
        addDefault("half_setp", 44);
        addDefault("halfslab", 44);
        addDefault("half_slab", 44);
        addDefault("brick", 45);
        addDefault("bricks", 45);
        addDefault("tnt", 46);
        addDefault("bookshelf", 47);
        addDefault("book_shelf", 47);
        addDefault("mossy", 48);
        addDefault("mossystone", 48);
        addDefault("mossy_stone", 48);
        addDefault("mossycobb", 48);
        addDefault("mossy_cobb", 48);
        addDefault("mossycobble", 48);
        addDefault("mossy_cobble", 48);
        addDefault("mossycobblestone", 48);
        addDefault("mossy_cobblestone", 48);
        addDefault("obsidian", 49);
        addDefault("torch", 50);
        addDefault("woodstair", 53);
        addDefault("wood_stair", 53);
        addDefault("woodenstair", 53);
        addDefault("wooden_stair", 53);
        addDefault("chest", 54);
        addDefault("diamondblock", 57);
        addDefault("diamond_block", 57);
        addDefault("workbench", 58);
        addDefault("furnace", 61);
        addDefault("ladder", 65);
        addDefault("rail", 66);
        addDefault("stonestair", 67);
        addDefault("stone_stair", 67);
        addDefault("lever", 69);
        addDefault("stonepressure", 70);
        addDefault("stone_pressure", 70);
        addDefault("woodpressure", 72);
        addDefault("wood_pressure", 72);
        addDefault("woodenpressure", 72);
        addDefault("wooden_pressure", 72);
        addDefault("redtorch", 76);
        addDefault("red_torch", 76);
        addDefault("redstonetorch", 76);
        addDefault("redstone_torch", 76);
        addDefault("button", 77);
        addDefault("ice", 79);
        addDefault("snow", 80);
        addDefault("cactus", 81);
        addDefault("clay", 82);
        addDefault("jukebox", 84);
        addDefault("fence", 85);
        addDefault("pumpkin", 86);
        addDefault("netherrack", 87);
        addDefault("nether_rack", 87);
        addDefault("soulsand", 88);
        addDefault("soul_sand", 88);
        addDefault("glowstone", 89);
        addDefault("glow_stone", 89);
        addDefault("lantern", 91);
        addDefault("pumpkinlantern", 91);
        addDefault("pumpkin_lantern", 91);
        addDefault("jack_o_lantern", 91);
        addDefault("trapdoor", 96);
        addDefault("trap_door", 96);
        addDefault("brickstone", 98);
        addDefault("brick_stone", 98);
        addDefault("ironbars", 101);
        addDefault("iron_bars", 101);
        addDefault("glasspane", 102);
        addDefault("glass_pane", 102);
        addDefault("melon", 103);
        addDefault("vines", 106);
        addDefault("fencegate", 107);
        addDefault("fence_gate", 107);
        addDefault("brickstair", 108);
        addDefault("brick_stair", 108);
        addDefault("stonebrickstair", 109);
        addDefault("stonebrick_stair", 109);
        addDefault("mycelium", 110);
        addDefault("lillypad", 111);
        addDefault("lilly_pad", 111);
        addDefault("netherbrick", 112);
        addDefault("nether_brick", 112);
        addDefault("netherfence", 113);
        addDefault("nether_fence", 113);
        addDefault("netherstair", 114);
        addDefault("nether_stair", 114);
        addDefault("enchant", 116);
        addDefault("enchanting", 116);
        addDefault("enchanttable", 116);
        addDefault("enchant_table", 116);
        addDefault("enchantingtable", 116);
        addDefault("enchanting_table", 116);
        addDefault("endstone", 121);
        addDefault("end_stone", 121);
        addDefault("dragonegg", 122);
        addDefault("dragon_egg", 122);
        addDefault("redlantern", 123);
        addDefault("red_lantern", 123);
        addDefault("redstonelantern", 123);
        addDefault("redstone_lantern", 123);
    }

}
