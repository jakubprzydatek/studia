package GUI;

import GameEngine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawPawns extends JPanel {
    Graphics g2d;
    protected Board board;
    protected Pawn currentPawn = null;
    protected int currentX;
    protected int currentY;
    protected Color pColor;
    protected Color bgColor;
    protected Color pfillColor;
    protected String gameType;
    JPopupMenu contextMenu;
    JMenuItem goraPopup, dolPopup, lewoPopup, prawoPopup;
    JPanel gameState;
    JLabel gameStateStatus;

    public DrawPawns(Color bgColor, Color pColor, Color pfillColor, String gameType){
        this.bgColor = bgColor;
        this.pColor = pColor;
        this.pfillColor = pfillColor;
        this.setBackground(bgColor);
        this.gameType=gameType;
        printContextMenu();
        setPreferredSize(new Dimension(650, 700));
        if(gameType.equals("gb")){
            board = new BritishBoard();
        }else{
            board = new EuropeanBoard();
        }
        board.getPawn(3, 3).setBeaten(true);
        gameState = new JPanel();
        gameStateStatus = new JLabel("Wykonaj pierwszy ruch");
        gameState.add(gameStateStatus);
        gameState.setLocation(300, 1);
        add(gameState);

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2d = g;
        g2d.setColor(pColor);
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                switch (gameType){
                    case "eu":
                        if(!PositionOnBoard.forbiddenPositionsOnEuropean.contains(new PositionOnBoard(j, i)))
                        {
                            if(!board.getPawn(j, i).isBeaten()){
                                g2d.fillOval((j+1)*70, (i+1)*70, 70, 70);
                                g2d.setColor(pfillColor);
                                g2d.fillOval((j+1)*70+10, (i+1)*70+1, 50, 68);
                            }

                            g2d.setColor(pColor);
                            g2d.drawRect((j+1)*70, (i+1)*70, 70, 70);
                        }
                        break;
                    case "gb":
                        if(!PositionOnBoard.forbiddenPositionsOnBritish.contains(new PositionOnBoard(j, i)))
                        {
                            if(!board.getPawn(j, i).isBeaten()){
                                g2d.fillOval((j+1)*70, (i+1)*70, 70, 70);
                                g2d.setColor(pfillColor);
                                g2d.fillOval((j+1)*70+10, (i+1)*70+1, 50, 68);
                            }

                            g2d.setColor(pColor);
                            g2d.drawRect((j+1)*70, (i+1)*70, 70, 70);
                        }
                        break;
                }
            }
        }

    }

    public void handleSelect(int j, int i){
        if(!PositionOnBoard.checkIfLegalPosition((j/70)-1, (i/70)-1, gameType)) return;
        if(board.getPawn((j/70)-1, (i/70)-1).isBeaten()) return;
        Graphics graphics = getGraphics();
        if(currentPawn!=null){
            if(currentPawn.isBeaten()){
                graphics.setColor(bgColor);
            }else{
                graphics.setColor(pfillColor);
            }
            currentPawn.setActive(false);
            graphics.fillOval((currentX+1)*70+10, (currentY+1)*70, 50, 68);
        }
        currentX = (j/70)-1;
        currentY = (i/70)-1;
        currentPawn = board.getPawn(currentX, currentY);
        currentPawn.setActive(true);
        graphics.setColor(Color.white);
        graphics.fillOval((currentX+1)*70+10, (currentY+1)*70, 50, 68);
        System.out.println(board);
        System.out.println("Dla tablicy: "+board.countAvailableBoardMoves(gameType));
        System.out.println("Dla pionka: "+board.countAvailablePawnMoves(currentPawn, gameType));
    }

    public void handleMoveDown(PositionOnBoard positionOnBoard){
        if (currentPawn==null) return;
        if(PositionOnBoard.checkIfLegalPosition(positionOnBoard.getI(), positionOnBoard.getJ()+2, gameType)){
            if(!board.getPawn(currentX, currentY+1).isBeaten() && board.getPawn(currentX, currentY+2).isBeaten()){
                erasePawn(currentX, currentY, currentPawn);
                erasePawn(currentX, currentY+1, board.getPawn(currentX, currentY+1));
                setNewPawn(currentX, currentY+2);
                gameStateStatus.setText(generateGameStatusText());
            }
        }
    }

    public void handleMoveUp(PositionOnBoard positionOnBoard){
        if (currentPawn==null) return;
        if(PositionOnBoard.checkIfLegalPosition(positionOnBoard.getI(), positionOnBoard.getJ()-2, gameType)){
            if(!board.getPawn(currentX, currentY-1).isBeaten() && board.getPawn(currentX, currentY-2).isBeaten()){
                erasePawn(currentX, currentY, currentPawn);
                erasePawn(currentX, currentY-1, board.getPawn(currentX, currentY-1));
                setNewPawn(currentX, currentY-2);
                gameStateStatus.setText(generateGameStatusText());
            }
        }
    }

    public void handleMoveLeft(PositionOnBoard positionOnBoard){
        if (currentPawn==null) return;
        if(PositionOnBoard.checkIfLegalPosition(positionOnBoard.getI()-2, positionOnBoard.getJ(), gameType)){
            if(!board.getPawn(currentX-1, currentY).isBeaten() && board.getPawn(currentX-2, currentY).isBeaten()){
                erasePawn(currentX, currentY, currentPawn);
                erasePawn(currentX-1, currentY, board.getPawn(currentX-1, currentY));
                setNewPawn(currentX-2, currentY);
                gameStateStatus.setText(generateGameStatusText());
            }
        }
    }

    public void handleMoveRight(PositionOnBoard positionOnBoard){
        if (currentPawn==null) return;
        if(PositionOnBoard.checkIfLegalPosition(positionOnBoard.getI()+2, positionOnBoard.getJ(), gameType)){
            if(!board.getPawn(currentX+1, currentY).isBeaten() && board.getPawn(currentX+2, currentY).isBeaten()){
                erasePawn(currentX, currentY, currentPawn);
                erasePawn(currentX+1, currentY, board.getPawn(currentX+1, currentY));
                setNewPawn(currentX+2, currentY);
                gameStateStatus.setText(generateGameStatusText());
            }
        }
    }

    private void erasePawn(int x, int y, Pawn currPawn){
        Graphics graphics = getGraphics();
        graphics.setColor(bgColor);
        currPawn.setActive(false);
        currPawn.setBeaten(true);
        graphics.fillOval((x+1)*70, (y+1)*70, 70, 70);
        currPawn = null;
    }
    private void setNewPawn(int x, int y){
        Graphics graphics = getGraphics();
        graphics.setColor(pColor);
        graphics.fillOval((x+1)*70, (y+1)*70, 70, 70);
        graphics.setColor(pfillColor);
        graphics.fillOval((x+1)*70+10, (y+1)*70+1, 50, 68);
        board.getPawn(x,y).setBeaten(false);
    }

    private void printContextMenu(){
        contextMenu = new JPopupMenu();
        goraPopup = new JMenuItem("Do góry");
        goraPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPawn!=null) handleMoveUp(currentPawn.getPosition());
                System.out.println(board);
            }
        });
        dolPopup = new JMenuItem("W dół");
        dolPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPawn!=null) handleMoveDown(currentPawn.getPosition());
                System.out.println(board);
            }
        });
        lewoPopup = new JMenuItem("W lewo");
        lewoPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPawn!=null) handleMoveLeft(currentPawn.getPosition());
            }
        });
        prawoPopup = new JMenuItem("W prawo");
        prawoPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPawn!=null) handleMoveRight(currentPawn.getPosition());
            }
        });
        contextMenu.add(goraPopup);
        contextMenu.add(dolPopup);
        contextMenu.add(lewoPopup);
        contextMenu.add(prawoPopup);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });
    }

    void showPopup(MouseEvent me) {
        if(me.isPopupTrigger())
            contextMenu.show(me.getComponent(), me.getX(), me.getY());
    }

    public Pawn getCurrentPawn() {
        return currentPawn;
    }

    public String generateGameStatusText(){
        if(board.countAvailableBoardMoves(gameType) > 0){
            return "Ilość dostępnych ruchów: "+board.countAvailableBoardMoves(gameType);
        }else{
            return "Gra zakończona!";
        }
    }
}
