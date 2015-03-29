package tk.bizu.andcalculator;

public class Number extends Node
{
    private double aValue;
    
    public Number(double paValue)
    {
        this.aValue = paValue;
    }

    @Override
    public double calculate()
    {
        return aValue;
    }
    
    @Override
    public String getStringValue()
    {
        return aValue + "";
    }
}