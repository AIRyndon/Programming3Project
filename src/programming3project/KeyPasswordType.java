package programming3project;

public enum KeyPasswordType 
{
    KEYHEAD(0), KEYTAIL(1);
    
    private int functionValue;
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    KeyPasswordType(int functionValue)
    {
        this.functionValue = functionValue;
    }
    
    KeyPasswordType()
    {
        functionValue = 0;
    }
    // </editor-fold>
}
