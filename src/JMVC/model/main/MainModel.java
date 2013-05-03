package JMVC.model.main;

import com.dmurph.mvc.model.AbstractModel;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 27/04/13
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class MainModel extends AbstractModel {
        private String text = "MVC is GREAT!";
        private Color color = Color.black;

        public void setText(String argText) {
                String old = text;
                text = argText;
                firePropertyChange("text", old, text);
        }
        public void setColor(Color argColor) {
                Color old = color;
                color = argColor;
                firePropertyChange("color", old, color);
        }

        public Color getColor() {
                return color;
        }
        public String getText() {
                return text;
        }
}
