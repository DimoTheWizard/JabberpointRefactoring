import java.util.ArrayList;

/**
 * <p>Presentations keeps track of the slides in a presentation.</p>
 * <p>Only one instance of this class is available.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String title; //The title of the presentation
	private ArrayList<Slide> slides = null; //An ArrayList with slides
	private int currentSlideNumber = 0; //The number of the current slide

	//CONSTRUCTOR
	public Presentation() {
		this.slides = new ArrayList<>();
		this.currentSlideNumber = 0;
	}

	//METHODS
	public void nextSlide(){
		setCurrentSlideNumber(this.currentSlideNumber + 1);
	}

	public void prevSlide(){
		setCurrentSlideNumber(this.currentSlideNumber - 1);
	}

	public void clear(){
		this.slides.clear();
	}

	public void append(Slide slide){
		this.slides.add(slide);
	}

	//GETTERS AND SETTERS
	public int getSlidesSize(){
		return this.slides.size();
	}

	public String getTitle(){
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCurrentSlideNumber(){
		return this.currentSlideNumber;
	}

	public void setCurrentSlideNumber(int currentSlideNumber) {
		this.currentSlideNumber = currentSlideNumber;
	}

	public Slide getSlide(int num){
		if(this.isSlideNumValid(num)){
			return this.slides.get(num);
		} else {
			return null;
		}
	}

	public Slide getCurrentSlide(){
		return getSlide(this.currentSlideNumber);
	}

	private boolean isSlideNumValid(int slideNum){
		if(slideNum >= 0 && slideNum < this.slides.size()){
			return true;
		}
		return false;
	}
}
