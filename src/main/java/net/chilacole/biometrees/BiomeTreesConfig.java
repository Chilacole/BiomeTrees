
package net.chilacole.biometrees;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import turniplabs.halplibe.util.ConfigHandler;

public class BiomeTreesConfig {

	private static final Properties DEFAULTS = new Properties();
	private static final ConfigHandler CONFIG;
	private static final Properties VALUES = new Properties();

	// This array determines the order of settings in biometrees.cfg.
	private static final String[] CONFIG_ORDER = {
		// General
		"biomeAwareGrowth",

		// Swamp
		"swampOakGrowth",

		// Rainforest
		"rainforestOakGrowth",
		"rainforestGiantOak",
		"rainforestFancyMossyOak",
		"rainforestFancyRegularOak",
		"rainforestRegularOak",

		// Birch forest
		"birchForestGrowth",
		"birchForestTall",
		"birchForestRegular",

		// Caatinga
		"caatingaGrowth",
		"caatingaTallThorn",
		"caatingaShrub",

		// Caatinga plains
		"caatingaPlainsGrowth",
		"caatingaPlainsShortThorn",
		"caatingaPlainsShrub"
	};

	static {
		// General
		DEFAULTS.setProperty("biomeAwareGrowth", "true");

		// Swamp
		DEFAULTS.setProperty("swampOakGrowth", "true");

		// Rainforest
		DEFAULTS.setProperty("rainforestOakGrowth", "true");
		DEFAULTS.setProperty("rainforestGiantOak", "true");
		DEFAULTS.setProperty("rainforestFancyMossyOak", "true");
		DEFAULTS.setProperty("rainforestFancyRegularOak", "true");
		DEFAULTS.setProperty("rainforestRegularOak", "true");

		// Birch forest
		DEFAULTS.setProperty("birchForestGrowth", "true");
		DEFAULTS.setProperty("birchForestTall", "true");
		DEFAULTS.setProperty("birchForestRegular", "true");

		// Caatinga
		DEFAULTS.setProperty("caatingaGrowth", "true");
		DEFAULTS.setProperty("caatingaTallThorn", "true");
		DEFAULTS.setProperty("caatingaShrub", "true");

		// Caatinga plains
		DEFAULTS.setProperty("caatingaPlainsGrowth", "true");
		DEFAULTS.setProperty("caatingaPlainsShortThorn", "true");
		DEFAULTS.setProperty("caatingaPlainsShrub", "true");

		CONFIG = new ConfigHandler("biometrees", DEFAULTS);

		VALUES.putAll(DEFAULTS);

		try (InputStream input = new FileInputStream(CONFIG.getFilePath())) {
			VALUES.load(input);
		} catch (IOException e) {
			// If the config doesn't exist yet, use the defaults.
		}

		// Rewrite the file in the desired order, preserving loaded values.
		saveConfig();
	}

	// --- Getters ---

	public static boolean isBiomeAwareGrowthEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("biomeAwareGrowth", "true"));
	}

	// Oak
	public static boolean isSwampOakGrowthEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("swampOakGrowth", "true"));
	}

	public static boolean isRainforestOakGrowthEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("rainforestOakGrowth", "true"));
	}

	public static boolean isRainforestGiantOakEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("rainforestGiantOak", "true"));
	}

	public static boolean isRainforestFancyMossyOakEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("rainforestFancyMossyOak", "true"));
	}

	public static boolean isRainforestFancyRegularOakEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("rainforestFancyRegularOak", "true"));
	}

	public static boolean isRainforestRegularOakEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("rainforestRegularOak", "true"));
	}

	// Birch
	public static boolean isBirchForestGrowthEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("birchForestGrowth", "true"));
	}

	public static boolean isBirchForestTallEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("birchForestTall", "true"));
	}

	public static boolean isBirchForestRegularEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("birchForestRegular", "true"));
	}

	// Thorn
	public static boolean isCaatingaGrowthEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("caatingaGrowth", "true"));
	}

	public static boolean isCaatingaTallThornEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("caatingaTallThorn", "true"));
	}

	public static boolean isCaatingaShrubEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("caatingaShrub", "true"));
	}

	public static boolean isCaatingaPlainsGrowthEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("caatingaPlainsGrowth", "true"));
	}

	public static boolean isCaatingaPlainsShortThornEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("caatingaPlainsShortThorn", "true"));
	}

	public static boolean isCaatingaPlainsShrubEnabled() {
		return Boolean.parseBoolean(VALUES.getProperty("caatingaPlainsShrub", "true"));
	}

	// --- Setters ---

	public static void setBiomeAwareGrowth(boolean enabled) {
		setAndSave("biomeAwareGrowth", enabled);
	}

	// Oak
	public static void setSwampOakGrowth(boolean enabled) {
		setAndSave("swampOakGrowth", enabled);
	}

	public static void setRainforestOakGrowth(boolean enabled) {
		setAndSave("rainforestOakGrowth", enabled);
	}

	public static void setRainforestGiantOak(boolean enabled) {
		setAndSave("rainforestGiantOak", enabled);
	}

	public static void setRainforestFancyMossyOak(boolean enabled) {
		setAndSave("rainforestFancyMossyOak", enabled);
	}

	public static void setRainforestFancyRegularOak(boolean enabled) {
		setAndSave("rainforestFancyRegularOak", enabled);
	}

	public static void setRainforestRegularOak(boolean enabled) {
		setAndSave("rainforestRegularOak", enabled);
	}

	// Birch
	public static void setBirchForestGrowth(boolean enabled) {
		setAndSave("birchForestGrowth", enabled);
	}

	public static void setBirchForestTall(boolean enabled) {
		setAndSave("birchForestTall", enabled);
	}

	public static void setBirchForestRegular(boolean enabled) {
		setAndSave("birchForestRegular", enabled);
	}

	// Thorn
	public static void setCaatingaGrowth(boolean enabled) {
		setAndSave("caatingaGrowth", enabled);
	}

	public static void setCaatingaTallThorn(boolean enabled) {
		setAndSave("caatingaTallThorn", enabled);
	}

	public static void setCaatingaShrub(boolean enabled) {
		setAndSave("caatingaShrub", enabled);
	}

	public static void setCaatingaPlainsGrowth(boolean enabled) {
		setAndSave("caatingaPlainsGrowth", enabled);
	}

	public static void setCaatingaPlainsShortThorn(boolean enabled) {
		setAndSave("caatingaPlainsShortThorn", enabled);
	}

	public static void setCaatingaPlainsShrub(boolean enabled) {
		setAndSave("caatingaPlainsShrub", enabled);
	}

	// --- Save Helpers ---

	private static void setAndSave(String key, boolean value) {
		VALUES.setProperty(key, Boolean.toString(value));
		saveConfig();
	}


	private static void saveConfig() {
		String[][] descriptions = {
			{
				"biomeAwareGrowth",
				"Master toggle for custom biome-aware sapling growth."
			},
			{
				"swampOakGrowth",
				"Enables custom oak growth behavior in Swamp biomes."
			},
			{
				"rainforestOakGrowth",
				"Enables custom oak growth behavior in Rainforest biomes."
			},
			{
				"rainforestGiantOak",
				"Allows the giant round oak variant to generate in Rainforests."
			},
			{
				"rainforestFancyMossyOak",
				"Allows the fancy mossy oak variant to generate in Rainforests."
			},
			{
				"rainforestFancyRegularOak",
				"Allows the fancy regular oak variant to generate in Rainforests."
			},
			{
				"rainforestRegularOak",
				"Allows the regular oak variant to generate in Rainforests."
			},
			{
				"birchForestGrowth",
				"Enables custom tree growth behavior in Birch Forest biomes."
			},
			{
				"birchForestTall",
				"Allows the tall birch tree variant to generate in Birch Forests."
			},
			{
				"birchForestRegular",
				"Allows the regular birch tree variant to generate in Birch Forests."
			},
			{
				"caatingaGrowth",
				"Enables custom tree growth behavior in Caatinga biomes."
			},
			{
				"caatingaTallThorn",
				"Allows the tall thorn tree variant to generate in Caatinga."
			},
			{
				"caatingaShrub",
				"Allows the shrub variant to generate in Caatinga."
			},
			{
				"caatingaPlainsGrowth",
				"Enables custom tree growth behavior in Caatinga Plains."
			},
			{
				"caatingaPlainsShortThorn",
				"Allows the short thorn tree variant to generate in Caatinga Plains."
			},
			{
				"caatingaPlainsShrub",
				"Allows the shrub variant to generate in Caatinga Plains."
			}
		};

		try (BufferedWriter writer = new BufferedWriter(
			new OutputStreamWriter(
				new FileOutputStream(CONFIG.getFilePath()),
				StandardCharsets.UTF_8))) {

			writer.write("# Biome Trees configuration");
			writer.newLine();
			writer.write("# true = enabled, false = disabled");
			writer.newLine();
			writer.newLine();

			for (String[] description : descriptions) {
				String key = description[0];
				String comment = description[1];
				String value = VALUES.getProperty(key);

				if (value != null) {
					writer.write("# " + comment);
					writer.newLine();
					writer.write(key + "=" + value);
					writer.newLine();
					writer.newLine();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

