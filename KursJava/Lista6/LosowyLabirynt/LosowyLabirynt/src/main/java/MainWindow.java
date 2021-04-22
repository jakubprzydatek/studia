import model.Cell;
import model.LabirynthGenerator;

import java.awt.*;
import java.awt.event.*;

public class MainWindow extends Frame {
    MainWindow(String title, int w, int h, int rows, int cols) {
        setTitle(title);

        LabirynthGenerator generator = new LabirynthGenerator();
        generator.generate();

        LabirynthControler labirynthControler = new LabirynthControler(generator.getGrid(), generator.getEntry());
        add(labirynthControler);
        Image icon = Toolkit.getDefaultToolkit().getImage("among.png");
        setIconImage(icon);

        addWindowListener(new WindowAdapter() {
            public void windowClosing( WindowEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });


        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed ( KeyEvent ev)
            {
                boolean isNotEnd = true;
                switch (ev.getKeyCode())
                {
                    case KeyEvent.VK_UP: // klawisz 'R'
                        isNotEnd = labirynthControler.handleUp();
                        break;
                    case KeyEvent.VK_DOWN: // klawisz 'G'
                        isNotEnd = labirynthControler.handleDown();
                        break;
                    case KeyEvent.VK_LEFT: // klawisz 'B'
                        isNotEnd = labirynthControler.handleLeft();
                        break;
                    case KeyEvent.VK_RIGHT:  // inne klawisze
                        isNotEnd = labirynthControler.handleRight();
                        break;
                    default:
                        break;
                }
                if(!isNotEnd)
                {
                    setVisible(false);
                    dispose();
                    new MainWindow("Among Us 2", 300, 300, 5, 10).setVisible(true);
                }
            }
        });

        // Normal end ... pack it up!
        setPreferredSize(new Dimension(Cell.SIZE*25, Cell.SIZE*25));
        pack();
    }

    public static void main(String[] a) {
        new MainWindow("Among Us 2", 300, 300, 5, 10).setVisible(true);
    }
}
