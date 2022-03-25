package org.distantnetwork.powermagecore.builders.ConfigBuilders;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.Item;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class ConfigItemBuilder {
    private final FileConfiguration config;

    // Constructors
    public ConfigItemBuilder(FileConfiguration config, ItemStack item) {
        this.config = config;
        Map<String, Object> itemInfo = Item.getItemStackInfo(item);
        for (Map.Entry<String, Object> entry : itemInfo.entrySet()) {
            this.config.set(entry.getKey(), entry.getValue());
        }
    }
    public ConfigItemBuilder(FileConfiguration config, @NotNull Material material) {
        this.config = config;
        this.config.set("material", material.getKey().getKey());
        this.config.set("amount", 1);
    }
    public ConfigItemBuilder(FileConfiguration config, @NotNull Material material, int amount) {
        this.config = config;
        this.config.set("material", material.getKey().getKey());
        this.config.set("amount", amount);
    }
    public ConfigItemBuilder(FileConfiguration config, @NotNull Material material, int amount, String name) {
        this.config = config;
        this.config.set("name", name);
        this.config.set("material", material.getKey().getKey());
        this.config.set("amount", amount);
    }
    public ConfigItemBuilder(FileConfiguration config, @NotNull Material material, int amount, String name, List<String> lore) {
        this.config = config;
        this.config.set("name", name);
        this.config.set("material", material.getKey().getKey());
        this.config.set("amount", amount);
        this.config.set("lore", lore);
    }
    public ConfigItemBuilder(FileConfiguration config, @NotNull Material material, int amount, String name, String... lore) {
        this.config = config;
        this.config.set("name", name);
        this.config.set("material", material.getKey().getKey());
        this.config.set("amount", amount);
        this.config.set("lore", lore);
    }

    //Methods
    public void save() {
        ConfigurationManager.save(this.config.getCurrentPath());
    }
}
