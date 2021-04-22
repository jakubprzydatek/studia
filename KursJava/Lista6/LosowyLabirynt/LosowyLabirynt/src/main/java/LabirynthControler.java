import model.Cell;
import model.Vertex;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LabirynthControler extends Canvas {
    private final Cell[][] cells;
    private Cell explorer;

    public LabirynthControler(Cell[][] cells, Cell cell ) {
        this.cells = cells;
        explorer = cell;
    }

    public BufferedImage getImage(){
        BufferedImage image=null;
        File file = new File("among.png");
        try{
            image = ImageIO.read(file);
        }catch (IOException e){
            System.out.println("Nie udało się wczytać obrazka");
            System.out.println(System.getProperty("user.dir"));
        }
        Image tmp = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public void paint(Graphics g) {
        for (int j = 0; j < cells.length; j++) {
            for (int k = 0; k < cells.length; k++) {
                Vertex vertex = cells[j][k].getUpperLeftVertex();
                if(cells[j][k].isExit()){
                    g.setColor(Color.GRAY);
                    g.fillRect(vertex.getX(), vertex.getY(), Cell.SIZE, Cell.SIZE);
                }else if (cells[j][k].isPath()){
                    g.setColor(Color.GREEN);
                    g.fillRect(vertex.getX(), vertex.getY(), Cell.SIZE, Cell.SIZE);
                }else
                if (cells[j][k].isBorder() ){
                    g.setColor(Color.BLACK);
                    g.fillRect(vertex.getX(), vertex.getY(), Cell.SIZE, Cell.SIZE);
                }
                else{
                    g.setColor(Color.YELLOW);
                    g.fillRect(vertex.getX(), vertex.getY(), Cell.SIZE, Cell.SIZE);
                }
            }
        }
        g.setColor(Color.RED);
        g.fillRect(explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), Cell.SIZE, Cell.SIZE);
        g.drawImage(getImage(), explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), null);
    }

    public boolean handleUp(){
        if(explorer.getPositionInGrid().getY()-1 != -1 && cells[explorer.getPositionInGrid().getX()][explorer.getPositionInGrid().getY()-1].isPath()){
            Graphics graphics = getGraphics();
            graphics.setColor(Color.GREEN);
            graphics.fillRect(explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), Cell.SIZE, Cell.SIZE);
            explorer = cells[explorer.getPositionInGrid().getX()][explorer.getPositionInGrid().getY()-1];
            graphics.setColor(Color.RED);
            graphics.fillRect(explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), Cell.SIZE, Cell.SIZE);
            graphics.drawImage(getImage(), explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), null);
        }
        if(explorer.isExit()){
            System.out.println("Koniec");
            return false;
        }
        return true;
    }
    public boolean handleDown(){
        if(explorer.getPositionInGrid().getY()+1 != cells.length && cells[explorer.getPositionInGrid().getX()][explorer.getPositionInGrid().getY()+1].isPath()){
            Graphics graphics = getGraphics();
            graphics.setColor(Color.GREEN);
            graphics.fillRect(explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), Cell.SIZE, Cell.SIZE);
            explorer = cells[explorer.getPositionInGrid().getX()][explorer.getPositionInGrid().getY()+1];
            graphics.setColor(Color.RED);
            graphics.fillRect(explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), Cell.SIZE, Cell.SIZE);
            graphics.drawImage(getImage(), explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), null);
        }
        if(explorer.isExit()){
            System.out.println("Koniec");
            return false;
        }
        return true;
    }

    public boolean handleLeft(){
        if(explorer.getPositionInGrid().getX()-1 != -1 && cells[explorer.getPositionInGrid().getX()-1][explorer.getPositionInGrid().getY()].isPath()){
            Graphics graphics = getGraphics();
            graphics.setColor(Color.GREEN);
            graphics.fillRect(explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), Cell.SIZE, Cell.SIZE);
            explorer = cells[explorer.getPositionInGrid().getX()-1][explorer.getPositionInGrid().getY()];
            graphics.setColor(Color.RED);
            graphics.fillRect(explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), Cell.SIZE, Cell.SIZE);
            graphics.drawImage(getImage(), explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), null);
        }
        if(explorer.isExit()){
            System.out.println("Koniec");
            return false;
        }
        return true;
    }

    public boolean handleRight(){
        if(explorer.getPositionInGrid().getX()+1 != cells.length && cells[explorer.getPositionInGrid().getX()+1][explorer.getPositionInGrid().getY()].isPath()){
            Graphics graphics = getGraphics();
            graphics.setColor(Color.GREEN);
            graphics.fillRect(explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), Cell.SIZE, Cell.SIZE);
            explorer = cells[explorer.getPositionInGrid().getX()+1][explorer.getPositionInGrid().getY()];
            graphics.setColor(Color.RED);
            graphics.fillRect(explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), Cell.SIZE, Cell.SIZE);
            graphics.drawImage(getImage(), explorer.getUpperLeftVertex().getX(), explorer.getUpperLeftVertex().getY(), null);
        }
        if(explorer.isExit()){
            System.out.println("Koniec");
            return false;
        }
        return true;
    }

}
