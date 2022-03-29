package theDormant.cards.modalChoiceCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDormant.cards.AbstractEasyCard;
import theDormant.cards.EasyModalChoiceCard;

import static theDormant.DormantMod.makeID;

public class Rest extends AbstractEasyCard {
    public final static String ID = makeID("Rest");
    public Rest() {
        super(ID, -1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    
    }
    
    @Override
    public void onChoseThisOption() {
        super.onChoseThisOption();
    }
    
    @Override
    public void upp() {
    
    }
}
