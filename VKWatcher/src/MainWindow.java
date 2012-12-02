/*
 *   Copyright 2012 Aleksey Litvinov litvinov.aleks@gmail.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainWindow extends JFrame {

    public static final int WIDTH = 220;
    public static final int HEIGHT = 65;

    private JPanel getUserStatusPanel;

    public MainWindow(){
        setTitle("VKWatcher");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Process data on close

        GetUserStatusPanel getUserStatusPanel = new GetUserStatusPanel();
        this.getUserStatusPanel = getUserStatusPanel;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(getUserStatusPanel, BorderLayout.NORTH);

        getContentPane().add(mainPanel);
        setVisible(true);
    }
}


class GetUserStatusPanel extends JPanel{
    private JButton getStatusButton;
    private JTextField inputIDField;

    GetUserStatusPanel(){
        Box b = Box.createHorizontalBox();
        JTextField inputIDField = new JTextField("", 8);//8 is a width of text field
        this.inputIDField = inputIDField;
        JButton getStatusButton = createButton("Get Status", 100);
        this.getStatusButton = getStatusButton;
        getStatusButton.addActionListener(new GetStatusAction());
        b.add(inputIDField);
        b.add(Box.createHorizontalGlue());
        b.add(getStatusButton);
        add(b);
    }

    private JButton createButton(String name, int width){
        JButton b = new JButton (name);
        int height = (int)b.getPreferredSize().getHeight();
        Dimension d = new Dimension (width, height);
        b.setMinimumSize(d);
        b.setMaximumSize(d);
        b.setPreferredSize(d);
        return b;
    }


    private class GetStatusAction implements ActionListener {

        //private String response;

        public void actionPerformed(ActionEvent e) {
            //get id from textField
            //evaluate user status
            //show message window
            //JOptionPane.showMessageDialog(getStatusButton, "User id" + getUserID() + " is " + VK.isOnline(VK.getResponse(getUserID())), "User Status for id" + getUserID(), JOptionPane.INFORMATION_MESSAGE);
            try {
                String response = VK.getResponse(getUserID());
                boolean isOnline = VK.isOnline(response);
                String status = VK.getOnlineStatus(isOnline);
                JOptionPane.showMessageDialog(getStatusButton, "User id" + getUserID() + " is " + status, "User Status for id" + getUserID(), JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }

    public String getUserID(){
        return inputIDField.getText();
    }

}
