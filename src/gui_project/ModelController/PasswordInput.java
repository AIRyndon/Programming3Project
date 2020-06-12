/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

/**
 *
 * @author pc
 */
public class PasswordInput extends BaseModel
{
    private String passwordHead;
    private String passwordTail;
    private final LockedArea lockedArea;
    private final String beforeQuestion;
    private final String questions;
    private final String answer;
    private final boolean correct;
    
    PasswordInput(LockedArea lockedArea)
    {
        this.lockedArea = lockedArea;
        passwordHead = lockedArea.getPassword().substring(0, 1) + "XX";
        passwordTail = "XX" + lockedArea.getPassword().substring(2);
    }
}
