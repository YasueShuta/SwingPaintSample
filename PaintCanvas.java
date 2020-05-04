package SwingPaintSample;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class PaintCanvas extends Canvas implements MouseListener, MouseMotionListener {

	// 描画内容を保持
	private BufferedImage cImage = null;
	// 描画インスタンス
	private Graphics2D g2d;
	
	// キャンバスのサイズ
	private int w;
	private int h;
	
	// 線の開始座標・終了座標
	private int sx, sy, ex, ey;
	// 描画モード: draw(0), erase(1)
	private int type;
	// 線の太さ
	public int width = 1;
	public Color c = Color.black;
	
	public PaintCanvas(int w, int h) {
		// お絵かき用キャンバス
		// 座標を初期化
		this.w = w;
		this.h = h;
		sx = sy = -1;
		ex = ey = -1;
		type = 0;
		
		// MouseListener, MouseMotionListenerを設定
		addMouseListener(this);
		addMouseMotionListener(this);
		
		// キャンバスの背景を白に設定
		setBackground(Color.white);
		cImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) cImage.getGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, w, h);
		
		// 描画
		repaint();
	}

	public PaintCanvas(GraphicsConfiguration config) {
		super(config);
		// お絵かき用キャンバス
		
	}
	
	// キャンバスをクリア
	public void clear() {
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, w, h);
		repaint();
	}

	// 線の太さ変更
	public void setStroke(int n) {
		width = n;
	}

	// 線の色変更
	public void setColorCombo(String color) {
		if (color.contentEquals("BLACK")) {
			c = Color.black;
		} else if (color.contentEquals("RED")) {
			c = Color.red;
		} else if (color.contentEquals("BLUE")) {
			c = Color.blue;
		} else if (color.contentEquals("GREEN")) {
			c = Color.green;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// 描画モード（線分を描画）
		if (type == 1) {
			if (sx >= 0 && sy >= 0 && ex >= 0 && ey >= 0) {
				BasicStroke stroke = new BasicStroke(width, 
						BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
				g2d.setStroke(stroke);
				g2d.setColor(c);
				g2d.drawLine(ex, ey, sx, sy);
			}
		}
		// 消しゴムモード
		else if (type == 2) {
			if (sx >= 0 && sy >= 0 && ex >= 0 && ey >= 0) {
				// 両端が丸い線分に設定
				BasicStroke stroke = new BasicStroke(50.0f,
						BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
				g2d.setStroke(stroke);
				g2d.setColor(Color.white);
				g2d.drawLine(ex, ey, sx, sy);
			}
		}
		
		// キャンバスに反映
		g.drawImage(cImage, 0, 0, null);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		// 押されているボタンを検知
		int mod = e.getModifiersEx();
		if ((mod & MouseEvent.BUTTON1_DOWN_MASK) != 0) {
			// 左クリック(描画モード)
			type = 1;
		}
		if ((mod & MouseEvent.BUTTON3_DOWN_MASK) != 0) {
			// 右クリック(消しゴムモード)
			type = 2;
		}
		
		// 過去の座標を開始座標に設定
		ex = sx;
		ey = sy;
		
		// 新しい座標を終了座標に設定
		Point point = e.getPoint();
		sx = point.x;
		sy = point.y;
		
		// 再描画
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// ドラッグが終了したら座標を初期化
		sx = sy = -1;
		ex = ey = -1;
		type = 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Nothing
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 座標を取得
		Point point = e.getPoint();
		sx = point.x;
		sy = point.y;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Nothing		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Nothing		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Nothing
	}
}
