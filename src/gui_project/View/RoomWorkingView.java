/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.View;

import gui_project.ModelController.BaseModel;
import gui_project.ModelController.BaseObserver;
import gui_project.ModelController.DetectiveController;
import gui_project.ModelController.Hint;
import gui_project.ModelController.HintController;
import gui_project.ModelController.KeyPassword;
import gui_project.ModelController.KeyPasswordController;
import gui_project.ModelController.LockedAreaController;
import gui_project.ModelController.MainController;
import gui_project.ModelController.NPC;
import gui_project.ModelController.NPCController;
import gui_project.ModelController.RoomWorkingController;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author pc
 */
public class RoomWorkingView extends javax.swing.JPanel implements
        ComponentListener, BaseObserver
{

    private final MainController mainCtrl;
    private final DetectiveController detectiveCtrl;
    private final NPCController victimCtrl;
    private LockedAreaController lockedAreaCtrl;
    private final RoomWorkingController roomCtrl;
    private Rectangle officeLockBound, keyPasswordBound;
    private KeyPasswordController keyPasswordCtrl;

    /**
     * Creates new form RoomView
     */
    public RoomWorkingView(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            NPCController victimCtrl,
            RoomWorkingController roomCtrl)
    {
        this.mainCtrl = mainCtrl;
        this.detectiveCtrl = detectiveCtrl;
        this.victimCtrl = victimCtrl;
        this.roomCtrl = roomCtrl;

        initComponents();
        gameTextArea.setEditable(false);
        gameTextArea.setFocusable(false);
        addComponentListener(this);
        setFocusable(true);
    }

    @Override
    public void update(BaseModel model)
    {
        if (model instanceof NPC)
        {
            gameTextArea.setText(((NPC) model).getSpokenLine());
            mainCtrl.updateConversationLevel();
        } else if (model instanceof Hint)
        {
            gameTextArea.setText(((Hint) model).getMessage());
        } else if (model instanceof KeyPassword)
        {
            gameTextArea.setText(((KeyPassword) model).getMessage());
        }

    }

    @Override
    public void componentShown(ComponentEvent e)
    {
        roomCtrl.getItemBlockCtrls().forEach(i ->
        {
            i.getItemBlock().registerObserver(this);
        });

        requestFocusInWindow();
    }

    public Rectangle getBound()
    {
        return new Rectangle(10, 15,
                this.getSize().width - 30, this.getSize().height - 30);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        detectiveCtrl.draw(g2);
        g2.draw(getBound());

        roomCtrl.getItemBlockCtrls().forEach(itemBlockCtrl ->
        {
            if (itemBlockCtrl instanceof NPCController)
            {
                NPCController npc = (NPCController) itemBlockCtrl;
                npc.draw(g2);
            } else if (itemBlockCtrl instanceof HintController)
            {
                HintController hint = (HintController) itemBlockCtrl;
                hint.draw(g2);
            } else if (itemBlockCtrl instanceof LockedAreaController)
            {
                LockedAreaController areaLocked = (LockedAreaController) itemBlockCtrl;
                areaLocked.draw(g2);

                lockedAreaCtrl = areaLocked;
                officeLockBound = areaLocked.getView().getBound();
            } else if (itemBlockCtrl instanceof KeyPasswordController)
            {
                KeyPasswordController keyPassword = (KeyPasswordController) itemBlockCtrl;
                keyPassword.draw(g2);
            }
        });

        roomCtrl.getItemBlockCtrls().forEach(itemBlockCtrl ->
        {
            if (itemBlockCtrl instanceof LockedAreaController)
            {
                LockedAreaController areaLocked = (LockedAreaController) itemBlockCtrl;
                officeLockBound = areaLocked.getView().getBound();
            } else if (itemBlockCtrl instanceof KeyPasswordController)
            {
                KeyPasswordController keyPassword = (KeyPasswordController) itemBlockCtrl;
                keyPassword.draw(g2);

                keyPasswordCtrl = keyPassword;
                keyPasswordBound = keyPassword.getView().getBound();
            } else
            {
                itemBlockCtrl.draw(g2);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        houseDoor = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gameTextArea = new javax.swing.JTextArea();

        setName("WorkingRoom"); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                formKeyReleased(evt);
            }
        });

        jLabel1.setText("WorkingRoom");

        houseDoor.setText("*");
        houseDoor.setFocusCycleRoot(true);
        houseDoor.setName("MaidRoomDoor"); // NOI18N

        gameTextArea.setColumns(20);
        gameTextArea.setRows(5);
        jScrollPane1.setViewportView(gameTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(houseDoor)))
                .addContainerGap(385, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(houseDoor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:

        roomCtrl.checkCollisions(evt.getKeyCode(), roomCtrl.getItemBlockCtrls(),
                detectiveCtrl, getBound());

        repaint();
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:

        detectiveCtrl.keyReleased(evt);

        if (detectiveCtrl.getView().getBound().intersects(houseDoor.getBounds()))
        {
            mainCtrl.showPanel("House");
            detectiveCtrl.setLocationX(detectiveCtrl.getDetective().getRoomHouseLocationX());
            detectiveCtrl.setLocationY(detectiveCtrl.getDetective().getRoomHouseLocationY());
        } else if (detectiveCtrl.getView().getBound().intersects(officeLockBound)
                && !lockedAreaCtrl.getLockedArea().isUnLocked())
        {
            mainCtrl.showPanel("OfficeLock");
        }
        if (detectiveCtrl.getView().getBound().intersects(keyPasswordBound)
                && !keyPasswordCtrl.getKeyPassword().isCorrect())
        {
            mainCtrl.showPanel("HeadDogHouse");
        }

        repaint();
    }//GEN-LAST:event_formKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea gameTextArea;
    private javax.swing.JLabel houseDoor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentResized(ComponentEvent e)
    {

    }

    @Override
    public void componentMoved(ComponentEvent e)
    {

    }

    @Override
    public void componentHidden(ComponentEvent e)
    {

    }
}
