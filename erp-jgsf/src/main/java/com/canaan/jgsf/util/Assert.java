package com.canaan.jgsf.util;

import java.util.Arrays;
import java.util.Collection;

import com.canaan.distribute.util.Checker;
import com.canaan.jgsf.exception.ClientBizException;
import com.canaan.jgsf.exception.ClientExceptionEnum;
import com.google.common.base.Preconditions;
public class Assert {
	
	private Assert(){}
	
	
	private static boolean BeNotNull(Object reference,boolean beStrict) {
		if (reference == null) {
			return false;
		}
		if (beStrict) {
			if (String.class.isInstance(reference)) {
				return !"".equals(reference);
			} else if (Collection.class.isInstance(reference)) {
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
	
	public static <T> T CheckNotNull(T reference, ClientExceptionEnum exenum, Object...args) {
		if (!Checker.BeNotNull(reference)) {
			throw new ClientBizException(exenum, args);
		}
		return reference;
	}
	
	public static <T> T CheckNotNull(T reference, ClientExceptionEnum exenum) {
		if (!Checker.BeNotNull(reference)) {
			throw new ClientBizException(exenum, exenum);
		}
		return reference;
	}
	
	public static <T> Collection<T> checkNotNull(Collection<T> collection, ClientExceptionEnum exenum, Object...args) {
		if (!Checker.BeNotNull(collection)) {
			throw new ClientBizException(exenum, args);
		}
		return collection;
	}
	
	public static <T> Collection<T> checkNotNull(Collection<T> collection, ClientExceptionEnum exenum) {
		if (!Checker.BeNotNull(collection)) {
			throw new ClientBizException(exenum);
		}
		return collection;
	}
	
	
	public static <T> T CheckNotNull(boolean beStrict, T reference) {
		if (!BeNotNull(reference, beStrict)) {
			throw new NullPointerException();
		}
		return reference;
	}
	
	public static <T> T checkNotNull(T refererce) {
		if (!Checker.BeNotNull(refererce)) {
			throw new NullPointerException();
		}
		return refererce;
	}
	
	
	public static void CheckArgument(boolean beStrict, Object... references) {
		for (Object object : references) {
			Preconditions.checkArgument(BeNotNull(object, beStrict));
		}
//		Arrays.asList(references).stream().forEach(reference -> Preconditions.checkArgument(BeNotNull(reference, beStrict)));
	}
	
	public static <T> T checkArgument(T reference) {
		Preconditions.checkArgument(Checker.BeNotNull(reference));
		return reference;
	}
	
	public static <T> Collection<T> checkArgument(Collection<T> collection) {
		Preconditions.checkArgument(Checker.BeNotNull(collection));
		return collection;
	}
	
	public static <T> T checkArgument(T reference, ClientExceptionEnum exenum, Object...args) {
		if (!Checker.BeNotNull(reference)) {
			throw new ClientBizException(exenum, args);
		}
		return reference;
	}
	
	public static <T> T checkArgument(T reference, ClientExceptionEnum exenum) {
		if (!Checker.BeNotNull(reference)) {
			throw new ClientBizException(exenum);
		}
		return reference;
	}
	
	public static <T> Collection<T> checkArgument(Collection<T> collection, ClientExceptionEnum exenum, Object...args) {
		if (!Checker.BeNotNull(collection)) {
			throw new ClientBizException(exenum, args);
		}
		return collection;
	}
	
	public static <T> Collection<T> checkArgument(Collection<T> collection, ClientExceptionEnum exenum) {
		if (!Checker.BeNotNull(collection)) {
			throw new ClientBizException(exenum);
		}
		return collection;
	}
	
	public static <T> void checkNotEqual(T reference, Object target, ClientExceptionEnum exceptionEnum) {
		checkArgument(reference);
		if (!reference.equals(target)) {
			throw new ClientBizException(exceptionEnum);
		}
	}
	
	
	
	
	
}
