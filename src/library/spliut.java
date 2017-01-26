package library;

public class spliut {

	public static void main(String[] args) {
	
		String names="gbr,usa,nor";
		String[] countryNames=new String[(names.split(",").length)];
		
		countryNames=names.split(",");
				
				
		
		for (String country : countryNames) {
			System.out.println(country);
		}
		
		
	}
}
