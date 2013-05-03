package JMVC.controller.main;

import JMVC.model.main.MainModel;
import com.dmurph.mvc.MVCEvent;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 27/04/13
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */

public class ColorChangeEvent extends MVCEvent {

	public final Color color;
	public final MainModel model;

	public ColorChangeEvent(Color argColor, MainModel argModel) {
		super(MainController.COLOR_CHANGE);
		color = argColor;
		model = argModel;
	}
}
