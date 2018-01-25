package com.canaan.jgsf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SerializeUtil {
	private static final int DEFAULT_BUFF_SIZE = 1024;

	public static String convertToByteString(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            final byte[] byteArray = bos.toByteArray();
            return new String(Base64.getEncoder().encode(byteArray), StandardCharsets.UTF_8);
//            return Base64.getEncoder().encodeToString(byteArray);
        }
    }

	public static Object convertFromByteString(String byteString) throws IOException, ClassNotFoundException {
        final byte[] bytes = Base64.getDecoder().decode(byteString.getBytes(StandardCharsets.UTF_8));
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }
	public static byte[] serialize(Object target) {
		byte[] result = null;
		if (null == target) {
			return new byte[0];
		} else {
			if (!(target instanceof Serializable)) {
				throw new IllegalArgumentException(SerializeUtil.class.getSimpleName() + " requires a Serializable payload "
						+ "but received an object of type [" + target.getClass().getName() + "]");
			}
			try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream(DEFAULT_BUFF_SIZE);
				 ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream)) {
				objectOutputStream.writeObject(target);
				result = byteStream.toByteArray();
			} catch (Exception e) {
				log.error("Failed to serialize", e);
			}
			return result;
		}
	}

	public static Object deserialize(byte[] bytes) {
		Object result = null;
		if (isEmpty(bytes)) {
			return null;
		} else {
			try (ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
				 ObjectInputStream objectInputStream = new ObjectInputStream(byteStream)) {
				result = objectInputStream.readObject();
			} catch (Exception e) {
				log.error("Failed to deserialize", e);
			}
			return result;
		}
	}

	private static boolean isEmpty(byte[] data) {
		return data == null || data.length == 0;
	}
}
