import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// Each class accepts a vistor interface and a common function of vistor is called.
// This common function is overloaded with different types of classes.
// What type code will be executed is based on the type of object passed in the common function.
// Notice that visitor interface is decalared latter.

// The visitor pattern is used when we have to perform an operation on a group of similar kinds of objects. 
// With the help of a visitor pattern, we can move the operational logic from the objects to another class.

interface ItemElement {

	public int accept(ShoppingCartVisitor visitor);
}

class Book implements ItemElement, java.io.Serializable {

	private int price;
	private String isbnNumber;
	
	public Book(int cost, String isbn){
		this.price=cost;
		this.isbnNumber=isbn;
	}
	
	public int getPrice() {
		return price;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}

}

class Fruit implements ItemElement {
	
	private int pricePerKg;
	private int weight;
	private String name;
	
	public Fruit(int priceKg, int wt, String nm){
		this.pricePerKg=priceKg;
		this.weight=wt;
		this.name = nm;
	}
	
	public int getPricePerKg() {
		return pricePerKg;
	}


	public int getWeight() {
		return weight;
	}

	public String getName(){
		return this.name;
	}
	
	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}

}

interface ShoppingCartVisitor {

	int visit(Book book);
	int visit(Fruit fruit);
}

class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

	@Override
	public int visit(Book book) {
		int cost=0;
		//apply 5$ discount if book price is greater than 50
		if(book.getPrice() > 50){
			cost = book.getPrice()-5;
		}else cost = book.getPrice();
		System.out.println("Book ISBN::"+book.getIsbnNumber() + " cost ="+cost);
		return cost;
	}

	@Override
	public int visit(Fruit fruit) {
		int cost = fruit.getPricePerKg()*fruit.getWeight();
		System.out.println(fruit.getName() + " cost = "+cost);
		return cost;
	}

}

class ShoppingCartClient {

	private static int calculatePrice(ItemElement[] items) {
		ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
		int sum=0;
		for(ItemElement item : items){
			sum = sum + item.accept(visitor);
		}
		return sum;
	}

	public static void main(String[] args) {

		ItemElement[] items = new ItemElement[]{new Book(20, "1234"),new Book(100, "5678"),
				new Fruit(10, 2, "Banana"), new Fruit(5, 5, "Apple")};
		
		int total = calculatePrice(items);
		System.out.println("Total Cost = " + total);

		// Checking if same object value maps to different key in hash set.
		Set<Book> books = new HashSet<Book>();
		Book a = new Book(20, "1234");
		Book b = new Book(20, "1234");

		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(a.equals(b));

		books.add(a);
		books.add(b);
		System.out.println(books.size());

		Set<String> uniqueSet = new HashSet<>(books.size());
		Set<Book> uniqueBooks = books.stream().filter(p -> uniqueSet.add(p.getIsbnNumber())).collect(Collectors.toSet());
		System.out.println(uniqueBooks.size());
	}
}
