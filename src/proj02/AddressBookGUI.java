package proj02;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddressBookGUI
{
    private JPanel window01;
    private JPanel window02;
    private JScrollPane scrollPanel;
    private JButton displayButton;
    private JButton newButton;
    private JButton removeButton;
    private JPanel box;

    public static void main(String args[]){
        //setBounds
        //though i change x,y,w,h output is the same
        AddressBookGUI inst = new AddressBookGUI();
        JFrame frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(inst.windowBox());
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel windowBox()
    {
        box = new JPanel();
        box.setLayout(new BorderLayout());
        box.add(firstWindow(), BorderLayout.CENTER);
        box.add(secondWindow(), BorderLayout.SOUTH);

        return box;

    }



    private JPanel firstWindow()
    {
        window01 = new JPanel();
        scrollPanel = new JScrollPane();
        scrollPanel.setPreferredSize(new Dimension(200,100));
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        window01.add(scrollPanel, BorderLayout.NORTH);

        return window01;
    }

    private JPanel secondWindow()
    {
        window02 = new JPanel();
        displayButton = new JButton("Display");
        newButton = new JButton("New");
        removeButton = new JButton("Remove");
        AddressBook ab = new AddressBook();
        AddressEntry entry = new AddressEntry();

        displayButton.setPreferredSize(new Dimension(100,30));
        newButton.setPreferredSize(new Dimension(100,30));
        removeButton.setPreferredSize(new Dimension(100,30));

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ab.list();
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ab.add(entry);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ab.remove("Ju");
            }
        });
        window02.add(displayButton);
        window02.add(newButton);
        window02.add(removeButton);
        return window02;
    }


}
