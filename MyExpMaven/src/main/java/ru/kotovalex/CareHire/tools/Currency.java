package ru.kotovalex.CareHire.tools;

/**
 * Created by vasilenko.aleksandr on 06.07.2016.
 */
public class Currency {
    private String Code;
    private String Symbol;
    private String ThousandsSeparator;
    private String DecimalSeparator;
    private boolean SymbolOnLeft;
    private boolean SpaceBetweenAmountAndSymbol;
    private int RoundingCoefficient;
    private int DecimalDigits;

    @Override
    public String toString() {
        return Code ;
    }
}
