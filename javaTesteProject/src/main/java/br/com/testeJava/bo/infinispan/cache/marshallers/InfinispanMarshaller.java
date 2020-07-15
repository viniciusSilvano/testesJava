package br.com.testeJava.bo.infinispan.cache.marshallers;

import java.io.ByteArrayInputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;

import org.infinispan.commons.io.ByteBuffer;
import org.infinispan.commons.io.ByteBufferFactoryImpl;
import org.infinispan.commons.io.ExposedByteArrayOutputStream;
import org.infinispan.commons.marshall.AdaptiveBufferSizePredictor;
import org.infinispan.commons.marshall.BufferSizePredictor;
import org.infinispan.commons.marshall.Marshaller;
import org.infinispan.commons.marshall.StreamingMarshaller;
import org.infinispan.commons.util.Util;

import com.thoughtworks.xstream.XStream;

public class InfinispanMarshaller implements Marshaller, StreamingMarshaller {

	// private ObjectMapper objectMapper = new ObjectMapper();
	XStream xs = new XStream();

	private static final Logger LOGGER = Logger.getLogger(InfinispanMarshaller.class.getName());

	@Override
	public ObjectOutput startObjectOutput(OutputStream os, boolean isReentrant, int estimatedSize) throws IOException {
		return new ObjectOutputStream(os);
	}

	@Override
	public void finishObjectOutput(ObjectOutput oo) {
		Util.flushAndCloseOutput(oo);
	}

	@Override
	public void objectToObjectStream(Object obj, ObjectOutput out) throws IOException {
		LOGGER.info(String.format("Infinispan PREPARING TO WRITING, obj and objectMapper is null: %b/%b",
				Objects.nonNull(obj), Objects.nonNull(xs)));
		String xml = xs.toXML(obj);
		LOGGER.info(String.format("Infinispan WRITING: %s", xml));
		out.writeObject(xml);
	}

	@Override
	public Object objectFromObjectStream(ObjectInput in) throws IOException, ClassNotFoundException {
		LOGGER.info(String.format("Infinispan PREPARING TO READING, obj and objectMapper is null: %b/%b",
				Objects.nonNull(in), Objects.nonNull(xs)));
		String xml = (String) in.readObject();
		LOGGER.info(String.format("Infinispan READING: %s", xml));
		return xs.fromXML(xml);
	}

	@Override
	public ObjectInput startObjectInput(InputStream is, boolean isReentrant) throws IOException {
		return new ObjectInputStream(is);
	}

	@Override
	public void finishObjectInput(ObjectInput oi) {
		if (oi != null) {
			try {
				oi.close();
			} catch (IOException e) {
			}
		}
	}

	@Override
	public ByteBuffer objectToBuffer(Object o) throws IOException, InterruptedException {
		ExposedByteArrayOutputStream baos = new ExposedByteArrayOutputStream();
		return executeObjectToBuffer(o, baos);
	}

	public ByteBuffer objectToBuffer(Object o, int estimatedSize) throws IOException, InterruptedException {
		ExposedByteArrayOutputStream baos = new ExposedByteArrayOutputStream(estimatedSize);
		return executeObjectToBuffer(o, baos);
	}

	private ByteBuffer executeObjectToBuffer(Object o, ExposedByteArrayOutputStream baos) throws IOException {
		LOGGER.info(String.format("executeObjectToBuffer object is null: %b", Objects.nonNull(o)));
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		LOGGER.info(String.format("ObjectOutputStream is null: %s", Objects.nonNull(oos)));
		objectToObjectStream(o, oos);
		oos.flush();
		oos.close();
		baos.close();
		byte[] b = baos.toByteArray();
		return new ByteBufferFactoryImpl().newByteBuffer(b, 0, b.length);
	}

	@Override
	public Object objectFromByteBuffer(byte[] buf, int offset, int length) throws IOException, ClassNotFoundException {
		byte[] newBytes = new byte[length];
		System.arraycopy(buf, offset, newBytes, 0, length);
		return objectFromObjectStream(new ObjectInputStream(new ByteArrayInputStream(buf)));
	}

	@Override
	public void start() {
		xs = new XStream();
		// objectMapper = new ObjectMapper();
	}

	@Override
	public void stop() {
		xs = null;
		// objectMapper = null;
	}

	@Override
	public Object objectFromInputStream(InputStream is) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(is);
		return objectFromObjectStream(in);
	}

	@Override
	public byte[] objectToByteBuffer(Object obj, int estimatedSize) throws IOException, InterruptedException {
		ByteBuffer buffer = objectToBuffer(obj, estimatedSize);
		return buffer.getBuf();
	}

	@Override
	public byte[] objectToByteBuffer(Object obj) throws IOException, InterruptedException {
		ByteBuffer buffer = objectToBuffer(obj);
		return buffer.getBuf();
	}

	@Override
	public Object objectFromByteBuffer(byte[] buf) throws IOException, ClassNotFoundException {
		return objectFromObjectStream(new ObjectInputStream(new ByteArrayInputStream(buf)));
	}

	@Override
	public boolean isMarshallable(Object o) throws Exception {
		return (o instanceof Serializable || o instanceof Externalizable);
	}

	@Override
	public BufferSizePredictor getBufferSizePredictor(Object o) {
		return new AdaptiveBufferSizePredictor();
	}
}