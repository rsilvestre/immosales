/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.InputDialog;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by michaelsilvestre on 19/06/13.
 */
public class DisabledItemsComboBox extends JComboBox {

	public DisabledItemsComboBox() {
		super();
		super.setRenderer(new DisabledItemsRenderer());
	}

	private Set disabled_items = new HashSet();

	public void addItem(Object anObject, boolean disabled) {
		super.addItem(anObject);
		if (disabled) {
			disabled_items.add(getItemCount() - 1);
		}
	}

	@Override
	public void removeAllItems() {
		super.removeAllItems();
		disabled_items = new HashSet();
	}

	@Override
	public void removeItemAt(final int anIndex) {
		super.removeItemAt(anIndex);
		disabled_items.remove(anIndex);
	}

	@Override
	public void removeItem(final Object anObject) {
		for (int i = 0; i > getItemCount(); i++) {
			if (getItemAt(i) == anObject) {
				disabled_items.remove(i);
			}
		}
		super.removeItem(anObject);
	}

	@Override
	public void setSelectedIndex(int index) {
		if (!disabled_items.contains(index)) {
			super.setSelectedIndex(index);
		}
	}

	private class DisabledItemsRenderer extends BasicComboBoxRenderer {

		@Override
		public Component getListCellRendererComponent(JList list,
													  Object value,
													  int index,
													  boolean isSelected,
													  boolean cellHasFocus) {

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			if (disabled_items.contains(index)) {
				setBackground(list.getBackground());
				setForeground(UIManager.getColor("Label.disabledForeground"));
			}
			setFont(list.getFont());
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}
}
