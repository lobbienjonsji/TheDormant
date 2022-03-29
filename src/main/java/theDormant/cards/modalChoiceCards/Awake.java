package theDormant.cards.modalChoiceCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDormant.DormantMod;
import theDormant.cards.AbstractDormantCard;
import theDormant.cards.AbstractEasyCard;
import theDormant.cards.EasyModalChoiceCard;

import static theDormant.DormantMod.makeID;

public class Awake extends AbstractEasyCard {
    public final static String ID = makeID("Awake");
    
    public Awake() {
        super(ID, -1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    
    }
    
    @Override
    public void onChoseThisOption() {
        super.onChoseThisOption();
        DormantMod.IsAwoken = true;
        TransformCardsInGroup(AbstractDungeon.player.drawPile);
        TransformCardsInGroup(AbstractDungeon.player.discardPile);
        AbstractDungeon.player.hand.applyPowers();
    }
    
    public void TransformCardsInGroup(CardGroup group)
    {
        for (int i = 0; i < group.group.size(); i++) {
            AbstractCard card = group.group.get(i);
            if(card instanceof AbstractDormantCard)
            {
                group.group.set(i, ((AbstractDormantCard) card).returnAwokenCard());
            }
        }
    }
    
    @Override
    public void upp() {
    
    }
}
