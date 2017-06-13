package graphics;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.Serializable;

import javax.swing.JFrame;

import main.JavaGo;
import mathematics.Vector2d;
import mathematics.Vector2i;

public class Screen extends Canvas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4658050286714842228L;
	public Screen(int width, int height, int scale) {
		this.scale = scale;
		// Height is calculated to obtain a 16:9 aspect ratio.
		resize(new Dimension(width * scale, height * scale));

		frame = new JFrame();
		frame.setResizable(true);
		frame.setTitle(JavaGo.TITLE);
		frame.add(this); // Fills from with this instance of Screen, which is a
							// subclass of Canvas, which in turn is a subclass
							// of Component.
		frame.pack(); // Resizes frame to be same size as Component added above.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.addComponentListener(new ComponentListener() 
		{  
		        // This method is called after the component's size changes
		        public void componentResized(ComponentEvent evt) {
		            Component c = (Component)evt.getSource();
		    
		            // Get new size
		            Dimension newSize = c.getSize();
		            resize(newSize);
		        }

				@Override
				public void componentHidden(ComponentEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void componentMoved(ComponentEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void componentShown(ComponentEvent arg0) {
					// TODO Auto-generated method stub
					
				}
		});
		frame.setVisible(true);
		requestFocus();
	}

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
						if(xAbsolute < 0) {
							xAbsolute = 0;
						}
						final int index = yAbsolute * dimensions.width + xAbsolute;
						final int colour = sprite.getPixel(x, y);
						if (index > -1 && index < pixelMap.length) {
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

	public void setTitle(String title) {
		frame.setTitle(title);
	}

	private Dimension dimensions;
	private Dimension scaledDimensions;
	private JFrame frame;
	private BufferedImage image;
	private int[] pixelMap;
	private int scale;
	private Vector2i offset;

}
