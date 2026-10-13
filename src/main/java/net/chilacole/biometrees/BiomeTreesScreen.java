package net.chilacole.biometrees;

import net.minecraft.client.gui.ButtonElement;
import net.minecraft.client.gui.Screen;

public class BiomeTreesScreen extends Screen {

	// --- Button ID Constants ---
	private static final int BTN_MASTER = 0;
	private static final int BTN_DONE = 10;

	// Oak IDs
	private static final int BTN_OAK_SWAMP = 1;
	private static final int BTN_OAK_RAINFOREST = 2;
	private static final int BTN_OAK_GIANT = 4;
	private static final int BTN_OAK_FANCY_MOSSY = 5;
	private static final int BTN_OAK_FANCY_REGULAR = 6;
	private static final int BTN_OAK_REGULAR = 7;

	// Birch IDs
	private static final int BTN_BIRCH_FOREST = 11;
	private static final int BTN_BIRCH_TALL = 12;
	private static final int BTN_BIRCH_REGULAR = 13;

	// Thorn IDs
	private static final int BTN_THORN_CAATINGA = 21;
	private static final int BTN_THORN_TALL = 22;
	private static final int BTN_THORN_SHRUB = 23;
	private static final int BTN_THORN_CAATINGA_PLAINS = 24;
	private static final int BTN_THORN_PLAINS_SHORT = 25;
	private static final int BTN_THORN_PLAINS_SHRUB = 26;

	// --- Button Elements ---
	private ButtonElement masterButton;

	// Left Column
	private ButtonElement swampOakButton;
	private ButtonElement rainforestOakButton;
	private ButtonElement giantOakButton;
	private ButtonElement fancyMossyOakButton;
	private ButtonElement fancyRegularOakButton;
	private ButtonElement regularOakButton;

	private ButtonElement birchForestButton;
	private ButtonElement birchTallButton;
	private ButtonElement birchRegularButton;

	// Right Column
	private ButtonElement caatingaButton;
	private ButtonElement caatingaTallButton;
	private ButtonElement caatingaShrubButton;

	private ButtonElement caatingaPlainsButton;
	private ButtonElement caatingaPlainsShortButton;
	private ButtonElement caatingaPlainsShrubButton;

	public BiomeTreesScreen(Screen parent) {
		super(parent);
	}

	@Override
	public void init() {
		this.buttons.clear();

		int centerX = this.width / 2;
		int startY = this.height / 2 - 110;

		int leftColX = centerX - 205;
		int rightColX = centerX + 5;

		// Master Button
		masterButton = new ButtonElement(BTN_MASTER, centerX - 100, startY, 200, 20, getMasterText());
		this.add(masterButton);

		// --- LEFT COLUMN ---
		swampOakButton = new ButtonElement(BTN_OAK_SWAMP, leftColX, startY + 24, 200, 20, getSwampText());
		this.add(swampOakButton);
		rainforestOakButton = new ButtonElement(BTN_OAK_RAINFOREST, leftColX, startY + 48, 200, 20, getRainforestText());
		this.add(rainforestOakButton);
		giantOakButton = new ButtonElement(BTN_OAK_GIANT, leftColX, startY + 72, 200, 20, getGiantText());
		this.add(giantOakButton);
		fancyMossyOakButton = new ButtonElement(BTN_OAK_FANCY_MOSSY, leftColX, startY + 96, 200, 20, getFancyMossyText());
		this.add(fancyMossyOakButton);
		fancyRegularOakButton = new ButtonElement(BTN_OAK_FANCY_REGULAR, leftColX, startY + 120, 200, 20, getFancyRegularText());
		this.add(fancyRegularOakButton);
		regularOakButton = new ButtonElement(BTN_OAK_REGULAR, leftColX, startY + 144, 200, 20, getRegularOakText());
		this.add(regularOakButton);

		birchForestButton = new ButtonElement(BTN_BIRCH_FOREST, leftColX, startY + 168, 200, 20, getBirchForestText());
		this.add(birchForestButton);
		birchTallButton = new ButtonElement(BTN_BIRCH_TALL, leftColX, startY + 192, 200, 20, getBirchTallText());
		this.add(birchTallButton);
		birchRegularButton = new ButtonElement(BTN_BIRCH_REGULAR, leftColX, startY + 216, 200, 20, getBirchRegularText());
		this.add(birchRegularButton);

		// --- RIGHT COLUMN ---
		caatingaButton = new ButtonElement(BTN_THORN_CAATINGA, rightColX, startY + 24, 200, 20, getCaatingaText());
		this.add(caatingaButton);
		caatingaTallButton = new ButtonElement(BTN_THORN_TALL, rightColX, startY + 48, 200, 20, getCaatingaTallText());
		this.add(caatingaTallButton);
		caatingaShrubButton = new ButtonElement(BTN_THORN_SHRUB, rightColX, startY + 72, 200, 20, getCaatingaShrubText());
		this.add(caatingaShrubButton);

		caatingaPlainsButton = new ButtonElement(BTN_THORN_CAATINGA_PLAINS, rightColX, startY + 96, 200, 20, getCaatingaPlainsText());
		this.add(caatingaPlainsButton);
		caatingaPlainsShortButton = new ButtonElement(BTN_THORN_PLAINS_SHORT, rightColX, startY + 120, 200, 20, getCaatingaPlainsShortText());
		this.add(caatingaPlainsShortButton);
		caatingaPlainsShrubButton = new ButtonElement(BTN_THORN_PLAINS_SHRUB, rightColX, startY + 144, 200, 20, getCaatingaPlainsShrubText());
		this.add(caatingaPlainsShrubButton);

		// Done Button
		this.add(new ButtonElement(BTN_DONE, centerX - 100, this.height - 30, 200, 20, "Done"));

		updateButtonStates();
	}

