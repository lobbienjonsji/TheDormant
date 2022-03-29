package theDormant.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theDormant.DormantMod.makeID;

public class Whet extends AbstractDormantCard {
    public final static String ID = makeID("Whet");
    public Whet() {
        super(false, ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY); // This card is a 1 cost Common Attack that targets an Enemy.
        baseDamage = 8;
    }
    
    public Whet(boolean forPreview)
    {
        super(forPreview, ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY); // This card is a 1 cost Common Attack that targets an Enemy.
        baseDamage = 8;
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }
    
    public void upp() {
        upgradeDamage(3);
    }
    
    @Override
    public AbstractAwokenCard returnAwokenCard() {
        SharpClaws sharpClaws = new SharpClaws(true);
        //TODO: ALSO COPY CARD MODS *they have a makeCopy,
        // so just gotta for each loop over the card's card mods and apply .makeCopies to the other*
        if(upgraded)
        {
            sharpClaws.upgrade();
        }
        return sharpClaws;
    }
}