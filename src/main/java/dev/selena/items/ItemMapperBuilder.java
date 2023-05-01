package dev.selena.items;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemMapperBuilder {

    private String entityName = null;
    private String itemType = null;
    private String itemColor = null;
    private String title = null;
    private List<String> loreLines = null;
    private boolean glowing = false;
    private Map<String, Integer> enchants = null;
    private boolean usable = true;
    private int amount = 1;
    private boolean commandItem = false;
    private String[] commands;

    public ItemMapperBuilder entityDetails(String EntityName) {
        entityName = EntityName;
        return this;
    }

    public ItemMapperBuilder itemStack(String itemType) {
        this.itemType = itemType;
        return this;
    }
    public ItemMapperBuilder itemName(String itemName) {
        this.title = itemName;
        return this;
    }

    public ItemMapperBuilder loreLines(List<String> lines) {
        loreLines = lines;
        return this;
    }
    public ItemMapperBuilder loreLines(String ... lines) {
        loreLines = Arrays.asList(lines);
        return this;
    }

    public ItemMapperBuilder glowing(boolean glowing) {
        this.glowing = glowing;
        return this;
    }

    public ItemMapperBuilder enchants(Map<String, Integer> enchants) {
        this.enchants = enchants;
        return this;
    }
    public ItemMapperBuilder usable(boolean usable) {
        this.usable = usable;
        return this;
    }
    public ItemMapperBuilder commandItem(boolean doesCommand) {
        this.commandItem = doesCommand;
        return this;
    }
    public ItemMapperBuilder commands(String ... commands) {
        this.commands = commands;
        return this;
    }

    public ItemMapperBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemMapperBuilder color(String itemColor) {
        this.itemColor = itemColor;
        return this;
    }

    public ItemMapper build() {
        return new ItemMapper(entityName, itemType, itemColor, title, loreLines, glowing, enchants, usable, amount, commandItem, commands);
    }

}
