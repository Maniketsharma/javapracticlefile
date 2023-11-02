import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class eh1 extends Applet implements ActionListener {
//Java Program to Change the Applet Background Colour when Button Clicked
    Button b1;
    Color[] c = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };
    int colorIndex = 0;

    @Override
    public void init() {
        b1 = new Button("Change Color");
        b1.addActionListener(this);
        add(b1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (colorIndex < c.length - 1) {
            colorIndex++;
        } else {
            colorIndex = 0;
        }
        setBackground(c[colorIndex]);
    }

}