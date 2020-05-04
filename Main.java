package SwingPaintSample;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Main {
	
	static int w = 800, h = 600;

	public static void main(String[] args) {
		run();
	}

	public static void run() {
		// JFrameのインスタンスを生成
		JFrame frame = new JFrame("マイ ペイント");
		// ウィンドウを閉じたらプログラムを終了する
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ウィンドウのサイズ,初期位置
		frame.setSize(w, h);
		frame.setLocationRelativeTo(null);
		
		// PaintCanvasのインスタンスを生成
		PaintCanvas canvas = new PaintCanvas(w, h);
		
		// フレームの追加
		JPanel p_main = new JPanel();
		frame.getContentPane().add(p_main, BorderLayout.CENTER);
		JPanel p_ctrl = new JPanel();
		frame.getContentPane().add(p_ctrl, BorderLayout.NORTH);
		
		canvas.setPreferredSize(new Dimension(w, h));
		p_main.add(canvas);
		
		// 全消去
		JButton clear = new JButton("CLEAR");
		clear.addActionListener(new ClearListener(canvas));
		p_ctrl.add(clear);
		
		// 線の太さ調節
		JSlider slider = new JSlider(1, 50, 1);
		slider.addChangeListener(new SliderListener(canvas));
		p_ctrl.add(slider);
		
		// 線の色変更
		String[] combodata = {"BLACK", "RED", "BLUE", "GREEN"};
		JComboBox<String> combo = new JComboBox<String>(combodata);
		combo.addActionListener(new ComboListener(canvas));
		p_ctrl.add(combo);
		
		// ウィンドウを表示
		frame.setVisible(true);
	}
}
