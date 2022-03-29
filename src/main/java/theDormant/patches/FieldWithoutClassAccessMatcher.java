package theDormant.patches;

import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.patcher.Expectation;
import javassist.expr.Expr;
import javassist.expr.FieldAccess;

public class FieldWithoutClassAccessMatcher extends Matcher {
    private String fieldName;
    
    public FieldWithoutClassAccessMatcher(String fieldName) {
        super(Expectation.FIELD_ACCESS);
        this.fieldName = fieldName;
    }
    
    public boolean match(Expr toMatch) {
        FieldAccess expr = (FieldAccess)toMatch;
        return expr.getFieldName().equals(this.fieldName);
    }
}
