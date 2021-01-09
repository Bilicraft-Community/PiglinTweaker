package com.mcsunnyside.piglintweaker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PiglinTweaker extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPiglinDead(EntityDeathEvent e){
        if(e.getEntityType() != EntityType.PIGLIN && e.getEntityType() != EntityType.ZOMBIFIED_PIGLIN){
            return;
        }
        if(e.getEntity().getLastDamageCause() == null){
            return;
        }
        EntityDamageEvent.DamageCause cause = e.getEntity().getLastDamageCause().getCause();
        if(cause != EntityDamageEvent.DamageCause.CRAMMING && cause != EntityDamageEvent.DamageCause.FALL){
            return;
        }
        e.getDrops().removeIf(item -> item.getType() == Material.GOLDEN_SWORD);
        e.getDrops().removeIf(item -> item.getType() == Material.GOLDEN_AXE);
        e.getDrops().removeIf(item -> item.getType() == Material.CROSSBOW);
    }
}
