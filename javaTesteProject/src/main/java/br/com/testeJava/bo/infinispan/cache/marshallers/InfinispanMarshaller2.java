package br.com.testeJava.bo.infinispan.cache.marshallers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

import org.infinispan.commons.io.ByteBuffer;
import org.infinispan.commons.io.ByteBufferFactoryImpl;
import org.infinispan.commons.io.ExposedByteArrayOutputStream;
import org.infinispan.commons.marshall.AbstractMarshaller;

import com.thoughtworks.xstream.XStream;

public class InfinispanMarshaller2 extends AbstractMarshaller {

	XStream xs = new XStream();

	private static final Logger LOGGER = Logger.getLogger(InfinispanMarshaller.class.getName());

	@Override
	public Object objectFromByteBuffer(byte[] buf, int offset, int length) throws IOException, ClassNotFoundException {
		byte[] newBytes = new byte[length];
		System.arraycopy(buf, offset, newBytes, 0, length);
		return objectFromObjectStream(new ObjectInputStream(new ByteArrayInputStream(buf)));
	}

	public Object objectFromObjectStream(ObjectInput in) throws IOException, ClassNotFoundException {
		String xml = (String) in.readObject();
		LOGGER.info("Reading: \n" + xml);
		return xs.fromXML(xml);
	}

	@Override
	public boolean isMarshallable(Object o) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected ByteBuffer objectToBuffer(Object o, int estimatedSize) throws IOException, InterruptedException {
		ExposedByteArrayOutputStream baos = new ExposedByteArrayOutputStream(estimatedSize);
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		objectToObjectStream(o, oos);
		oos.flush();
		oos.close();
		baos.close();
		byte[] b = baos.toByteArray();
		return new ByteBufferFactoryImpl().newByteBuffer(b, 0, b.length);
	}

	public void objectToObjectStream(Object obj, ObjectOutput out) throws IOException {
		String xml = xs.toXML(obj);
		LOGGER.info("Writing: \n" + xml);
		out.writeObject(xml);
	}

}
