package rpncalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RPNCalculator extends JFrame {

    JPanel numPan, methodPan, displayPan, lblPan, headPan, sidePan, entPan;
    //numPan
    JButton[] btnNum;//0-9
    JButton btnDec, btnPM;//decimal,plus minus
    //displayPan
    JTextField[] tfDis;
    //methodPan
    JButton btnXY, btnClr, btnSqr, btnAdd, btnSub, btnMult, btnDiv, btnEnt;
    //lblPan
    double val[] = new double[4];

    final int ENTER = 99;
    final int ADD = 98;
    final int SUB = 97;
    final int MULT = 96;
    final int DIV = 95;
    final int XY = 94;
    final int CLR = 93;
    final int SQR = 92;
    final int DEC = 91;
    final int PM = 90;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new RPNCalculator();
    }

    RPNCalculator() {
        initialize();
        setCommand();
        validation();
    }

    void initialize() {
        // TODO Auto-generated method stub
        //setting up frame
        this.setTitle("RPN Calculator");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setting up panel
        this.setLayout(new BorderLayout());

        myActionListener al = new myActionListener();
        myKeyListener kl = new myKeyListener();//doesnt work

        this.addKeyListener(kl);
        numPan = new JPanel();
        numPan.setLayout(new GridLayout(4, 3));
        btnNum = new JButton[10];
        for (int i = 2; i >= 0; i--) {
            for (int j = 1; j <= 3; j++) {
                btnNum[i] = new JButton("" + (i * 3 + j));
                btnNum[i].addActionListener(al);
                numPan.add(btnNum[i]);
            }
        }
        btnNum[0] = new JButton("0");
        btnDec = new JButton(".");
        btnPM = new JButton("\u00B1");
        btnNum[0].addActionListener(al);
        btnDec.addActionListener(al);
        btnPM.addActionListener(al);
        numPan.add(btnNum[0]);
        numPan.add(btnDec);
        numPan.add(btnPM);

        methodPan = new JPanel();
        methodPan.setLayout(new GridLayout(4, 2));
        btnXY = new JButton("X \u2194 Y");
        btnClr = new JButton("CE");
        btnSqr = new JButton("\u221A");
        btnAdd = new JButton("+");
        btnSub = new JButton("-");
        btnMult = new JButton("\u00D7");
        btnDiv = new JButton("\u00F7");
        btnEnt = new JButton("Enter");

        btnXY.addActionListener(al);
        btnClr.addActionListener(al);
        btnSqr.addActionListener(al);
        btnAdd.addActionListener(al);
        btnSub.addActionListener(al);
        btnMult.addActionListener(al);
        btnDiv.addActionListener(al);
        btnEnt.addActionListener(al);
        methodPan.add(btnXY);
        methodPan.add(btnClr);
        methodPan.add(new JLabel());
        methodPan.add(btnSqr);
        methodPan.add(btnAdd);
        methodPan.add(btnSub);
        methodPan.add(btnMult);
        methodPan.add(btnDiv);
        //methodPan.add(new JLabel());
        //methodPan.add(btnEnt);
        entPan = new JPanel();
        entPan.setLayout(new GridLayout(1, 1));
        entPan.add(btnEnt);

        sidePan = new JPanel();
        sidePan.setLayout(new BoxLayout(sidePan, BoxLayout.Y_AXIS));
        sidePan.add(methodPan);
        sidePan.add(entPan);

        lblPan = new JPanel();
        lblPan.setLayout(new GridLayout(4, 1));
        lblPan.add(new JLabel("T"));
        lblPan.add(new JLabel("Z"));
        lblPan.add(new JLabel("Y"));
        lblPan.add(new JLabel("X"));

        displayPan = new JPanel();
        displayPan.setLayout(new BoxLayout(displayPan, BoxLayout.Y_AXIS));
        tfDis = new JTextField[4];

        for (int i = 0; i < 4; i++) {
            tfDis[i] = new JTextField("0");
            tfDis[i].setBackground(Color.WHITE);
            tfDis[i].setOpaque(true);
            tfDis[i].setEditable(false);
            tfDis[i].setHorizontalAlignment(SwingConstants.RIGHT);
            displayPan.add(tfDis[i]);
        }

        headPan = new JPanel();
        headPan.setLayout(new BoxLayout(headPan, BoxLayout.X_AXIS));
        headPan.add(lblPan);
        headPan.add(displayPan);
    }

    void validation() {
        // TODO Auto-generated method stub

        this.add(headPan, BorderLayout.NORTH);
        this.add(numPan, BorderLayout.CENTER);
        this.add(sidePan, BorderLayout.EAST);

        this.validate();
        this.setVisible(true);
    }

    void setCommand() {
        btnEnt.setActionCommand("" + ENTER);
        btnAdd.setActionCommand("" + ADD);
        btnSub.setActionCommand("" + SUB);
        btnMult.setActionCommand("" + MULT);
        btnDiv.setActionCommand("" + DIV);
        btnClr.setActionCommand("" + CLR);
        btnSqr.setActionCommand("" + SQR);
        btnXY.setActionCommand("" + XY);
        btnPM.setActionCommand("" + PM);
        btnDec.setActionCommand("" + DEC);
    }

    private class myActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println(e.getActionCommand());
            int command = Integer.parseInt(e.getActionCommand());

            if (command >= 0 && command <= 9) {
                if(tfDis[3].getText().charAt(0)=='0')tfDis[3].setText("");
                tfDis[3].setText(tfDis[3].getText()+command);
                val[3]=Double.parseDouble(tfDis[3].getText());
            } else if (command == DEC) {
                tfDis[3].setText(tfDis[3].getText()+".");
            } else if (command == PM) {
                val[3] = -val[3];
                tfDis[3].setText(val[3] + "");
            } else if (command == ENTER) {
                if (val[0] == 0) {
                    for (int i = 0; i < 3; i++) {
                        val[i] = val[i + 1];
                        tfDis[i].setText(val[i] + "");
                    }
                    val[3] = 0;
                }
            } else if (command == CLR) {
                for (int i = 3; i > 0; i--) {
                    val[i] = val[i - 1];
                    tfDis[i].setText(val[i] + "");
                }
                val[0] = 0;
            } else if (command == XY) {
                double temp;
                temp = val[2];
                val[2] = val[3];
                val[3] = temp;
                tfDis[2].setText("" + val[2]);
                tfDis[3].setText("" + val[3]);
            } else if (command == ADD) {
                val[3] += val[2];
                calc();
            } else if (command == SUB) {
                val[3] = val[2] - val[3];
                calc();
            } else if (command == MULT) {
                val[3] *= val[2];
                calc();
            } else if (command == DIV) {
                val[3] = val[2] / val[3];
                calc();
            } else if (command == SQR) {
                val[3] = Math.sqrt(val[3]);
                tfDis[3].setText("" + val[3]);
            }

            checkTF();

        }

        void checkTF() {
            String temp;
            for (int i = 0; i < 4; i++) {
                if (val[i] == 0) {
                    tfDis[i].setText("0");
                } /*else {
                    temp = tfDis[i].getText();
                    if (temp.charAt(temp.length()-1) == '0'
                            && temp.charAt(temp.length() - 2) == '.') {
                        temp = temp.substring(0, temp.length() - 2);
                        tfDis[i].setText("" + temp);
                    }
                }*/
            }
        }

        void calc() {
            tfDis[3].setText(val[3] + "");
            for (int i = 2; i > 0; i--) {
                val[i] = val[i - 1];
                tfDis[i].setText(val[i] + "");
            }
            val[0] = 0;
            tfDis[0].setText("" + val[0]);
        }
        /*
         void resetDec() {
         power = 0;
         decimal = 0;
         dec = false;
         }
        
         double getDecimal(int command){
         decimal = decimal*10+command;
         power++;
         return decimal/(Math.pow(10,power));
         }*/
    }

    //doesnt work
    private class myKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println(e.getKeyCode());
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

}
