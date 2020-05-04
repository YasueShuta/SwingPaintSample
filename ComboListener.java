package SwingPaintSample;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class ComboListener implements ActionListener {

	PaintCanvas canvas;
	
	public ComboListener(PaintCanvas canvas) {
		super();
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unchecked")
		JComboBox<String> source = (JComboBox<String>) e.getSource();
		String color = (String) source.getSelectedItem();
		canvas.setColorCombo(color);
	}

}
