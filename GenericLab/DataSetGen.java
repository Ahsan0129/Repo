

public class DataSetGen <T extends Measurable> {
		
	   private double sum; // Instance variables(only use getter and setter for privates)
	   private T maximum;//T are the classes that extend interface meaning T is baseball and bankaccount, these classes are implementing the interface measurable
	   private int count;

	   /**
	      Constructs an empty data set.
	 * @return 
	      
	   */
	   
	   public void Data()
	   {
	      sum = 0;
	      count = 0;
	      maximum = null;
	   }

	   /**
	      Adds a data value to the data set.
	      @param x a data value
	   */
	   public void add(T x)
	   {
	      sum = sum + x.getMeasure();
	      if (count == 0 || maximum.getMeasure() < x.getMeasure())
	         maximum = x;
	      count++;
	   }

	   /**
	      Gets the average of the added data.
	      @return the average or 0 if no data has been added
	   */
	   public double getAverage()
	   {
	      if (count == 0) return 0;
	      else return sum / count;
	   }

	   /**
	      Gets the largest of the added data.
	 * @return 
	      @return the maximum or 0 if no data has been added
	   */
	public T getMaximum()
	   {
	      return (T) maximum;
	   }
	}

