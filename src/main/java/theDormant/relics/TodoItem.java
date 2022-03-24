package theDormant.relics;

import theDormant.TheDormant;

import static theDormant.DormantMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheDormant.Enums.DORMANT_COLOR);
    }
}
