package results;

/**
 * Cette classe permet de convertir les pixels en mm
 */
public class RealLenght {
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