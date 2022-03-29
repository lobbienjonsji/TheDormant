package theDormant.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theDormant.DormantMod;
import theDormant.cards.AbstractAwokenCard;
import theDormant.cards.AbstractDormantCard;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Logger;

public class OnCardCreationPatch{
    @SpirePatch2(clz = ShowCardAndAddToDrawPileEffect.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, float.class, float.class, boolean.class, boolean.class, boolean.class})
    @SpirePatch2(clz = ShowCardAndAddToDrawPileEffect.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, boolean.class, boolean.class})
    @SpirePatch2(clz = ShowCardAndAddToHandEffect.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class})
    @SpirePatch2(clz = ShowCardAndAddToHandEffect.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, float.class, float.class})
    @SpirePatch2(clz = ShowCardAndAddToDiscardEffect.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class})
    @SpirePatch2(clz = ShowCardAndAddToDiscardEffect.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, float.class, float.class})
    public static class OnCardCreation
    {
        @SpireInsertPatch(locator=Locator.class)
        public static void Insert(AbstractGameEffect __instance)
        {
            try {
                Field field = __instance.getClass().getDeclaredField("card");
                field.setAccessible(true);
                AbstractCard card = (AbstractCard) field.get(__instance);
                if(card instanceof AbstractDormantCard && DormantMod.IsAwoken)
                {
                    field.set(__instance, ((AbstractDormantCard) card).returnAwokenCard().makeStatEquivalentCopy());
                }
                else if(card instanceof AbstractAwokenCard && !DormantMod.IsAwoken)
                {
                    field.set(__instance, ((AbstractAwokenCard) card).returnDormantCard().makeStatEquivalentCopy());
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                DormantMod.logger.info(e.getMessage());
            }
            
        }
        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new FieldWithoutClassAccessMatcher("card");
                int[] locations = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
                for (int i = 0; i < locations.length; i++) {
                    locations[i]+=1;
                }
                return locations;
            }
        }
    }
    @SpirePatch2(clz = CardGroup.class, method = "addToTop")
    public static class OnToTopPatch
    {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(CardGroup __instance, AbstractCard c) {
            if(c instanceof AbstractDormantCard && DormantMod.IsAwoken)
            {
                __instance.addToTop(((AbstractDormantCard) c).returnAwokenCard());
                return SpireReturn.Return();
            }
            else if(c instanceof AbstractAwokenCard && !DormantMod.IsAwoken)
            {
                __instance.addToTop(((AbstractAwokenCard) c).returnDormantCard());
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
    
    @SpirePatch2(clz = CardGroup.class, method = "addToBottom")
    public static class OnToBottomPatch
    {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(CardGroup __instance, AbstractCard c) {
            if(c instanceof AbstractDormantCard && DormantMod.IsAwoken)
            {
                __instance.addToBottom(((AbstractDormantCard) c).returnAwokenCard());
                return SpireReturn.Return();
            }
            else if(c instanceof AbstractAwokenCard && !DormantMod.IsAwoken)
            {
                __instance.addToBottom(((AbstractAwokenCard) c).returnDormantCard());
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
    
    @SpirePatch2(clz = CardGroup.class, method = "addToRandomSpot")
    public static class OnToRandomPatch
    {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(CardGroup __instance, AbstractCard c) {
            if(c instanceof AbstractDormantCard && DormantMod.IsAwoken)
            {
                __instance.addToRandomSpot(((AbstractDormantCard) c).returnAwokenCard());
                return SpireReturn.Return();
            }
            else if(c instanceof AbstractAwokenCard && !DormantMod.IsAwoken)
            {
                __instance.addToRandomSpot(((AbstractAwokenCard) c).returnDormantCard());
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
    @SpirePatch2(clz = CardGroup.class, method = "addToHand")
    public static class OnToHandPatch
    {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(CardGroup __instance, AbstractCard c) {
            if(c instanceof AbstractDormantCard && DormantMod.IsAwoken)
            {
                __instance.addToHand(((AbstractDormantCard) c).returnAwokenCard());
                return SpireReturn.Return();
            }
            else if(c instanceof AbstractAwokenCard && !DormantMod.IsAwoken)
            {
                __instance.addToHand(((AbstractAwokenCard) c).returnDormantCard());
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}