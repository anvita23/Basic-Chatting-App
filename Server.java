package chattingapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInput;
import java.io.*;
import java.net.*;

public class Server extends JFrame implements ActionListener {

    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    Server() {
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chattingapp/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); // As image cannot be directly used in JLabel
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5, 17, 30, 30);
        p1.add(l1);
        l1.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chattingapp/icons/1.png"));
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5); // As image cannot be directly used in Jlabel
        JLabel l2 = new JLabel(i6);
        l2.setBounds(40, 5, 60, 60);
        p1.add(l2);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chattingapp/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8); // As image cannot be directly used in Jlabel
        JLabel l10 = new JLabel(i9);
        l10.setBounds(290, 20, 30, 30);
        p1.add(l10);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chattingapp/icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11); // As image cannot be directly used in Jlabel
        JLabel l11 = new JLabel(i12);
        l11.setBounds(350, 20, 35, 30);
        p1.add(l11);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chattingapp/icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14); // As image cannot be directly used in Jlabel
        JLabel l12 = new JLabel(i15);
        l12.setBounds(410, 20, 13, 25);
        p1.add(l12);


        JLabel l3 = new JLabel("Alex");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 100, 18);
        p1.add(l3);

        JLabel l4 = new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110, 35, 100, 20);
        p1.add(l4);

        a1 = new JTextArea();
        a1.setBounds(5, 75, 440, 570);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        a1.setEditable(false);
        a1.setLineWrap(true); //for lengthier sentences
        a1.setWrapStyleWord(true); //for lengthier sentences
        add(a1);

        t1 = new JTextField();
        t1.setBounds(5, 655, 310, 40);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        b1 = new JButton("Send");
        b1.setBackground(new Color(7, 94, 84));
        b1.setBounds(320, 655, 123, 40);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);
        add(b1);

        getContentPane().setBackground(Color.white);
        setLayout(null);
        setSize(450, 700);
        setLocation(400, 50);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent a) {
        try{
            String out = t1.getText();
            a1.setText(a1.getText() + "\n\t\t\t" + out);
            dout.writeUTF(out);
            t1.setText("");
        }
        catch(Exception e){}
    }

    public static void main(String[] args) {
        new Server().setVisible(true);
        String msginput = "";
        try {
            skt = new ServerSocket(6001);

            s = skt.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            msginput = din.readUTF();
            a1.setText(a1.getText() + "\n" + msginput);
            skt.close();
            s.close();


        } catch (Exception e) {}
    }
}

