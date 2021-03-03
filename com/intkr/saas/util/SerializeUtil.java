package com.intkr.saas.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * 
 * @author Beiden
 * @date 2017-12-4
 * @version 1.0
 */
public class SerializeUtil {

	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
		} finally {
			FileUtil.close(oos);
			FileUtil.close(baos);
		}
		return null;
	}

	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
		} finally {
			FileUtil.close(bais);
			FileUtil.close(ois);
		}
		return null;
	}

	public static class kryo {

		public static byte[] serialize(Object obj) {
			return null;
		}

		// 由于kryo不是线程安全的，所以每个线程都使用独立的kryo
		private static final ThreadLocal<Kryo> kryoLocal = new ThreadLocal<Kryo>() {
			protected Kryo initialValue() {
				Kryo kryo = new Kryo();
				return kryo;
			}
		};
		private static final ThreadLocal<Output> outputLocal = new ThreadLocal<Output>();
		private static final ThreadLocal<Input> inputLocal = new ThreadLocal<Input>();

		public static void serialize(Object obj, byte[] bytes) {
			Kryo kryo = getKryo();
			Output output = getOutput(bytes);
			kryo.writeObjectOrNull(output, obj, obj.getClass());
			output.flush();
		}

		public static void serialize(Object obj, byte[] bytes, int offset, int count) {
			Kryo kryo = getKryo();
			Output output = getOutput(bytes, offset, count);
			kryo.writeObjectOrNull(output, obj, obj.getClass());
			output.flush();
		}

		private static Kryo getKryo() {
			return kryoLocal.get();
		}

		/**
		 * 获取Output并设置初始数组
		 * 
		 * @param bytes
		 * @return
		 */
		private static Output getOutput(byte[] bytes) {
			Output output = null;
			if ((output = outputLocal.get()) == null) {
				output = new Output();
				outputLocal.set(output);
			}
			if (bytes != null) {
				output.setBuffer(bytes);
			}
			return output;
		}

		/**
		 * 获取Output
		 * 
		 * @param bytes
		 * @return
		 */
		private static Output getOutput(byte[] bytes, int offset, int count) {
			Output output = null;
			if ((output = outputLocal.get()) == null) {
				output = new Output();
				outputLocal.set(output);
			}
			if (bytes != null) {
				output.writeBytes(bytes, offset, count);
			}
			return output;
		}

		/**
		 * 获取Input
		 * 
		 * @param bytes
		 * @param offset
		 * @param count
		 * @return
		 */
		private static Input getInput(byte[] bytes, int offset, int count) {
			Input input = null;
			if ((input = inputLocal.get()) == null) {
				input = new Input();
				inputLocal.set(input);
			}
			if (bytes != null) {
				input.setBuffer(bytes, offset, count);
			}
			return input;
		}

		public static <T> T deserialize(byte[] bytes, int offset, int count) {
			Kryo kryo = getKryo();
			Input input = getInput(bytes, offset, count);
			return (T) kryo.readClassAndObject(input);
		}

		public static <T> T deserialize(byte[] bytes) {
			return deserialize(bytes, 0, bytes.length);
		}

	}

}
