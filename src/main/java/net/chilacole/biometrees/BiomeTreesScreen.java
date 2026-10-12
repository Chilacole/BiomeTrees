package net.chilacole.biometrees;

import net.minecraft.client.gui.ButtonElement;
import net.minecraft.client.gui.Screen;

public class BiomeTreesScreen extends Screen {

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

		// Column coordinates
		int leftColX = centerX - 205;
		int rightColX = centerX + 5;

		// Master Button (Top Center)
		masterButton = new ButtonElement(0, centerX - 100, startY, 200, 20, getMasterText());
		this.add(masterButton);

		// --- LEFT COLUMN: OAK & BIRCH ---
		swampOakButton = new ButtonElement(1, leftColX, startY + 24, 200, 20, getSwampText());
		this.add(swampOakButton);
		rainforestOakButton = new ButtonElement(2, leftColX, startY + 48, 200, 20, getRainforestText());
		this.add(rainforestOakButton);
		giantOakButton = new ButtonElement(4, leftColX, startY + 72, 200, 20, getGiantText());
		this.add(giantOakButton);
		fancyMossyOakButton = new ButtonElement(5, leftColX, startY + 96, 200, 20, getFancyMossyText());
		this.add(fancyMossyOakButton);
		fancyRegularOakButton = new ButtonElement(6, leftColX, startY + 120, 200, 20, getFancyRegularText());
		this.add(fancyRegularOakButton);
		regularOakButton = new ButtonElement(7, leftColX, startY + 144, 200, 20, getRegularOakText());
		this.add(regularOakButton);

		birchForestButton = new ButtonElement(11, leftColX, startY + 168, 200, 20, getBirchForestText());
		this.add(birchForestButton);
		birchTallButton = new ButtonElement(12, leftColX, startY + 192, 200, 20, getBirchTallText());
		this.add(birchTallButton);
		birchRegularButton = new ButtonElement(13, leftColX, startY + 216, 200, 20, getBirchRegularText());
		this.add(birchRegularButton);

		// --- RIGHT COLUMN: THORN ---
		caatingaButton = new ButtonElement(21, rightColX, startY + 24, 200, 20, getCaatingaText());
		this.add(caatingaButton);
		caatingaTallButton = new ButtonElement(22, rightColX, startY + 48, 200, 20, getCaatingaTallText());
		this.add(caatingaTallButton);
		caatingaShrubButton = new ButtonElement(23, rightColX, startY + 72, 200, 20, getCaatingaShrubText());
		this.add(caatingaShrubButton);

		caatingaPlainsButton = new ButtonElement(24, rightColX, startY + 96, 200, 20, getCaatingaPlainsText());
		this.add(caatingaPlainsButton);
		caatingaPlainsShortButton = new ButtonElement(25, rightColX, startY + 120, 200, 20, getCaatingaPlainsShortText());
		this.add(caatingaPlainsShortButton);
		caatingaPlainsShrubButton = new ButtonElement(26, rightColX, startY + 144, 200, 20, getCaatingaPlainsShrubText());
		this.add(caatingaPlainsShrubButton);

		// Done Button (Bottom Center)
		this.add(new ButtonElement(10, centerX - 100, this.height - 30, 200, 20, "Done"));

