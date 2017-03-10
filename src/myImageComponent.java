import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

// content pane
class myImageComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage img = null;

	public myImageComponent() {
		File file = new File(
				"C:\\Java\\kadai\\workspace\\�O���[�v6�@Nearly Production\\bin\\design\\�w�i3.png");
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 100, 0, this);
	}
}