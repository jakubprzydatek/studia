package GUI;

import GUI.utils.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame{
    JFrame f;
    JMenuBar mb;
    JMenu menuGra, menuRuchy, menuUstawienia, submenuTrybGry, submenuKolorPlanszy, submenuKolorPionow, menuPomoc;
    JMenuItem newGame, endGame, goraSM, dolSM, lewoSM, prawoSM, aboutGame, aboutApp;
    JDialog aboutGameDialog, aboutAppDialog;
    JRadioButtonMenuItem brytyjskiTG, europejskiTG, BColorBlack, BColorGray, PColorBlue, PColorGreen;
    JLabel stanGry;
    boolean isBoardVisible = false;
    String gameMode, gameText;
    Color boardColor, pawnColor, pawnFillColor;
    DrawPawns board;
    public Frame(){
        this.gameMode = "gb";
        this.gameText = "Gra w toku";
        this.boardColor = Color.BLACK;
        this.pawnColor = Color.BLUE;
        this.pawnFillColor = Color.red;
        f = new JFrame();
        printMenuBar();
        printBoard(Color.black, isBoardVisible);
        f.setSize(900, 900);
        f.setLayout(null);
        f.setVisible(true);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graMenuListeners();
        ustawieniaMenuListeners();
        ruchyMenuListeners();
        pomocMenuListeners();
    }

    private void printMenuBar(){
        mb = new JMenuBar();
        menuGra = new JMenu("Gra");
        newGame=new JMenuItem("Nowa");
        endGame=new JMenuItem("Koniec");
        menuGra.add(newGame); menuGra.addSeparator();menuGra.add(endGame);

        menuRuchy = new JMenu("Ruchy");
        goraSM =new JMenuItem("Do góry");
        dolSM =new JMenuItem("W dół");
        lewoSM=new JMenuItem("W lewo");
        prawoSM=new JMenuItem("W prawo");
        menuRuchy.add(goraSM); menuRuchy.add(dolSM); menuRuchy.add(lewoSM); menuRuchy.add(prawoSM);

        menuUstawienia = new JMenu("Ustawienia");
        submenuTrybGry = new JMenu("Tryb gry");
        brytyjskiTG = new JRadioButtonMenuItem("Angielski");
        brytyjskiTG.setSelected(true);
        europejskiTG = new JRadioButtonMenuItem("Europejski");
        submenuTrybGry.add(brytyjskiTG); submenuTrybGry.add(europejskiTG);

        submenuKolorPlanszy = new JMenu("Kolor planszy");
        BColorBlack = new JRadioButtonMenuItem("Czarny");
        BColorBlack.setSelected(true);
        BColorGray = new JRadioButtonMenuItem("Szary");
        submenuKolorPlanszy.add(BColorBlack); submenuKolorPlanszy.add(BColorGray);

        submenuKolorPionow = new JMenu("Kolor pionów");
        PColorBlue = new JRadioButtonMenuItem("Niebiesko-czerwony");
        PColorBlue.setSelected(true);
        PColorGreen = new JRadioButtonMenuItem("Zielono-żółty");
        submenuKolorPionow.add(PColorBlue); submenuKolorPionow.add(PColorGreen);

        menuUstawienia.add(submenuTrybGry); menuUstawienia.add(submenuKolorPlanszy); menuUstawienia.add(submenuKolorPionow);

        menuPomoc = new JMenu("Pomoc");
        aboutApp = new JMenuItem("O aplikacji");
        aboutGame = new JMenuItem("O grze");
        aboutGameDialog = createAGDialog(f);
        aboutAppDialog = createAADialog(f);

        menuPomoc.add(aboutApp); menuPomoc.add(aboutGame);
        menuPomoc.setHorizontalAlignment(JMenu.RIGHT);
        mb.add(menuGra);
        mb.add(menuRuchy);
        mb.add(menuUstawienia);
        mb.add(Box.createHorizontalGlue());
        mb.add(menuPomoc);
        f.setJMenuBar(mb);
    }


    private void graMenuListeners(){
        newGame.addActionListener(ev -> {
            isBoardVisible = true;
            printBoard(Color.black, isBoardVisible);
            f.revalidate();
            f.repaint();
        });
        newGame.setMnemonic(KeyEvent.VK_N);
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
        endGame.addActionListener(e -> System.exit(0));
        endGame.setMnemonic(KeyEvent.VK_E);
        endGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
    }

    private void ustawieniaMenuListeners(){
        brytyjskiTG.addActionListener(e -> {
                europejskiTG.setSelected(false);
                gameMode="gb";
        });

        europejskiTG.addActionListener(e -> {
                brytyjskiTG.setSelected(false);
                gameMode="eu";
        });

        BColorBlack.addActionListener(e -> {
            BColorGray.setSelected(false);
            boardColor = Color.BLACK;
        });

        BColorGray.addActionListener(e -> {
            BColorBlack.setSelected(false);
            boardColor = Color.gray;
        });

        PColorBlue.addActionListener(e -> {
            PColorGreen.setSelected(false);
            pawnColor = Color.BLUE;
            pawnFillColor = Color.red;
        });

        PColorGreen.addActionListener(e -> {
            PColorBlue.setSelected(false);
            pawnColor = Color.green;
            pawnFillColor = Color.yellow;
        });


    }

    private void ruchyMenuListeners(){
        goraSM.addActionListener(e -> {
            System.out.println("Listener działa");
            if(board.getCurrentPawn()!=null) board.handleMoveUp(board.getCurrentPawn().getPosition());
            System.out.println(board);
        });
        goraSM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_MASK));
        dolSM.addActionListener(e -> {
            if(board.getCurrentPawn()!=null) board.handleMoveDown(board.getCurrentPawn().getPosition());
            System.out.println(board);
        });
        dolSM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_MASK));
        lewoSM.addActionListener(e -> {
            if(board.getCurrentPawn()!=null) board.handleMoveLeft(board.getCurrentPawn().getPosition());
        });
        lewoSM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.CTRL_MASK));
        prawoSM.addActionListener(e -> {
            if(board.getCurrentPawn()!=null) board.handleMoveRight(board.getCurrentPawn().getPosition());
        });
        prawoSM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.CTRL_MASK));
    }

    private void pomocMenuListeners(){
        aboutGame.addActionListener(e -> aboutGameDialog.setVisible(true));

        aboutApp.addActionListener(e -> aboutAppDialog.setVisible(true));
    }

    private void printBoard(Color color, boolean isVisible){
       board = new DrawPawns(boardColor, pawnColor, pawnFillColor, gameMode);
       board.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               if(SwingUtilities.isLeftMouseButton(e)){
                   int x=e.getX();
                   int y=e.getY();
                   board.handleSelect(x, y);
               }
           }
       });
       board.setVisible(isVisible);
       //stanGry = new JLabel(gameText);
       //stanGry.setBounds(500, 1000, 100, 100);
       //board.add(stanGry);
       f.setContentPane(board);

    }


    private static JDialog createAGDialog(final JFrame frame){
        final JDialog modelDialog = new JDialog(frame, "Jak grać?",
                Dialog.ModalityType.DOCUMENT_MODAL);
        modelDialog.setBounds(132, 132, 500, 200);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        dialogContainer.add(new JTextArea(Strings.instruction)
                , BorderLayout.CENTER);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> modelDialog.setVisible(false));

        panel1.add(okButton);
        dialogContainer.add(panel1, BorderLayout.SOUTH);

        return modelDialog;
    }

    private static JDialog createAADialog(final JFrame frame){
        final JDialog modelDialog = new JDialog(frame, "Informacje",
                Dialog.ModalityType.DOCUMENT_MODAL);
        modelDialog.setBounds(132, 132, 250, 200);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        dialogContainer.add(new JTextArea(Strings.aboutAuthor)
                , BorderLayout.CENTER);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> modelDialog.setVisible(false));

        panel1.add(okButton);
        dialogContainer.add(panel1, BorderLayout.SOUTH);

        return modelDialog;
    }

}
