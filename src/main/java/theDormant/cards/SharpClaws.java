package theDormant.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theDormant.DormantMod.makeID;

public class SharpClaws extends AbstractAwokenCard{
    public final static String ID = makeID("SharpClaws");
    public SharpClaws() {
        super(false, ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 12;
    }
    
    public SharpClaws(boolean forPreview) {
        super(forPreview, ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 12;
    }
    
    @Override
    public void upp() {
        upgradeDamage(4);
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }
    
    @Override
    public AbstractDormantCard returnDormantCard() {
        Whet whet = new Whet(true);
        //TODO: ALSO COPY CARD MODS *they have a makeCopy,
        // so just gotta for each loop over the card's card mods and apply .makeCopies to the other*
        if(upgraded)
        {
            whet.upgrade();
        }
        return whet;
    }
}
