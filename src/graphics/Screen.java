package graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import gui.Window;
import inputs.Keyboard;
import inputs.Mouse;
import mathematics.Vector2d;
import mathematics.Vector2i;

public class Screen extends Canvas {

	public void clear(int colour) {
		for (int i = 0; i < pixelMap.length; ++i) {
			pixelMap[i] = colour;
		}
	}

	public Vector2i getDimensions() {
		return new Vector2i(dimensions);
	}

	public Vector2d getDimensionsD() {
		return new Vector2d(dimensions);
	}

	public int getHeight() {
		return scaledDimensions.height;
	}

	public static Screen getInstance() {
		if (theScreen == null) {
			theScreen = new Screen(Window.DEFAULT_SCREEN_WIDTH, Window.DEFAULT_SCREEN_HEIGHT, Window.DEFAULT_SCALE);
		}
		return theScreen;
	}

	public int getWidth() {
		return scaledDimensions.width;
	}

	public void render() {
		BufferStrategy bufferStrategy = getBufferStrategy(); // Canvas method.
		if (bufferStrategy == null) {
			createBufferStrategy(3); // Triple-buffering.
			return;
		}
		Graphics graphics = bufferStrategy.getDrawGraphics();
		graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		graphics.dispose();
		bufferStrategy.show();
	}

	public void render(Sprite sprite, Vector2i position, boolean fixed) {
		position = new Vector2i(position);
		if (!fixed) {
			position.subtract(offset);
		}
		final int spriteHeight = sprite.getHeight();
		final int spriteWidth = sprite.getWidth();
		for (int y = 0; y < spriteHeight; ++y) {
			final int yAbsolute = y + position.getY();
			if (yAbsolute > -1 && yAbsolute < dimensions.height) {
				for (int x = 0; x < spriteWidth; ++x) {
					int xAbsolute = x + position.getX();
					if (xAbsolute >= -sprite.getWidth() && xAbsolute < dimensions.width) {
						if (xAbsolute < 0) {
							xAbsolute = 0;
						}
						final int index = yAbsolute * dimensions.width + xAbsolute;
						final int colour = sprite.getPixel(x, y);
						if (index > -1 && index < pixelMap.length && colour != 0x00000000) {
							pixelMap[index] = colour;
						}
					}
				}
			}
		}
	}

	public void render(Sprite sprite, Vector2i position, boolean fixed, int clearColour) {
		position = new Vector2i(position);
		if (!fixed) {
			position.subtract(offset);
		}
		final int spriteHeight = sprite.getHeight();
		final int spriteWidth = sprite.getWidth();
		for (int y = 0; y < spriteHeight; ++y) {
			final int yAbsolute = y + position.getY();
			if (yAbsolute > -1 && yAbsolute < dimensions.height) {
				for (int x = 0; x < spriteWidth; ++x) {
					int xAbsolute = x + position.getX();
					if (xAbsolute >= -sprite.getWidth() && xAbsolute < dimensions.width) {
						if(xAbsolute < 0) {
							xAbsolute = 0;
						}
						final int index = yAbsolute * dimensions.width + xAbsolute;
						final int colour = sprite.getPixel(x, y);
						if (index > -1 && index < pixelMap.length && colour != clearColour) {
							pixelMap[index] = colour;
						}
					}
				}
			}
		}
	}

	public void resize(Dimension scaledDimensions) {
		this.scaledDimensions = scaledDimensions;
		dimensions = new Dimension(scaledDimensions.width / scale, scaledDimensions.height / scale);
		image = new BufferedImage(dimensions.width, dimensions.height, BufferedImage.TYPE_INT_RGB);
		pixelMap = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		setPreferredSize(scaledDimensions);
	}

	public void setOffset(Vector2i offset) {
		this.offset = offset;
	}

	private BufferedImage image;
	private Dimension dimensions;
	private Dimension scaledDimensions;
	private int[] pixelMap;
	private int scale;
	private static final long serialVersionUID = 1L;
	private Vector2i offset;
	private static Screen theScreen;

	private Screen(int width, int height, int scale) {
		this.scale = scale;
		// 初始化鍵盤事件監聽器然後把它加入screen。
		addKeyListener(Keyboard.getInstance());

		// 初始化滑鼠事件監聽器然後把它加入screen。
		addMouseListener(Mouse.getInstance());
		addMouseMotionListener(Mouse.getInstance());

		// Height is calculated to obtain a 16:9 aspect ratio.
		resize(new Dimension(width * scale, height * scale));
	}

}