		updateButtonStates();
	}

	// --- Text Helpers ---
	private String getMasterText() { return "Biome-aware growth: " + (BiomeTreesConfig.isBiomeAwareGrowthEnabled() ? "ON" : "OFF"); }

	private String getSwampText() { return "> Swamp oaks: " + (BiomeTreesConfig.isSwampOakGrowthEnabled() ? "ON" : "OFF"); }
	private String getRainforestText() { return "> Rainforest oaks: " + (BiomeTreesConfig.isRainforestOakGrowthEnabled() ? "ON" : "OFF"); }
	private String getGiantText() { return "    - Giant round oak: " + (BiomeTreesConfig.isRainforestGiantOakEnabled() ? "ON" : "OFF"); }
	private String getFancyMossyText() { return "    - Fancy mossy oak: " + (BiomeTreesConfig.isRainforestFancyMossyOakEnabled() ? "ON" : "OFF"); }
	private String getFancyRegularText() { return "    - Fancy regular oak: " + (BiomeTreesConfig.isRainforestFancyRegularOakEnabled() ? "ON" : "OFF"); }
	private String getRegularOakText() { return "    - Regular oak: " + (BiomeTreesConfig.isRainforestRegularOakEnabled() ? "ON" : "OFF"); }

	private String getBirchForestText() { return "> Birch forest trees: " + (BiomeTreesConfig.isBirchForestGrowthEnabled() ? "ON" : "OFF"); }
	private String getBirchTallText() { return "    - Tall birch: " + (BiomeTreesConfig.isBirchForestTallEnabled() ? "ON" : "OFF"); }
	private String getBirchRegularText() { return "    - Regular birch: " + (BiomeTreesConfig.isBirchForestRegularEnabled() ? "ON" : "OFF"); }

	private String getCaatingaText() { return "> Caatinga thorns: " + (BiomeTreesConfig.isCaatingaGrowthEnabled() ? "ON" : "OFF"); }
	private String getCaatingaTallText() { return "    - Tall thorn: " + (BiomeTreesConfig.isCaatingaTallThornEnabled() ? "ON" : "OFF"); }
	private String getCaatingaShrubText() { return "    - Shrub: " + (BiomeTreesConfig.isCaatingaShrubEnabled() ? "ON" : "OFF"); }

	private String getCaatingaPlainsText() { return "> Caatinga plains thorns: " + (BiomeTreesConfig.isCaatingaPlainsGrowthEnabled() ? "ON" : "OFF"); }
	private String getCaatingaPlainsShortText() { return "    - Short thorn: " + (BiomeTreesConfig.isCaatingaPlainsShortThornEnabled() ? "ON" : "OFF"); }
	private String getCaatingaPlainsShrubText() { return "    - Shrub: " + (BiomeTreesConfig.isCaatingaPlainsShrubEnabled() ? "ON" : "OFF"); }

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
	}

	@Override
	protected void buttonClicked(ButtonElement button) {
		if (button.id == 0) {
			BiomeTreesConfig.setBiomeAwareGrowth(!BiomeTreesConfig.isBiomeAwareGrowthEnabled());
			masterButton.displayString = getMasterText();
			updateButtonStates();
		} else if (button.id == 1 && swampOakButton.enabled) {
			BiomeTreesConfig.setSwampOakGrowth(!BiomeTreesConfig.isSwampOakGrowthEnabled());
			swampOakButton.displayString = getSwampText();
		} else if (button.id == 2 && rainforestOakButton.enabled) {
			BiomeTreesConfig.setRainforestOakGrowth(!BiomeTreesConfig.isRainforestOakGrowthEnabled());
			rainforestOakButton.displayString = getRainforestText();
			updateButtonStates();
		} else if (button.id == 4 && giantOakButton.enabled) {
			BiomeTreesConfig.setRainforestGiantOak(!BiomeTreesConfig.isRainforestGiantOakEnabled());
			giantOakButton.displayString = getGiantText();
		} else if (button.id == 5 && fancyMossyOakButton.enabled) {
			BiomeTreesConfig.setRainforestFancyMossyOak(!BiomeTreesConfig.isRainforestFancyMossyOakEnabled());
			fancyMossyOakButton.displayString = getFancyMossyText();
		} else if (button.id == 6 && fancyRegularOakButton.enabled) {
			BiomeTreesConfig.setRainforestFancyRegularOak(!BiomeTreesConfig.isRainforestFancyRegularOakEnabled());
			fancyRegularOakButton.displayString = getFancyRegularText();
		} else if (button.id == 7 && regularOakButton.enabled) {
			BiomeTreesConfig.setRainforestRegularOak(!BiomeTreesConfig.isRainforestRegularOakEnabled());
			regularOakButton.displayString = getRegularOakText();
		} else if (button.id == 11 && birchForestButton.enabled) {
			BiomeTreesConfig.setBirchForestGrowth(!BiomeTreesConfig.isBirchForestGrowthEnabled());
			birchForestButton.displayString = getBirchForestText();
			updateButtonStates();
		} else if (button.id == 12 && birchTallButton.enabled) {
			BiomeTreesConfig.setBirchForestTall(!BiomeTreesConfig.isBirchForestTallEnabled());
			birchTallButton.displayString = getBirchTallText();
		} else if (button.id == 13 && birchRegularButton.enabled) {
			BiomeTreesConfig.setBirchForestRegular(!BiomeTreesConfig.isBirchForestRegularEnabled());
			birchRegularButton.displayString = getBirchRegularText();
		} else if (button.id == 21 && caatingaButton.enabled) {
			BiomeTreesConfig.setCaatingaGrowth(!BiomeTreesConfig.isCaatingaGrowthEnabled());
			caatingaButton.displayString = getCaatingaText();
			updateButtonStates();
		} else if (button.id == 22 && caatingaTallButton.enabled) {
			BiomeTreesConfig.setCaatingaTallThorn(!BiomeTreesConfig.isCaatingaTallThornEnabled());
			caatingaTallButton.displayString = getCaatingaTallText();
		} else if (button.id == 23 && caatingaShrubButton.enabled) {
			BiomeTreesConfig.setCaatingaShrub(!BiomeTreesConfig.isCaatingaShrubEnabled());
			caatingaShrubButton.displayString = getCaatingaShrubText();
		} else if (button.id == 24 && caatingaPlainsButton.enabled) {
			BiomeTreesConfig.setCaatingaPlainsGrowth(!BiomeTreesConfig.isCaatingaPlainsGrowthEnabled());
			caatingaPlainsButton.displayString = getCaatingaPlainsText();
			updateButtonStates();
		} else if (button.id == 25 && caatingaPlainsShortButton.enabled) {
			BiomeTreesConfig.setCaatingaPlainsShortThorn(!BiomeTreesConfig.isCaatingaPlainsShortThornEnabled());
			caatingaPlainsShortButton.displayString = getCaatingaPlainsShortText();
		} else if (button.id == 26 && caatingaPlainsShrubButton.enabled) {
			BiomeTreesConfig.setCaatingaPlainsShrub(!BiomeTreesConfig.isCaatingaPlainsShrubEnabled());
			caatingaPlainsShrubButton.displayString = getCaatingaPlainsShrubText();
		} else if (button.id == 10) {
			this.mc.displayScreen(this.getParentScreen());
		}
	}
}
