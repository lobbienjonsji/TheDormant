package theDormant;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theDormant.actions.EasyModalChoiceAction;
import theDormant.cards.AbstractDormantCard;
import theDormant.cards.AbstractEasyCard;
import theDormant.cards.EasyModalChoiceCard;
import theDormant.cards.cardvars.SecondDamage;
import theDormant.cards.cardvars.SecondMagicNumber;
import theDormant.cards.modalChoiceCards.Awake;
import theDormant.cards.modalChoiceCards.Rest;
import theDormant.relics.AbstractEasyRelic;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static theDormant.util.Wiz.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class DormantMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        OnStartBattleSubscriber,
        OnPlayerTurnStartPostDrawSubscriber{

    public static final String modID = "dormantmod";
    
    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    // This makes debugging so much easier
    public static Logger logger = LogManager.getLogger(DormantMod.class.getName());

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually
    public static Color awokenColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually
    
    
    
    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";
    
    private static final String ATTACK_S_ART_AWOKEN = modID + "Resources/images/512/attackAwoken.png";
    private static final String SKILL_S_ART_AWOKEN = modID + "Resources/images/512/skillAwoken.png";
    private static final String POWER_S_ART_AWOKEN = modID + "Resources/images/512/powerAwoken.png";
    private static final String ATTACK_L_ART_AWOKEN = modID + "Resources/images/1024/attackAwoken.png";
    private static final String SKILL_L_ART_AWOKEN = modID + "Resources/images/1024/skillAwoken.png";
    private static final String POWER_L_ART_AWOKEN = modID + "Resources/images/1024/powerAwoken.png";
    
    public static boolean IsAwoken = false;
    public static int TurnCounter = 0;
    
    public DormantMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheDormant.Enums.DORMANT_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
        BaseMod.addColor(TheDormant.Enums.AWOKEN_COLOR, awokenColor, awokenColor, awokenColor,
                awokenColor, awokenColor, awokenColor, awokenColor,
                ATTACK_S_ART_AWOKEN, SKILL_S_ART_AWOKEN, POWER_S_ART_AWOKEN, CARD_ENERGY_S,
                ATTACK_L_ART_AWOKEN, SKILL_L_ART_AWOKEN, POWER_L_ART_AWOKEN,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        DormantMod thismod = new DormantMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheDormant(TheDormant.characterStrings.NAMES[1], TheDormant.Enums.THE_DORMANT),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheDormant.Enums.THE_DORMANT);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }


    @Override
    public void receiveEditStrings() {
        logger.info("Loading custom Strings for " + modID);
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/eng/Powerstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    
    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        IsAwoken = false;
        TurnCounter = 0;
    }
    
    @Override
    public void receiveOnPlayerTurnStartPostDraw() {
        TurnCounter +=1;
        boolean hasDormantCard = false;
        for (AbstractCard card: AbstractDungeon.player.hand.group) {
            if(card instanceof AbstractDormantCard)
            {
                hasDormantCard = true;
            }
        }
        for (AbstractCard card: AbstractDungeon.player.drawPile.group) {
            if(card instanceof AbstractDormantCard)
            {
                hasDormantCard = true;
            }
        }
        for (AbstractCard card: AbstractDungeon.player.discardPile.group) {
            if(card instanceof AbstractDormantCard)
            {
                hasDormantCard = true;
            }
        }
        if(TurnCounter >= 4 && hasDormantCard && !IsAwoken)
        {
            ArrayList<AbstractCard> easyCardList = new ArrayList<>();
            easyCardList.add(new Rest());
            easyCardList.add(new Awake());
            atb(new EasyModalChoiceAction(easyCardList));
        }
    }
}
