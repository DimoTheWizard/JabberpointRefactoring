import java.util.ArrayList;

/** <p>A slide. This class has drawing functionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
	protected String title; //The title is kept separately
	protected ArrayList<SlideItem> items; //The SlideItems are kept in a vector

	public Slide() {
		this.items = new ArrayList<SlideItem>();
	}

	//Add a SlideItem
	public void append(SlideItem newItem) {
		this.items.add(newItem);
	}

	//Return the title of a slide
	public String getTitle() {
		return this.title;
	}

	//Change the title of a slide
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	//Create a TextItem out of a String and add the TextItem
	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	//Return all the SlideItems in am arraylist
	public ArrayList<SlideItem> getSlideItems() {
		return this.items;
	}

	public SlideItem getSlideItem(int itemNum){
		return this.items.get(itemNum);
	}

	//Returns the size of a slide
	public int getSize() {
		return items.size();
	}

}
