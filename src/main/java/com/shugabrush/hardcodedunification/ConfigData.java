package com.shugabrush.hardcodedunification;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.ConfigHolder;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id = HardcodedUnification.MOD_ID)
public class ConfigData
{

    public static ConfigData INSTANCE;
    public static ConfigHolder< ConfigData> CONFIG_HOLDER;

    public static void init()
    {
        CONFIG_HOLDER = Configuration.registerConfig(ConfigData.class, ConfigFormats.yaml());
        INSTANCE = CONFIG_HOLDER.getConfigInstance();
    }

    @Configurable
    public boolean adAstraUnificationEnabled = true;
    @Configurable
    public boolean avaritiaUnificationEnabled = true;
    @Configurable
    @Configurable.Comment("Neutronium material tag for Avaritia's Neutron Collectors.\n" +
            "It's your responsibility to make sure your item actually has this tag and that almostunified recognizes it.")
    public String neutroniumMaterial = "neutronium";
    @Configurable
    public boolean gtceuUnificationEnabled = true;
    @Configurable
    public boolean industrialForegoingUnificationEnabled = true;
    @Configurable
    public boolean mobGrindingUtilsUnificationEnabled = true;
    @Configurable
    public boolean powahUnificationEnabled = true;
    @Configurable
    public boolean stargateJourneyUnificationEnabled = true;
    @Configurable
    public boolean sophisticatedCoreUnificationEnabled = true;
    @Configurable
    public boolean utilitixUnificationEnabled = true;
}
