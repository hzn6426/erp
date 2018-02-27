
import java.security.MessageDigest;
import java.util.Calendar;


public class CommonTest {

	public static void main(String[] args) throws Exception{
//		String prefix = "shiro_session:";
//		String key = prefix + "wewfwefwf";
//		
////		MenuDTO dto = new MenuDTO();
////		dto.setCode("111");
//		String str = SerializeUtil.convertToByteString("wewfwefwf");
////		System.out.println(Arrays.toString(bytes));
////		String str = new String(bytes,StandardCharsets.UTF_8);
////		MessageDigest md = MessageDigest.getInstance("MD5");
//		System.out.println(key+str);
//		MenuDTO m =  (MenuDTO) SerializeUtil.convertFromByteString(str);
//		byte[] strbytes = str.getBytes(StandardCharsets.UTF_8);
//		System.out.println(Arrays.toString(strbytes));
//		String key =  prefix + str;
//		System.out.println(key);
//		int index = key.indexOf(":");
//		System.out.println(index);
//		String objStr = key.substring(prefix.length());
//		System.out.println(objStr);
//		byte [] targetbytes = objStr.getBytes("UTF-8");
//		System.out.println(targetbytes);
//		MenuDTO m =  (MenuDTO) SerializeUtil.deserialize(strbytes);
//		System.out.println(m.getCode());
//		System.out.println(java.util.TimeZone.getDefault().getID());
//		System.out.println(Calendar.getInstance().getTime());
		String passwd = "admin";
		byte[] hashed = passwd.getBytes("utf8");
		MessageDigest digest = MessageDigest.getInstance("MD5");
		for (int i = 0; i < 2; ++i) {
//			digest.reset();
			hashed = digest.digest(hashed);
		}
		System.out.println(new String(hashed,"utf8"));
	}

}
