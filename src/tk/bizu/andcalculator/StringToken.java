package tk.bizu.andcalculator;

public class StringToken implements Token
{
    private String aValue;
    
    public StringToken(String paValue)
    {
        this.aValue = paValue;
    }
    
    public String getStringValue()
    {
        return aValue;
    }
}
