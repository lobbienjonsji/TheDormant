package theDormant.cards.democards.complex;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theDormant.cards.AbstractEasyCard;
import theDormant.powers.ExampleTwoAmountPower;

import static theDormant.DormantMod.makeID;
import static theDormant.util.Wiz.applyToSelf;

public class ExampleTwoAmountPowerCard extends AbstractEasyCard {
    public final static String ID = makeID("ExampleTwoAmountPowerCard");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public ExampleTwoAmountPowerCard() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ExampleTwoAmountPower(p, magicNumber, 1));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}