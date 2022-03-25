package org.distantnetwork.powermagecore.builders.ConfigBuilders;

import org.bukkit.inventory.Inventory;

import java.io.File;

import static org.distantnetwork.powermagecore.utils.Config.ConfigItem.*;
import static org.distantnetwork.powermagecore.PowermageCore.getInstance;

public class ConfigInventoryBuilder {
    private File folder;
    private Inventory inventory;

    public ConfigInventoryBuilder(File folder, Inventory inventory) throws IllegalArgumentException {
        this.folder = folder;
        this.inventory = inventory;
        if (inventory == null) throw new IllegalArgumentException("Inventory cannot be null");
        if (folder == null) throw new IllegalArgumentException("Folder cannot be null");
    }

    public void save() {
        folder = getFileFolder(folder);
        if (folder == null) throw new IllegalArgumentException("Folder is not valid");
        for (int i = 0; i < inventory.getSize(); i++) {
            File file = getFileFile(new File(folder, "slot" + i + ".yml"));
            if (file == null) {
                getInstance().getLogger().info(String.format("ERROR: Error in file %s in folder %s - Skipping file...", i, folder.getName()));
                continue;
            }
            new ConfigItemBuilder(getConfig(file), inventory.getItem(i)).save();
        }
    }
}
