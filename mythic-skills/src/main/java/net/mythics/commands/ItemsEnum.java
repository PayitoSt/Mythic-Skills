package net.mythics.commands;

import org.bukkit.inventory.ItemStack;

import net.mythics.utils.Items;

public enum ItemsEnum {

    EXP_ORB_SMALL(Items.expOrbSmall()),
    EXP_ORB_MEDIUM(Items.expOrbMedium()),
    EXP_ORB_LARGE(Items.expOrbLarge()),
    EXP_ORB_MEGA(Items.expOrbMega());


    private ItemStack item;

    ItemsEnum(ItemStack item){
        this.item = item;
    }

    public ItemStack getItem(){
        return item;
    }
    
}
