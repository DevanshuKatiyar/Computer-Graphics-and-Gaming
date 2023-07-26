package TicTacToe;
import java.awt.*;// backgroud or visualization
import java.awt.event.*;// actionlistner //abstract window tool
import javax.swing.*;// jframe,jlabel,,lpanel,jbutton;<-graphics i.e swibg


public class TicTacToe extends JFrame implements ActionListener 
{

    private JButton[][] buttons;
    private JLabel playerLabel;
    private int curentplayer;
    private int currentvalue;
    private int[][] board;
    private int boardsize;
    private int[] scores;
    private JLabel[] scorelabels;
    private JPanel scorepanel;
    private int level;

    public TicTacToe(int boardsize,int level) 
    {
        this.boardsize = boardsize;
        currentvalue = (int) (Math.random()*6)+1;
       // System.out.print(currentvalue);
        


        board = new int[boardsize][boardsize];
        scores = new int[2];

        JPanel boardPanel = new JPanel(new GridLayout(boardsize, boardsize));
        buttons = new JButton[boardsize][boardsize];

        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].addActionListener(this);
                buttons[row][col].setBackground(Color.LIGHT_GRAY);
                boardPanel.add(buttons[row][col]);
            }
        }

    
        playerLabel = new JLabel ("Player " + curentplayer + "'s turn");
        playerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scorepanel = new JPanel(new GridLayout(2, 2));
        scorelabels = new JLabel[2];

        for (int player = 0; player < 2; player++) {
            scorelabels[player] = new JLabel("Player " + (player + 1) + ": " + scores[player]);
            scorelabels[player].setFont(new Font("Arial", Font.BOLD, 16));
            scorepanel.add(scorelabels[player]);
        }

        getContentPane().add(boardPanel, BorderLayout.CENTER);
        getContentPane().add(playerLabel, BorderLayout.NORTH);
        getContentPane().add(scorepanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1240, 800);
        
        setVisible(true);
        if(boardsize==3)
        {
           call1(); 
        }
        else{
call();
        }
        if(currentvalue%2==0)
        {
            JOptionPane.showMessageDialog(this, currentvalue+" It's AN EVEN NUMBER X' TURN");

            curentplayer = 1;

        }
        else{
            JOptionPane.showMessageDialog(this, currentvalue+" It's AN ODD NUMBER O' TURN");

            curentplayer = 2;

        }
        

    }

    public void actionPerformed(ActionEvent event) 
    {
        JButton click = (JButton) event.getSource();

        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) 
            {

                if (click == buttons[row][col]) {
                    if (board[row][col] == 0) {
                        board[row][col] = curentplayer;
                        if (curentplayer == 1) {
                            buttons[row][col].setFont(new Font("Arial", Font.BOLD, 50));
                            buttons[row][col].setText("X");
                            playerLabel.setText("Player 2's turn");
                            

                        
                

                        } else {
                            buttons[row][col].setFont(new Font("Arial", Font.BOLD, 50));
                            buttons[row][col].setText("O");
                            playerLabel.setText("Player 1's turn");
                            //curentplayer = 1;

                            

                        }
                        checkForWin();

        if(!checkForWin())
        {

        if(curentplayer == 1)
        {
            curentplayer=2;
        }
        else{
            curentplayer = 1;

        }
    }  //checkForWin();
                    }
                }
            }
        }
        
    }
    private boolean checkForWin()
    {
        boolean flag=false;

        // rows
        for (int row = 0; row < boardsize; row++) {
            int count = 0;
            for (int col = 0; col < boardsize; col++) {
                if (board[row][col] == curentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == boardsize) {
                    handleWin();
                    flag=true;

                }
            }
           
        }

        // columns
        for (int col = 0; col < boardsize; col++) {
            int count = 0;
            for (int row = 0; row < boardsize; row++) {
                if (board[row][col] == curentplayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == boardsize) {
                    handleWin();
                    flag=true;

                    
                }
            }
        }
    //digonal1
        int count1=0;
        for( int i=0;i<boardsize;i++)
        {
            if (board[i][i] == curentplayer) {
                count1++;
            } else {
                count1 = 0;
            }
            if (count1 == boardsize) {
                handleWin();
                flag=true;

                
            }
        }
    
        // digonal2
        for (int row = boardsize - 1; row < boardsize; row++) {
            for (int col = 0; col < 1; col++) {
                int count = 0;
                for (int i = 0; i < boardsize; i++) {
                    if (board[row - i][col + i] == curentplayer) {
                        count++;
                    } else {
                        count = 0;
                    }
                    if (count == boardsize) {
                        handleWin();
                        flag=true;
                        
                    }
                }
            }
        }
    
        // Check for tie
        boolean tie = true;
        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) {
                if (board[row][col] == 0) {
                    tie = false;
                    
                }
            }
        }
        if (tie) {
            handleTie();
            flag= false;
        }

 return flag;


    }
    
    private void handleWin() {
        scores[curentplayer - 1]++;
        //System.out.print(scores[curentplayer-1]);
       // System.out.print(scores[curentplayer]);
       for (int row = 0; row < boardsize; row++) {
        for (int col = 0; col < boardsize; col++) {
            if (board[row][col] == curentplayer) {
                buttons[row][col].setBackground(Color.GREEN); // Set the background color to green
            }
        }
    }


        scorelabels[curentplayer - 1].setText("Player " + curentplayer + ": " + scores[curentplayer - 1]);
        JOptionPane.showMessageDialog(this, "Player " + curentplayer + " wins!");
       // resetGame();
        if(scores[curentplayer-1] >1)
        {
            
            playerLabel.setText("player "+curentplayer+" wins the best of 3!!!!!!");
            playerLabel.setFont(new Font("Arial", Font.BOLD, 50));

            JOptionPane.showMessageDialog(this, curentplayer+" WINS THE BEST OF 3 ROUND!!!");
            
            //System.out.print(scores[curentplayer-1]);

           setVisible(false);

            boardsize++;
            level=level+1;
          
            new TicTacToe(boardsize,level);
        
        }
        else{
            resetGame();
        }

    }
  
    private void call1()
    {
        
        playerLabel.setText("WELCOME TO LEVEL 1");
            playerLabel.setFont(new Font("Arial", Font.BOLD, 50));
    
            JOptionPane.showMessageDialog(this,"w e l c o m e");
            
    }
    private void call()
    {
        playerLabel.setText("WELCOME TO NEXT LEVEL");
            playerLabel.setFont(new Font("Arial", Font.BOLD, 50));
    
           JOptionPane.showMessageDialog(this,"level incremented ");
            
    }
    private void handleTie() {
        JOptionPane.showMessageDialog(this, "It's a tie!");
        resetGame();
    }
    
    private void resetGame() {
        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) {
                board[row][col] = 0;
                buttons[row][col].setText("");
                buttons[row][col].setBackground(Color.LIGHT_GRAY);
            }
        }
        currentvalue = (int) (Math.random()*6)+1;
        System.out.print(currentvalue);
        if(currentvalue%2==0)
        {
            JOptionPane.showMessageDialog(this, currentvalue+" It's AN EVEN NUMBER X' TURN");
            curentplayer = 2;

        }
        else{
            JOptionPane.showMessageDialog(this, currentvalue +" It's AN ODD NUMBER O' TURN");
            curentplayer = 1;

        }
        playerLabel.setText("Player " + curentplayer + "'s turn");


    }
public static void main(String[] args) {
    new TicTacToe(3,1);
  // new TicTacToe1(4);
  // new TicTacToe1(5);
}
}