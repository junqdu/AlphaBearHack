package jotaro.alphabearhack;

import android.graphics.Bitmap;

import com.googlecode.tesseract.android.TessBaseAPI;


public class ImagePiece implements Runnable{
	private boolean hasInfo = false;
	private String letter;
	private static int blackColor = -16777216;
	private static int whiteColor = -1;
	private static int dataColor = -2295585;
	private int letterPixelCount;
    private Bitmap img;
    private int x;
    private int y;
    private double pieceSize;
    private TessBaseAPI scanner;
	public ImagePiece(Bitmap img, int x, int y, double pieceSize, TessBaseAPI scanner) throws Exception {
        this.img = img;
        this.x = x;
        this.y = y;
        this.pieceSize = pieceSize;
        this.scanner = scanner;
	}
	
	public double getLetterWidth(int iy, int ix, Bitmap img){
		double result = 0.0;
		int initial = ix;
		for(int x = ix; x<img.getHeight(); x++) {
			if(img.getPixel(x, iy) != whiteColor){
				result = x - ix;
			}
		}
		if(result <= 29){
			return 29;
		}else {
			return result;
		}
	}
	
	public void cutCorner(double heightRatio, double widthRatio, int ix, int iy){
		int w = (int) (img.getWidth()*widthRatio);
		int h = (int) (img.getHeight()*heightRatio);

		for(int y = h+iy; y<img.getHeight(); y++) {
            for(int x = w+ix; x<img.getWidth(); x++) {
            	if( y < 0){
            		return;
            	}
            	img.setPixel(x, y, whiteColor);
            }
		}
	}
	
	public boolean isHasInfo() {
		return hasInfo;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}
	
	public Bitmap getImage(){
		return this.img;
	}

	public int getLetterPixelCount() {
		return letterPixelCount;
	}

    @Override
    public void run() {
        this.img = Bitmap.createBitmap(img, (int) (x * pieceSize), (int) (y * pieceSize), (int) (pieceSize), (int) (pieceSize));
        letterPixelCount = 0;
        for(int iy=0; iy<this.img.getHeight() && !hasInfo; iy++) {
            for(int ix=0; ix<this.img.getWidth() && !hasInfo; ix++) {
                if(this.img.getPixel(ix, iy) != whiteColor){
                    hasInfo = true;
                    double cy = 25.0;
                    double cx = getLetterWidth(iy, ix, this.img);
                    //System.out.println("getLetterWidth is " + cx);
                    cutCorner(cy / 154, cx / this.img.getWidth(), ix, iy);
                    scanner.setImage(this.img);
                    String text = scanner.getUTF8Text();
                    letter = text.trim();
					System.out.println("pre: " + letter);
					if(letter.length()>=1) {
                        letter = letter.charAt(0) + "";
                        letter = letter.replaceAll("0", "O");
                        letter = letter.replaceAll("5", "S");
						letter = letter.replaceAll("<", "C");
						letter = letter.replaceAll("3", "S");
					} else if (letter.length()==0) {
                        letter = "I";
                    }
                    System.out.println(letter);
                    /*
                    while(letter.trim().length() > 1 && cx > 0 && cy > 0 ){
        	        	//System.out.println("cutting");
        	        	cutCorner(cy--/154, cx--/this.img.getWidth(), ix, iy);
            	        text = scanner.getUTF8Text();
            	        letter = text.trim();
        	        	System.out.println(letter);
        	        }
        	        if(letter.trim().length() != 1) {
        	        	throw new Exception("Invalid Size");
        	        }
        	        */
                    //System.out.println(letter);

                }
            }
        }
    }
}
