package net.chat.util;

import java.lang.reflect.Field;
import java.util.Date;

public class CreateDomainUtil {

	public static String domainToString(Object o) throws IllegalArgumentException, IllegalAccessException {
		StringBuffer sb = new StringBuffer();
		Field[] fields = o.getClass().getDeclaredFields();
		for (Field f : fields) {
			sb.append(f.getName() + ":" + f.get(o) + ";");
		}
		return sb.toString();
	}

	public static void createDomain(Object o) throws IllegalArgumentException, IllegalAccessException {

		Field[] fields = o.getClass().getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (String.class.equals(f.getType())) {
				f.set(o, "ss");
			} else if (Long.class.equals(f.getType()) && !"id".equals(f.getName())) {
				f.set(o, 1l);
			} else if (int.class.equals(f.getType())) {
				f.set(o, 1);
			} else if (Date.class.equals(f.getType())) {
				f.set(o, new Date());
			}
		}
	}
}
