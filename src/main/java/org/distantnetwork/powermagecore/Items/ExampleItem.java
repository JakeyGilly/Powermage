package org.distantnetwork.powermagecore.Items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.distantnetwork.powermagecore.utils.Enums.Rarity;
import org.distantnetwork.powermagecore.utils.ShopItem;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class ExampleItem extends ShopItem {
    public ExampleItem() {
        super(
                Material.OAK_LOG,
                1,
                "&7&oExample Item",
                Collections.singletonList("&7&oThis is an example item."),
                Arrays.asList(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE),
                new HashMap<>(),
                0,
                true,
                Rarity.CORE,
                1,
                Collections.singletonList("A item forged in the mouth of a dwarven star"),
                true
        );
    }
}
