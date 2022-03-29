package theDormant.cards;

import theDormant.TheDormant;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractAwokenCard extends AbstractEasyCard{
    public AbstractAwokenCard(boolean forPreview, String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, TheDormant.Enums.AWOKEN_COLOR);
        if(!forPreview)
        {
            cardToPreview = new ArrayList<>(Arrays.asList(returnDormantCard()));
        }
    }
    
    public abstract AbstractDormantCard returnDormantCard();
}
