import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel text = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean p1_turn;
    public TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        text.setBackground(new Color(25,25,25));
        text.setForeground(new Color(25,255,0));
        text.setFont(new Font("Times New Roman",Font.BOLD,75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("X si 0");
        text.setOpaque(true);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));

        for(int i=0; i<9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Calibri",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);
        title_panel.add(text);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<9; i++){
            if(e.getSource()==buttons[i]){
                if(p1_turn){
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        p1_turn=false;
                        text.setText("O alege");
                        check();
                    }
                }
                else{
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        p1_turn=true;
                        text.setText("X alege");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn(){
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(random.nextInt(2)==0){
            p1_turn=true;
            text.setText("X alege");
        }
        else{
            p1_turn=false;
            text.setText("O alege");
        }
    }
    public void check() {

        int nr=0;
        for(int i=0;i<9;i++){
            if(buttons[i].getText()!="")
                nr++;
        }
        if(nr==9)
            egal();

        int[][] w = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        for(int i=0;i<8;i++){
            if(     (buttons[w[i][0]].getText().equals("X")) &&
                    (buttons[w[i][1]].getText()=="X") &&
                    (buttons[w[i][2]].getText()=="X") ){
                xWins(w[i][0],w[i][1],w[i][2]);
            }
            if(     (buttons[w[i][0]].getText()=="O") &&
                    (buttons[w[i][1]].getText()=="O") &&
                    (buttons[w[i][2]].getText()=="O") ){
                oWins(w[i][0],w[i][1],w[i][2]);
            }
        }

    }
    public void xWins(int a,int b,int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
        text.setText("X castiga");
        new Timer(5_000, (e) -> { frame.setVisible(false); frame.dispose(); }).start();
    }
    public void oWins(int a,int b,int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
        text.setText("O castiga");
        new Timer(5_000, (e) -> { frame.setVisible(false); frame.dispose(); }).start();

    }
    public void egal(){
        text.setText("Jocul s-a terminat egal");
        new Timer(5_000, (e) -> { frame.setVisible(false); frame.dispose(); }).start();
    }

}
