package theDormant.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDormant.DormantMod;

import static theDormant.DormantMod.makeID;

public class TestCard extends AbstractEasyCard{
    public TestCard() {
        super(makeID("TestCard"), 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }
    
    @Override
    public void upp() {
    
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new MakeTempCardInDiscardAction(new Whet(), 1));
        addToBot(new MakeTempCardInDrawPileAction(new Whet(), 1, true, true));
        DormantMod.IsAwoken = true;
    }
}
