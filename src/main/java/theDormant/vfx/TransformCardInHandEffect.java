package theDormant.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;

import java.awt.*;

public class TransformCardInHandEffect extends AbstractGameEffect {
    private static final float EFFECT_DUR = 0.8F;
    private AbstractCard card;
    private AbstractCard currentCard;
    private int index;
    private boolean hasCardBeenTransformed;
    
    public TransformCardInHandEffect(int index, AbstractCard currentCard, AbstractCard card) {
        this.index = index;
        this.card = card;
        hasCardBeenTransformed = false;
        duration = EFFECT_DUR;
        color = Color.WHITE;
        this.currentCard = currentCard;
    }
    
    @Override
    public void update()
    {
        duration -= Gdx.graphics.getDeltaTime();
        if(duration <= 0.4F && !hasCardBeenTransformed)
        {
            hasCardBeenTransformed = true;
            card.current_x = currentCard.current_x;
            card.current_y = currentCard.current_y;
            card.target_x = card.current_x;
            card.target_y = card.current_y;
            card.angle = currentCard.angle;
            card.targetAngle = card.angle;
            card.update();
            AbstractDungeon.player.hand.group.set(index, card);
            AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
        }
        if (this.duration < 0.0F) {
            AbstractDungeon.player.hand.glowCheck();
            this.isDone = true;
        }
    }
    
    @Override
    public void render(SpriteBatch spriteBatch) {
    
    }
    
    @Override
    public void dispose() {
    
    }
}
