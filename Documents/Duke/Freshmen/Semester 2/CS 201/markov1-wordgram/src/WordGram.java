
/**
 * A WordGram represents a sequence of strings
 * just as a String represents a sequence of characters
 * 
 * @author YOUR NAME HERE
 *
 */
public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram (add comments)
	 * @param source
	 * @param start
	 * @param size
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		
		int position = 0;
		for (int i=start;i<start+size;i++)
		{
			myWords[position] = source[i];
			position++;	
		}
		
		myToString = null;
		myHash = 0;
		
		// TODO: initialize myWords and others as needed
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * Complete this comment
	 * @return
	 */
	public int length(){
		// TODO: change this
		return myWords.length;
	}


	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null)
		{
			return false;
		}
		
		else
		{
			WordGram wg = (WordGram) o;
			
//			if (!(wg.length()==((WordGram) o).length())) //if lengths are different
//			{
//				return false; 
//			}
//			String s1 = wg.myToString;
//			String s2 = ((WordGram) o).myToString;
//			if (!s1.equals(s2)) //if strings are different
//			{
//				return false;
//			}

			
			for (int i = 0; i < myWords.length; i++) {
				
				if (! (this.wordAt(i).equals(wg.wordAt(i)))) 
				{
					return false;
				}
			}
			
		}

	    // TODO: complete this method
		return true;
	}

	@Override
	public int hashCode(){
		if (myHash ==0)
		{
			myHash = this.toString().hashCode();
		}
		return myHash;
	}
	

	/**
	 * Create and complete this comment
	 * @param last is last String of returned WordGram
	 * @return
	 */
	public WordGram shiftAdd(String last) {
		WordGram wg = new WordGram(myWords,0,myWords.length);
		// TODO: Complete this method
		return wg;
	}

	@Override
	public String toString(){
		if (myToString.equals(null)) 
		{
			myToString = String.join(" ", myWords);
		}
		
		return myToString;
	}
}
