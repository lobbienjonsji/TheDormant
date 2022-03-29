package theDormant.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.DamageHooks;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theDormant.DormantMod;
import theDormant.vfx.TransformCardInHandEffect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractDormantCard extends AbstractEasyCard{
    public AbstractDormantCard(boolean forPreview, String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        if(!forPreview)
        {
            cardToPreview = new ArrayList<>(Arrays.asList(returnAwokenCard()));
        }
    }
    
    @Override
    public void applyPowers() {
        super.applyPowers();
        if(DormantMod.IsAwoken && AbstractDungeon.player.hand.contains(this))
        {
            AbstractDungeon.effectList.add(new TransformCardInHandEffect(AbstractDungeon.player.hand.group.indexOf(this),
                    this, returnAwokenCard()));
        }
    }
    
    public abstract AbstractAwokenCard returnAwokenCard();
}
