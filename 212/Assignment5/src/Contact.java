
public class Contact {

	public static String _phone;
	public static String _last;
	public static String _first;
	public Contact(String num, String lastName, String firstName){
		_phone = num;
		_last = lastName;
		_first = firstName;
	}
	public static String getFirst(){
		return _first;
	}
	public static String getLast(){
		return _last;
	}
	public static String getPhone(){
		return _phone;
	}
}