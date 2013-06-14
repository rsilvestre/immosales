/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.controller.bien;

import com.dmurph.mvc.MVCEvent;
import app.model.bien.FindBienModel;

import java.util.HashMap;

/**
 * Created by michaelsilvestre on 20/05/13.
 */
public class FindBienDbEvent extends MVCEvent {


	public final HashMap<String,String> requestFieldDatas;
	public final FindBienModel model;

	public FindBienDbEvent(HashMap<String, String> requestFieldDatasArgs, FindBienModel findBienModel) {
		super(BienController.FIND);
		requestFieldDatas = requestFieldDatasArgs;
		this.model = findBienModel;
	}
}
