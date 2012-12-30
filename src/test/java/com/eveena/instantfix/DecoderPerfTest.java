package com.eveena.instantfix;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;

import com.eveena.instantfix.Decoder;
import com.eveena.instantfix.GenericFixFiller;
import com.eveena.instantfix.GenericFixMessage;

public class DecoderPerfTest {
	private static final int ITERATIONS = 10000;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String msg = "8=FIX.4.110=157"
				+ "8=FIX.4.19=11235=049=BRKR56=INVMGR34=23552=19980604-07:58:28112=19980604-07:58:2810=157"
				+ "8=FIX.4.19=15435=649=BRKR56=INVMGR34=23652=19980604-07:58:4823=11568528=N55=SPMI.MI54=227=20000044=10100.00000025=H10=159";
		byte[] src;
		try {
			src = msg.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("unsupported ISO??");
		}

		// GenericFix
		Decoder<?> dc = new Decoder<GenericFixMessage>(new GenericFixFiller());
		dc.decode();
		System.gc();
		System.out.println("GenericFixMessage");
		testDecoder(src, dc);
		
		dc = new Decoder<ByteFixMessage>(new ByteFixFiller());
		dc.decode();
		System.gc();
		System.out.println("ByteFixMessage");
		testDecoder(src, dc);
		
		
		dc = new Decoder<TupleFixMessage>(new TupleFixFiller());
		dc.decode();
		System.gc();
		System.out.println("TupleFixMessage");
		testDecoder(src, dc);
		
	}
	private static void testDecoder(byte[] src, Decoder<?> dc) {
		for(int k=0;k<10;k++) {
			long start = System.currentTimeMillis();
			for(int j=0;j<ITERATIONS;j++) {
				for (int i = 0; i < src.length; i++) {
					byte[] a = new byte[i];
					byte[] b = new byte[src.length - i];
					System.arraycopy(src, 0, a, 0, i);
					System.arraycopy(src, i, b, 0, src.length - i);
					int c = 0;
					dc.push(a);
					while(dc.decode()!=null)
						c++;
					dc.push(b);
					while(dc.decode()!=null)
						c++;
					Assert.assertEquals("Test failed with i: " + i, 3, c);
				}
			}
			long end = System.currentTimeMillis();

			System.out.format("Msg/s %f%n", (double)ITERATIONS * 3 * src.length / (end - start) * 1000 );
		}
	}

}
