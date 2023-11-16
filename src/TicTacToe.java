import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;


public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JFrame frame2 = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel text = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean p1_turn;
    JPanel tf_panel = new JPanel();
    JPanel b2_panel = new JPanel();
    JLabel textf = new JLabel();
    JButton b1 = new JButton();
    JButton b2 = new JButton();

    public TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setTitle("X si 0");

        text.setBackground(new Color(235,171,153));
        text.setForeground(new Color(18,38,58));
        text.setFont(new Font("Times New Roman",Font.BOLD,75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("X si 0");
        text.setOpaque(true);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(255,75,10));

        for(int i=0; i<9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Calibri",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(246,222,182));
            buttons[i].addActionListener(this);
        }

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);
        title_panel.add(text);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(400,150);
        frame2.setLocationRelativeTo(null);
        frame2.getContentPane().setBackground(Color.white);
        frame2.setLayout(new BorderLayout());
        frame2.setTitle("Alegere ");

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<9; i++){
            if(e.getSource()==buttons[i]){
                if(p1_turn){
                    if(buttons[i].getText().isEmpty()){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        p1_turn=false;
                        text.setText("O alege");
                        check();
                    }
                }
                else{
                    if(buttons[i].getText().isEmpty()){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        p1_turn=true;
                        text.setText("X alege");
                        check();
                    }
                }
            }
        }
        if(e.getSource()==b1){
            frame.setVisible(false);
            frame2.setVisible(false);
            frame2.dispose();
            frame.dispose();
            new TicTacToe();
        }
        if(e.getSource()==b2){
            frame.setVisible(false);
            frame2.setVisible(false);
            frame2.dispose();
            frame.dispose();
        }
    }

    public void disablebutton(){
        for(int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
    }
    public void enablebutton(){
        for(int i=0;i<9;i++){
            buttons[i].setEnabled(true);
        }
    }

    public void firstTurn(){
        try{
            disablebutton();
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(random.nextInt(2)==0){
            enablebutton();
            p1_turn=true;
            text.setText("X alege");
        }
        else{
            enablebutton();
            p1_turn=false;
            text.setText("O alege");
        }
    }
    public void check() {

        int nr=0;
        for(int i=0;i<9;i++){
            if(!buttons[i].getText().isEmpty())
                nr++;
        }
        if(nr==9)
            egal();

        int[][] w = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        for(int i=0;i<8;i++){
            if(     (buttons[w[i][0]].getText().equals("X")) &&
                    (buttons[w[i][1]].getText().equals("X")) &&
                    (buttons[w[i][2]].getText().equals("X")) ){
                xWins(w[i][0],w[i][1],w[i][2]);
            }
            if(     (buttons[w[i][0]].getText().equals("O")) &&
                    (buttons[w[i][1]].getText().equals("O")) &&
                    (buttons[w[i][2]].getText().equals("O")) ){
                oWins(w[i][0],w[i][1],w[i][2]);
            }
        }


    }
    public void choice() {
        textf.setText("Doresti sa incepi un nou joc?");
        textf.setFont(new Font("Times New Roman",Font.BOLD,25));
        tf_panel.setBackground(Color.white);
        b2_panel.setBackground(Color.white);
        b2_panel.setLayout(new FlowLayout(FlowLayout.CENTER,70,10));
        b1.setText("Da");
        b2.setText("Nu");
        b1.setFocusable(false);
        b2.setFocusable(false);
        b1.setPreferredSize(new Dimension(120,30));
        b2.setPreferredSize(new Dimension(120,30));
        b1.addActionListener(this);
        b2.addActionListener(this);
        tf_panel.add(textf);
        b2_panel.add(b1);
        b2_panel.add(b2);
        frame2.add(tf_panel,BorderLayout.NORTH);
        frame2.add(b2_panel);
        frame2.setVisible(true);
    }
    public void xWins(int a,int b,int c){
        buttons[a].setBackground(new Color(103,140,3));
        buttons[b].setBackground(new Color(103,140,3));
        buttons[c].setBackground(new Color(103,140,3));
        buttons[a].setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor(){
                return new Color(255,75,10);
            }
        });
        buttons[b].setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor(){
                return new Color(255,75,10);
            }
        });
        buttons[c].setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor(){
                return new Color(255,75,10);
            }
        });
        disablebutton();
        text.setText("X castiga");
        choice();
        //new Timer(5_000, (e) -> { frame.setVisible(false); frame.dispose(); }).start();
    }
    public void oWins(int a,int b,int c){
        buttons[a].setBackground(new Color(103,140,3));
        buttons[b].setBackground(new Color(103,140,3));
        buttons[c].setBackground(new Color(103,140,3));
        buttons[a].setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor(){
                return new Color(255,75,10);
            }
        });
        buttons[b].setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor(){
                return new Color(255,75,10);
            }
        });
        buttons[c].setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor(){
                return new Color(255,75,10);
            }
        });
        disablebutton();
        text.setText("O castiga");
        choice();
        //new Timer(5_000, (e) -> { frame.setVisible(false); frame.dispose(); }).start();

    }
    public void egal(){
        text.setText("Jocul s-a terminat egal");
        disablebutton();
        choice();
        //new Timer(5_000, (e) -> { frame.setVisible(false); frame.dispose(); }).start();
    }

}
