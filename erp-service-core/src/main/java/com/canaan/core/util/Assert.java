package com.canaan.core.util;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.canaan.core.exception.ExceptionEnumable;
import com.canaan.core.exception.ServerException;
import com.canaan.util.tool.Checker;
import com.google.common.base.Preconditions;
public class Assert {
	
	private Assert(){}
	
	
	
	private static boolean BeNotNull(Object reference,boolean beStrict) {
		if (reference == null) {
			return false;
		}
		if (beStrict) {
			if (String.class.isInstance(reference)) {
				return !StringUtils.isBlank((String)reference);
			} 
			else if (Collection.class.isInstance(reference)) {
				return !((Collection<?>) reference).isEmpty();
			}
		}
		return true;
	}
	
//	private static <T> boolean BeNotNull(T reference) {
//		if (reference == null) {
//			return false;
//		}
//		return true;
//	}
//	
//	private static <T> boolean BeNotNull(Collection<T> collection) {
//		if (CollectionUtils.isEmpty(collection)) {
//			return false;
//		}
//		return true;
//	}
	
	public static <T> T CheckNotNull(T reference, ExceptionEnumable exenum, Object...args) {
		if (!Checker.BeNotNull(reference)) {
			throw new ServerException(exenum, args);
		}
		return reference;
	}
	
	public static <T> T CheckNotNull(T reference, ExceptionEnumable exenum) {
		if (!Checker.BeNotNull(reference)) {
			throw new ServerException(exenum, exenum);
		}
		return reference;
	}
	
	public static <T> Collection<T> CheckNotNull(Collection<T> collection, ExceptionEnumable exenum, Object...args) {
		if (!Checker.BeNotNull(collection)) {
			throw new ServerException(exenum, args);
		}
		return collection;
	}
	
	public static <T> Collection<T> CheckNotNull(Collection<T> collection, ExceptionEnumable exenum) {
		if (!Checker.BeNotNull(collection)) {
			throw new ServerException(exenum);
		}
		return collection;
	}
	
	
	public static <T> T CheckNotNull(boolean beStrict, T reference) {
		if (!BeNotNull(reference, beStrict)) {
			throw new NullPointerException();
		}
		return reference;
	}
	
	public static <T> T CheckNotNull(T refererce) {
		if (!Checker.BeNotNull(refererce)) {
			throw new NullPointerException();
		}
		return refererce;
	}
	
	
	public static void CheckArgument(Object... references) {
		for (Object object : references) {
			Preconditions.checkArgument(BeNotNull(object, false));
		}
//		Arrays.asList(references).stream().forEach(reference -> Preconditions.checkArgument(BeNotNull(reference, beStrict)));
	}
	
	public static void CheckArgumentStrict(Object...references) {
		for (Object object : references) {
			Preconditions.checkArgument(BeNotNull(object, true));
		}
	}
	
	public static <T> T CheckArgument(T reference) {
		Preconditions.checkArgument(Checker.BeNotNull(reference));
		return reference;
	}
	
	public static <T> Collection<T> CheckArgument(Collection<T> collection) {
		Preconditions.checkArgument(Checker.BeNotNull(collection));
		return collection;
	}
	
	public static <T> T CheckArgument(T reference, ExceptionEnumable exenum, Object...args) {
		if (!Checker.BeNotNull(reference)) {
			throw new ServerException(exenum, args);
		}
		return reference;
	}
	
	public static <T> T CheckArgument(T reference, ExceptionEnumable exenum) {
		if (!Checker.BeNotNull(reference)) {
			throw new ServerException(exenum);
		}
		return reference;
	}
	
	public static <T> Collection<T> CheckArgument(Collection<T> collection, ExceptionEnumable exenum, Object...args) {
		if (!Checker.BeNotNull(collection)) {
			throw new ServerException(exenum, args);
		}
		return collection;
	}
	
	public static <T> Collection<T> CheckArgument(Collection<T> collection, ExceptionEnumable exenum) {
		if (!Checker.BeNotNull(collection)) {
			throw new ServerException(exenum);
		}
		return collection;
	}
	
	public static <T> void CheckNotEqual(T reference, Object target, ExceptionEnumable exceptionEnum) {
		CheckArgument(reference);
		if (!reference.equals(target)) {
			throw new ServerException(exceptionEnum);
		}
	}
	
	
	
	
	
}
