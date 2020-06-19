
package gui_project.ModelController;

/**
 * 
 * Implemented by the View that wants to respond to changes in the model
 */
public interface BaseObserver
{
    void update(BaseModel model);
}
