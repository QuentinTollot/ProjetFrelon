package results;

/**
 * Used to get the real lenght of the hornet
 */
public class RealLenght {
	/**
	 * the number of pixel in the picture per millimeter
	 */
	private int pixelPerMm;
	public RealLenght(int pixelPerMmParam) {
		pixelPerMm = pixelPerMmParam;
	}
	public int getPixelPerMm() {
		return pixelPerMm;
	}
	public void setPixelPerMm(int pixelPerMm) {
		this.pixelPerMm = pixelPerMm;
	}
	
	public double getRealLenght(double lenghtInPixel) {
		return lenghtInPixel / pixelPerMm;
	}
}