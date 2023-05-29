package dev.selena.items;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ItemMapperBuilder {

    private String entityName = null;
    private String itemType = null;
    private String itemColor = null;
    private String title = null;
    private String skullTexture = null;
    private String skullUuid = null;
    private String skullOwner = null;
    private List<String> loreLines = null;
    private final Map<String, String> nbtStrings = new TreeMap<>();
    private final Map<String, Boolean> nbtBooleans = new TreeMap<>();
    private final Map<String, Integer> nbtInts = new TreeMap<>();
    private final Map<String, Float> nbtFloats = new TreeMap<>();
    private boolean glowing = false;
    private Map<String, Integer> enchants = null;
    private boolean usable = true;
    private int amount = 1;
    private boolean commandItem = false;
    private String[] commands;


    /**
     * Used in hordes to set the name of the horde it spawns.
     * Can be used for any other reason honestly
     * @param EntityName Horde name
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder entityDetails(String EntityName) {
        entityName = EntityName;
        return this;
    }

    /**
     * Set the item type
     * @param itemType Custom display name
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder itemStack(String itemType) {
        this.itemType = itemType;
        return this;
    }

    /**
     * Sets the item name
     * @param itemName Name of the item
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder itemName(String itemName) {
        this.title = itemName;
        return this;
    }

    /***
     * Used for setting the lore of the item
     * @param lines String list of lore lines
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder loreLines(List<String> lines) {
        loreLines = lines;
        return this;
    }

    /***
     * Used for setting the lore of the item
     * @param lines String[] of lore lines
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder loreLines(String ... lines) {
        loreLines = Arrays.asList(lines);
        return this;
    }

    /***
     * Adds item glint
     * @param glowing set to true to add glint
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder glowing(boolean glowing) {
        this.glowing = glowing;
        return this;
    }

    /***
     * Adds enchantments to the item
     * @param enchants String: Enchant name, Integer: Enchant level
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder enchants(Map<String, Integer> enchants) {
        this.enchants = enchants;
        return this;
    }

    /***
     * Stops the item from being placed
     * @param usable set to false to disable item placement
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder usable(boolean usable) {
        this.usable = usable;
        return this;
    }

    /***
     * Enables command execution upon right click
     * @param doesCommand enable commands
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder commandItem(boolean doesCommand) {
        this.commandItem = doesCommand;
        return this;
    }

    /***
     * Adds a command to be executed on right click
     * @param commands String[] of commands
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder commands(String ... commands) {
        this.commands = commands;
        return this;
    }

    /***
     * Sets the item stack size
     * @param amount stack size
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    /***
     * Used for setting armor color
     * @param itemColor hex color code for the item
     * @return ItemMapperBuilder
     */
    public ItemMapperBuilder color(String itemColor) {
        this.itemColor = itemColor;
        return this;
    }


    public ItemMapperBuilder skullTexture(String texture) {
        this.skullTexture = texture;
        return this;
    }

    public ItemMapperBuilder skullUuid(String uuid) {
        this.skullUuid = uuid;
        return this;
    }

    public ItemMapperBuilder skullOwner(String owner) {
        this.skullOwner = owner;
        return this;
    }

    public ItemMapperBuilder addNBTString(String name, String value) {
        this.nbtStrings.put(name, value);
        return this;
    }

    public ItemMapperBuilder addNBTBoolean(String name, boolean value) {
        this.nbtBooleans.put(name, value);
        return this;
    }

    public ItemMapperBuilder addNBTInt(String name, int value) {
        this.nbtInts.put(name, value);
        return this;
    }

    public ItemMapperBuilder addNBTFloat(String name, float value) {
        this.nbtFloats.put(name, value);
        return this;
    }

    /***
     * Builds the item into the ItemMapper class
     * @return ItemMapper
     */
    public ItemMapper build() {
        return new ItemMapper(this.entityName, this.itemType, this.itemColor, this.title, this.loreLines, this.glowing, this.enchants, this.usable, this.amount, this.commandItem, this.commands, this.skullTexture, this.skullUuid, this.skullOwner, this.nbtStrings, this.nbtBooleans, this.nbtInts, this.nbtFloats);
    }

}
