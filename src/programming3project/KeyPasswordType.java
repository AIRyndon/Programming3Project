/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

/**
 *
 * @author pc
 */
public enum KeyPasswordType 
{
    KEYHEAD(0), KEYTAIL(1);
    
    private int functionValue;
    
    KeyPasswordType(int functionValue)
    {
        this.setFunctionValue(functionValue);
    }

    KeyPasswordType() 
    {
        this.setFunctionValue(0);
    }
    
    /**
     * @return the functionValue
     */
    public int getFunctionValue()
    {
        return functionValue;
    }

    /**
     * @param functionValue the functionValue to set
     */
    public void setFunctionValue(int functionValue) 
    {
        this.functionValue = functionValue;
    }
}
