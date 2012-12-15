package com.eveenah.instantfix;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

public class DecoderTest {

	@Test
	public void testNormal() throws UnsupportedEncodingException {
		String msg = "8=FIX.4.110=157" + // header + end
				"8=89=935=3511=1141=4110=10" + // beginString, msgType, checkSum, clOrdID, origClOrdID
				"8=FIX.4.19=11235=049=BRKR56=INVMGR34=23552=19980604-07:58:28112=19980604-07:58:2810=157" + 
				"8=FIX.4.19=15435=649=BRKR56=INVMGR34=23652=19980604-07:58:4823=11568528=N55=SPMI.MI54=227=20000044=10100.00000025=H10=159";
		byte[] src;
		src = msg.getBytes("ISO-8859-1");
		
		Decoder<GenericFix> dc = new Decoder<GenericFix>(new Filler());
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
			Assert.assertEquals("Test failed with i: " + i, 4, c);
		}
	}

}