	// --- Color Helper (strips color when button is disabled/greyed out) ---
	private String getStatusText(boolean value, boolean isEnabled) {
		if (!isEnabled) {
			return value ? "ON" : "OFF";
		}
		return value ? "\u00a75ON" : "\u00a7eOFF";
	}

	// --- Text Helpers ---
	private String getMasterText() {
		return "Biome-aware sapling: " + getStatusText(BiomeTreesConfig.isBiomeAwareGrowthEnabled(), true);
	}

	private String getSwampText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled();
		return "> Swamp trees: " + getStatusText(BiomeTreesConfig.isSwampOakGrowthEnabled(), enabled);
	}
	private String getRainforestText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled();
		return "> Rainforest trees: " + getStatusText(BiomeTreesConfig.isRainforestOakGrowthEnabled(), enabled);
	}
	private String getGiantText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled() && BiomeTreesConfig.isRainforestOakGrowthEnabled();
		return "- Giant oak: " + getStatusText(BiomeTreesConfig.isRainforestGiantOakEnabled(), enabled);
	}
	private String getFancyMossyText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled() && BiomeTreesConfig.isRainforestOakGrowthEnabled();
		return "- Big mossy oak: " + getStatusText(BiomeTreesConfig.isRainforestFancyMossyOakEnabled(), enabled);
	}
	private String getFancyRegularText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled() && BiomeTreesConfig.isRainforestOakGrowthEnabled();
		return "- Big oak: " + getStatusText(BiomeTreesConfig.isRainforestFancyRegularOakEnabled(), enabled);
	}
	private String getRegularOakText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled() && BiomeTreesConfig.isRainforestOakGrowthEnabled();
		return "- Regular rainforest oak: " + getStatusText(BiomeTreesConfig.isRainforestRegularOakEnabled(), enabled);
	}

	private String getBirchForestText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled();
		return "> Birch forest trees: " + getStatusText(BiomeTreesConfig.isBirchForestGrowthEnabled(), enabled);
	}
	private String getBirchTallText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled() && BiomeTreesConfig.isBirchForestGrowthEnabled();
		return "- Taller birch: " + getStatusText(BiomeTreesConfig.isBirchForestTallEnabled(), enabled);
	}
	private String getBirchRegularText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled() && BiomeTreesConfig.isBirchForestGrowthEnabled();
		return "- Tall birch: " + getStatusText(BiomeTreesConfig.isBirchForestRegularEnabled(), enabled);
	}

	private String getCaatingaText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled();
		return "> Caatinga: " + getStatusText(BiomeTreesConfig.isCaatingaGrowthEnabled(), enabled);
	}
	private String getCaatingaTallText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled() && BiomeTreesConfig.isCaatingaGrowthEnabled();
		return "- Tall thorn: " + getStatusText(BiomeTreesConfig.isCaatingaTallThornEnabled(), enabled);
	}
	private String getCaatingaShrubText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled() && BiomeTreesConfig.isCaatingaGrowthEnabled();
		return "- Shrub: " + getStatusText(BiomeTreesConfig.isCaatingaShrubEnabled(), enabled);
	}

	private String getCaatingaPlainsText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled();
		return "> Caatinga plains: " + getStatusText(BiomeTreesConfig.isCaatingaPlainsGrowthEnabled(), enabled);
	}
	private String getCaatingaPlainsShortText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled() && BiomeTreesConfig.isCaatingaPlainsGrowthEnabled();
		return "- Thorn: " + getStatusText(BiomeTreesConfig.isCaatingaPlainsShortThornEnabled(), enabled);
	}
	private String getCaatingaPlainsShrubText() {
		boolean enabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled() && BiomeTreesConfig.isCaatingaPlainsGrowthEnabled();
		return "- Shrub: " + getStatusText(BiomeTreesConfig.isCaatingaPlainsShrubEnabled(), enabled);
	}

	private void updateButtonStates() {
		boolean masterEnabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled();

		swampOakButton.enabled = masterEnabled;
		rainforestOakButton.enabled = masterEnabled;
		birchForestButton.enabled = masterEnabled;
		caatingaButton.enabled = masterEnabled;
		caatingaPlainsButton.enabled = masterEnabled;

		boolean rainEnabled = masterEnabled && BiomeTreesConfig.isRainforestOakGrowthEnabled();
		giantOakButton.enabled = rainEnabled;
		fancyMossyOakButton.enabled = rainEnabled;
		fancyRegularOakButton.enabled = rainEnabled;
		regularOakButton.enabled = rainEnabled;

		boolean birchEnabled = masterEnabled && BiomeTreesConfig.isBirchForestGrowthEnabled();
		birchTallButton.enabled = birchEnabled;
		birchRegularButton.enabled = birchEnabled;

		boolean caatingaEnabled = masterEnabled && BiomeTreesConfig.isCaatingaGrowthEnabled();
		caatingaTallButton.enabled = caatingaEnabled;
		caatingaShrubButton.enabled = caatingaEnabled;

		boolean caatingaPlainsEnabled = masterEnabled && BiomeTreesConfig.isCaatingaPlainsGrowthEnabled();
		caatingaPlainsShortButton.enabled = caatingaPlainsEnabled;
		caatingaPlainsShrubButton.enabled = caatingaPlainsEnabled;

		// Refresh text so disabled buttons strip their color codes
		swampOakButton.displayString = getSwampText();
		rainforestOakButton.displayString = getRainforestText();
		giantOakButton.displayString = getGiantText();
		fancyMossyOakButton.displayString = getFancyMossyText();
		fancyRegularOakButton.displayString = getFancyRegularText();
		regularOakButton.displayString = getRegularOakText();

		birchForestButton.displayString = getBirchForestText();
		birchTallButton.displayString = getBirchTallText();
		birchRegularButton.displayString = getBirchRegularText();

		caatingaButton.displayString = getCaatingaText();
		caatingaTallButton.displayString = getCaatingaTallText();
		caatingaShrubButton.displayString = getCaatingaShrubText();

		caatingaPlainsButton.displayString = getCaatingaPlainsText();
		caatingaPlainsShortButton.displayString = getCaatingaPlainsShortText();
		caatingaPlainsShrubButton.displayString = getCaatingaPlainsShrubText();
	}

	@Override
	protected void buttonClicked(ButtonElement button) {
		if (button.id == BTN_MASTER) {
			BiomeTreesConfig.setBiomeAwareGrowth(!BiomeTreesConfig.isBiomeAwareGrowthEnabled());
			masterButton.displayString = getMasterText();
			updateButtonStates();
		} else if (button.id == BTN_OAK_SWAMP && swampOakButton.enabled) {
			BiomeTreesConfig.setSwampOakGrowth(!BiomeTreesConfig.isSwampOakGrowthEnabled());
			swampOakButton.displayString = getSwampText();
		} else if (button.id == BTN_OAK_RAINFOREST && rainforestOakButton.enabled) {
			BiomeTreesConfig.setRainforestOakGrowth(!BiomeTreesConfig.isRainforestOakGrowthEnabled());
			rainforestOakButton.displayString = getRainforestText();
			updateButtonStates();
		} else if (button.id == BTN_OAK_GIANT && giantOakButton.enabled) {
			BiomeTreesConfig.setRainforestGiantOak(!BiomeTreesConfig.isRainforestGiantOakEnabled());
			giantOakButton.displayString = getGiantText();
		} else if (button.id == BTN_OAK_FANCY_MOSSY && fancyMossyOakButton.enabled) {
			BiomeTreesConfig.setRainforestFancyMossyOak(!BiomeTreesConfig.isRainforestFancyMossyOakEnabled());
			fancyMossyOakButton.displayString = getFancyMossyText();
		} else if (button.id == BTN_OAK_FANCY_REGULAR && fancyRegularOakButton.enabled) {
			BiomeTreesConfig.setRainforestFancyRegularOak(!BiomeTreesConfig.isRainforestFancyRegularOakEnabled());
			fancyRegularOakButton.displayString = getFancyRegularText();
		} else if (button.id == BTN_OAK_REGULAR && regularOakButton.enabled) {
			BiomeTreesConfig.setRainforestRegularOak(!BiomeTreesConfig.isRainforestRegularOakEnabled());
			regularOakButton.displayString = getRegularOakText();
		} else if (button.id == BTN_BIRCH_FOREST && birchForestButton.enabled) {
			BiomeTreesConfig.setBirchForestGrowth(!BiomeTreesConfig.isBirchForestGrowthEnabled());
			birchForestButton.displayString = getBirchForestText();
			updateButtonStates();
		} else if (button.id == BTN_BIRCH_TALL && birchTallButton.enabled) {
			BiomeTreesConfig.setBirchForestTall(!BiomeTreesConfig.isBirchForestTallEnabled());
			birchTallButton.displayString = getBirchTallText();
		} else if (button.id == BTN_BIRCH_REGULAR && birchRegularButton.enabled) {
			BiomeTreesConfig.setBirchForestRegular(!BiomeTreesConfig.isBirchForestRegularEnabled());
			birchRegularButton.displayString = getBirchRegularText();
		} else if (button.id == BTN_THORN_CAATINGA && caatingaButton.enabled) {
			BiomeTreesConfig.setCaatingaGrowth(!BiomeTreesConfig.isCaatingaGrowthEnabled());
			caatingaButton.displayString = getCaatingaText();
			updateButtonStates();
		} else if (button.id == BTN_THORN_TALL && caatingaTallButton.enabled) {
			BiomeTreesConfig.setCaatingaTallThorn(!BiomeTreesConfig.isCaatingaTallThornEnabled());
			caatingaTallButton.displayString = getCaatingaTallText();
		} else if (button.id == BTN_THORN_SHRUB && caatingaShrubButton.enabled) {
			BiomeTreesConfig.setCaatingaShrub(!BiomeTreesConfig.isCaatingaShrubEnabled());
			caatingaShrubButton.displayString = getCaatingaShrubText();
		} else if (button.id == BTN_THORN_CAATINGA_PLAINS && caatingaPlainsButton.enabled) {
			BiomeTreesConfig.setCaatingaPlainsGrowth(!BiomeTreesConfig.isCaatingaPlainsGrowthEnabled());
			caatingaPlainsButton.displayString = getCaatingaPlainsText();
			updateButtonStates();
		} else if (button.id == BTN_THORN_PLAINS_SHORT && caatingaPlainsShortButton.enabled) {
			BiomeTreesConfig.setCaatingaPlainsShortThorn(!BiomeTreesConfig.isCaatingaPlainsShortThornEnabled());
			caatingaPlainsShortButton.displayString = getCaatingaPlainsShortText();
		} else if (button.id == BTN_THORN_PLAINS_SHRUB && caatingaPlainsShrubButton.enabled) {
			BiomeTreesConfig.setCaatingaPlainsShrub(!BiomeTreesConfig.isCaatingaPlainsShrubEnabled());
			caatingaPlainsShrubButton.displayString = getCaatingaPlainsShrubText();
		} else if (button.id == BTN_DONE) {
			this.mc.displayScreen(this.getParentScreen());
		}
	}
}
