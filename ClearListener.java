package SwingPaintSample;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener {
	
	PaintCanvas canvas;

	public ClearListener(PaintCanvas canvas) {
		super();
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		canvas.clear();
	}

}
