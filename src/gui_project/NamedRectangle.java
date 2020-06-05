/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project;

import java.awt.Rectangle;

/**
 *
 * @author Angelo
 */
public class NamedRectangle extends Rectangle
{

    private final String name;

    public NamedRectangle(String name, int x, int y, int width, int height)
    {
        super(x, y, width, height);
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
