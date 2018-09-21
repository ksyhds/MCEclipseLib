package com.Moon_eclipse.EclipseLib.ItemCreater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.Moon_eclipse.EclipseLib.LibMain;

public class ItemCreator 
{
	
	// 아이템 크리에이터가 해야할 일
	
	// 주어진 항목을 통해 아이템을 생성
	
	public static ItemStack Create_ItemStack(String Material_Name, int Amount, short Damage, String Display_Name, List<String> Lore, String ColorHex, String SkullURL, List<String> Enchants)
	{
		
		Material Target_Material = Material.getMaterial(Material_Name);
		
		ItemStack Target_Item = new ItemStack(Target_Material, Amount);
		
		Target_Item.setDurability(Damage);	
		
		ItemMeta Target_Item_Meta = Target_Item.getItemMeta();
		
		Target_Item_Meta.setDisplayName(Display_Name);
		
		Lore = setLoreColor(Lore);
		
		Target_Item_Meta.setLore(Lore);
		
		Target_Item.setItemMeta(Target_Item_Meta);
		
		if(Is_This_LetherArmor(Target_Material))
		{
			LeatherArmorMeta Lether_Item_Meta = (LeatherArmorMeta)Target_Item_Meta;
			
			Lether_Item_Meta.setColor(Color.fromRGB(Integer.parseInt(ColorHex, 16)));
			
			Target_Item.setItemMeta(Target_Item_Meta);
		}
		if(Is_This_Skull(Target_Material))
		{
			Target_Item = getSkullItemStack(SkullURL, Damage, Amount, Display_Name, Lore);
		}
		
	    if (!Enchants.isEmpty())
		{
	    	for (String enchant : Enchants)
			{
				//'16: 1'
				String enchantname = enchant.split(": ")[0];
				
				int level = Integer.parseInt(enchant.split(": ")[1]);
							
				Enchantment enchantment = EnchantmentWrapper.getByName(enchantname);
				
				Target_Item.addUnsafeEnchantment(enchantment, level);
			}
		}
	    
	    
	    Target_Item = LibMain.hideFlags_Unbreak(Target_Item);
	    
		return Target_Item;
	}
	public static List<String> setLoreColor(List<String> LoreList)
	{
		List<String> new_lore = new ArrayList<String>();
		
		for(String lore : LoreList)
		{
			new_lore.add(lore.replace("&", "§"));
		}
		
		return new_lore;
	}
	
	public static boolean Is_This_LetherArmor(Material Target)
	{
		if(Target.equals(Material.LEATHER_BOOTS) || Target.equals(Material.LEATHER_CHESTPLATE) || 	Target.equals(Material.LEATHER_HELMET) || Target.equals(Material.LEATHER_LEGGINGS))
		{
			return true;
		}
		return false;
	}
	public static boolean Is_This_Skull(Material Target)
	{
		if(Target.equals(Material.SKELETON_WALL_SKULL))
		{
			return true;
		}
		return false;
	}
	public static ItemStack getSkullItemStack(String SkullURL, Short Damage, int Amount, String Display_Name, List<String> Lore)
	{
		SkullMeta Skull_Meta;
		
		ItemStack new_itemstack = LibMain.getSkull(SkullURL, Display_Name);
		
		new_itemstack.setDurability(Damage);
		
		new_itemstack.setAmount(Amount);
		
		Skull_Meta = (SkullMeta)new_itemstack.getItemMeta();
		
		Skull_Meta.setLore(Lore);
		
		Skull_Meta.setDisplayName(Display_Name);
		
		new_itemstack.setItemMeta(Skull_Meta);
		
		return new_itemstack;
	}
	public static ItemStack getPlaceHoldered_ItemStack(ItemStack Target_Item, String PlaceHolder, String NextString)
	{
		ItemMeta Target_Meta = Target_Item.getItemMeta();
		
		List<String> Lore = Target_Meta.getLore();
		
		Lore = LibMain.ChangeString(PlaceHolder, NextString, Lore);
		
		Target_Meta.setLore(Lore);
		
		Target_Item.setItemMeta(Target_Meta);
		
		return Target_Item;

		
	}
}
