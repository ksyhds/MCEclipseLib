package com.Moon_eclipse.EclipseLib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagList;

public class LibMain extends JavaPlugin{
	
	static Configuration c;
	private static LibMain instance;
	
	public void onEnable()
	{
		instance = this;
		this.saveDefaultConfig();
		c = this.getConfig();		
	}
	public void onDisable(){}
	
	public static boolean hasString(List<String> lore, String name)
	{
		boolean b = false;
		for(String search : lore)
		{
			if(search.contains(name))
			{
				b = true;
			}
		}
		return b;
	}
	public static void takeitem( Player p, Inventory inv, int count, String name)
	{
		// ./팔기 이름 코드 메타 디스플레이이름 가격 수량
		// ./ -   0  1  2      3      4   5
		int before = 0;
		int tofor2 = count;
		for(int i = 0 ; i<54 ; i++)
		{
			boolean b = false;
			ItemStack is = inv.getItem(i);
			if(is != null && is.getAmount() > 0 && is.getItemMeta().hasDisplayName() && is.getItemMeta().getDisplayName().equals(name))
			{
				int amount = is.getAmount();
				for(int g = 0 + before ; g < tofor2; g++ )
				{
					if(amount > 0)
					{
						if(amount == 1)
						{
							inv.clear(i);
							before = g +1;
							b = true;
							break;
						}
					else
					{
						amount -= 1;
						is.setAmount(amount);
						b = false;
					}					
					}
				}
				if(b == false)
				{
					break;
				}
				if(b == true)
				{
					//Bukkit.broadcastMessage("dist < tofor");
				}
			}
		}
	}
	public static boolean hasitem( Player p, Inventory inv, int code, byte meta, int count, String name)
    {
    	boolean hasitem = false;
    	String Disname = name;
    	String Disname2 = Disname.replace("&", "§");
    	String Disname3 = Disname2.replace("_", " ");
    	int total = 0;
    	
    	for(int i = 0 ; i<54 ; i++)
    	{
    		try
    		{
    			if(
    					!(inv.getItem(i).equals(null))
    					&& inv.getItem(i).getTypeId() == code 
    					&& inv.getItem(i).getData().getData() == meta 
    					&& inv.getItem(i).getAmount() > 0
    					&& inv.getItem(i).getItemMeta().hasDisplayName()
    					&& inv.getItem(i).getItemMeta().getDisplayName().equals(Disname3)
    			  )
    				{
    					total += inv.getItem(i).getAmount();
    				}
    		}catch(Exception e){}
    		
    	}
    	if(total >= count)
    	{
    		hasitem = true;
    	}
    	else
    	{
    		p.sendMessage("§b[MCMANY]§r " + Disname3 + " §e이(가) 부족합니다.");
    	}
    	return hasitem;
    }
    public static boolean hasitem2( Player p, Inventory inv, int code, int count)
    {
    	boolean hasitem = false;
    	int total = 0;
    	
    	for(int i = 0 ; i<54 ; i++)
    	{
    		try
    		{
    			if(
    					!(inv.getItem(i).equals(null))
    					&& inv.getItem(i).getTypeId() == code 
    					&& inv.getItem(i).getAmount() > 0
    			  )
    				{
    					total += inv.getItem(i).getAmount();
    				}
    		}catch(Exception e){}
    		
    	}
    	if(total >= count)
    	{
    		hasitem = true;
    	}
    	else
    	{
    		p.sendMessage("§b[MCMANY]§4 " + code + "§4" + " §e이(가) 부족합니다.");
    	}
    	return hasitem;
    }public static boolean hasitem3( Player p, Inventory inv, int code, int count, String name)
    {
    	boolean hasitem = false;
    	String Disname = name;
    	String Disname2 = Disname.replace("&", "§");
    	String Disname3 = Disname2.replace("_", " ");
    	int total = 0;
    	
    	for(int i = 0 ; i<54 ; i++)
    	{
    		try
    		{
    			if(
    					!(inv.getItem(i).equals(null))
    					&& inv.getItem(i).getTypeId() == code 
    					&& inv.getItem(i).getAmount() > 0
    					&& inv.getItem(i).getItemMeta().hasDisplayName()
    					&& inv.getItem(i).getItemMeta().getDisplayName().equals(Disname3)
    			  )
    				{
    					total += inv.getItem(i).getAmount();
    				}
    		}catch(Exception e){}
    		
    	}
    	if(total >= count)
    	{
    		hasitem = true;
    	}
    	else
    	{
    		p.sendMessage("§b[MCMANY]§4 " + Disname3 + " §e이(가) 부족합니다.");
    	}
    	return hasitem;
    }
    public static ItemStack createItem(int typeId, int damage, int amount, String name, List<String> lore, String color, List<String> enchants)
	{
    	ItemStack i = new ItemStack(typeId);
		i.setDurability((short) damage);
		i.setAmount(amount);
		ItemMeta im = i.getItemMeta();
		String ColorHex = color;
		try
		{
			if(typeId == 298 || typeId == 299 || typeId == 300 || typeId == 301)
			{
				LeatherArmorMeta im2 = (LeatherArmorMeta) im;
				im2.setColor(Color.fromRGB(Integer.parseInt(ColorHex, 16)));
			}
		}catch(Exception e){}
		im.setDisplayName(name);
		im.setLore(lore);
		i.setItemMeta(im);
		Random rnd = new Random();
		if(enchants != null && !(enchants.isEmpty()) && !(enchants.toString().equals("")))
		{
			for(String enchant : enchants)
			{
				//'16: 1'
				int enchantname = Integer.parseInt(enchant.substring(0, enchant.length() - 3));
				int level = Integer.parseInt(enchant.substring(enchant.length() - 1));
				i.addUnsafeEnchantment(Enchantment.getById(enchantname), level);
			}
		}
		i = removeAttributes(i);
		return i;
	}
	public static void AddItem(Player p, Inventory inv, int code, int meta,int amount, String Disname, String lore)
	{
		ItemStack item = new ItemStack(code, amount,(short) 0, (byte) meta);
		ItemMeta im = item.getItemMeta();
		List<String> loreSet = new ArrayList<String>();
		im.setDisplayName(Disname);
		loreSet.add(lore);
		im.setLore(loreSet);
		item.setItemMeta(im);
		inv.addItem(item);
	}
	public static ItemStack removeAttributes(ItemStack i)
	{
        if(i == null) {
            return i;
        }
        if(i.getType() == Material.BOOK_AND_QUILL) {
            return i;
        }
        ItemStack item = i.clone();
        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag;
        if (!nmsStack.hasTag()){
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        else {
            tag = nmsStack.getTag();
        }
        NBTTagList am = new NBTTagList();
        tag.set("AttributeModifiers", am);
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }
	public static ItemStack hideFlags(ItemStack item)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag;
         if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        } else {
            tag = nmsStack.getTag();
        }
        tag.set("HideFlags", new NBTTagInt(63));
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }
	public static ItemStack hideFlags_Unbreak(ItemStack item)
    {
		item = removeAttributes(item);
		if(item.getType() == Material.SKULL_ITEM) {
            return item;
        }
		
		ItemMeta im = item.getItemMeta();
		im.setUnbreakable(true);
		item.setItemMeta(im);
        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag;
         if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        } else {
            tag = nmsStack.getTag();
        }
        tag.set("HideFlags", new NBTTagInt(63));
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }
	public static List<String> ChangeString(String PlaceHolder, String NextString, List<String> lore)
	{
		ArrayList<String> newlore = new ArrayList<String>();
		for(int i = 0; i < lore.size() ; i++)
		{
			if(lore.get(i).contains(PlaceHolder))
			{
				newlore.add(lore.get(i).replaceAll(PlaceHolder, NextString));
			}
			else
			{
				newlore.add(lore.get(i));
			}
		}
		return newlore;
	}
	public static ItemStack ReCreateItem(ItemStack is)
	{
		int type = is.getTypeId();
		int amount = is.getAmount();
		short damage = is.getDurability();
		ItemMeta im = is.getItemMeta();
		String Disname = im.getDisplayName();
		List<String> Lore = im.getLore();
		Map<Enchantment, Integer> Enchant = im.getEnchants();
		
		
		ItemStack ReCreatedItem = new ItemStack(type, amount, damage);
		ItemMeta ReCreatedItemMeta = ReCreatedItem.getItemMeta();
		ReCreatedItemMeta.setDisplayName(Disname);
		ReCreatedItemMeta.setLore(Lore);
		for(Enchantment enchant : Enchant.keySet())
		{
			ReCreatedItem.addEnchantment(enchant, Enchant.get(enchant));
		}
		
		ReCreatedItem.setItemMeta(ReCreatedItemMeta);

		return ReCreatedItem;
		
		
	}
	public static boolean IsWithin(Location BlockLocation, String position1, String position2)
	{
		boolean b = false;
		int xp,yp,zp,x1,y1,z1,x2,y2,z2;
		xp = (int) BlockLocation.getX();
		yp = (int) BlockLocation.getY();
		zp = (int) BlockLocation.getZ();
		String[] split1 = position1.split(",");
		String[] split2 = position2.split(",");
		x1 = Integer.parseInt(split1[0]);
		y1 = Integer.parseInt(split1[1]);
		z1 = Integer.parseInt(split1[2]);
		x2 = Integer.parseInt(split2[0]);
		y2 = Integer.parseInt(split2[1]);
		z2 = Integer.parseInt(split2[2]);
		
		if((xp >= x1 && xp <= x2) || (xp >= x2 && xp <= x1))
		{
			if((yp >= y1 && yp <= y2) || (yp >= y2 && yp <= y1))
			{
				if((zp >= z1 && zp <= z2) || (zp >= z2 && zp <= z1))
				{
					b = true;
				}
			}
		}
		
		return b;
	}
	public static double double_round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
	public static float float_round (float value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (float) Math.round(value * scale) / scale;
	}
	public static double getRangeRandomDouble(double min, double max)
	{
		Random ran = new Random();
		double randomValue = min + (max - min) * ran.nextDouble();
		return randomValue;
	}
	public static boolean InventoryIsFull(Player p)
	{
		boolean re = true;
		
		Inventory inv = p.getInventory();
		
		for(int i = 0; i < inv.getSize() ; i++)
		{
			ItemStack item = inv.getItem(i);
			if(item == null || item.getTypeId() == 0)
			{
				re = false;
				return re;
			}
		}
		
		return re;
	}
	public static float getPlayerSwingCooltime(Player player) 
	{
		float re = 0.0f;
		
		re = (float) ((CraftPlayer)player).getHandle().O;
		
		return re;
	}
	public static ItemStack getSkull(String url, String ItemName) 
	{
		ItemStack skull= new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        if (url == null || url.isEmpty())
            return skull;

        ItemMeta skullMeta = skull.getItemMeta();
        String Server_UUID_FOR_HEAD = get_UUID_From_config(ItemName);
        GameProfile profile = new GameProfile(UUID.fromString(Server_UUID_FOR_HEAD), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;

        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        profileField.setAccessible(true);

        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        skull.setItemMeta(skullMeta);
        return skull;
	}
	public static String get_UUID_From_config(String ItemName)
	{
		if(!c.contains("config." + ItemName))
		{
			String uuid = UUID.randomUUID().toString();
			
			c.set("config." + ItemName, uuid);
			
			getInstance().saveConfig();
			
			return uuid;
		}
		else
		{
			return c.getString("config." + ItemName);
		}
	}
	public static LibMain getInstance()
	{
		return instance;
	}	
	public static int getNumberofList(List<String> list, String string)
	{
		int re = 0;
		for(String str : list)
		{
			if(str.equals(string))
			{
				return re;
			}
			re++;
		}
		
		return -1;
	}
}
