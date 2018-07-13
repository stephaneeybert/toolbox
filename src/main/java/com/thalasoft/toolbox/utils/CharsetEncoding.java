package com.thalasoft.toolbox.utils;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_16BE;
import static java.nio.charset.StandardCharsets.UTF_16LE;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

public class CharsetEncoding {

	public static final Charset[] TRY_ENC_UTF8_ISO88591_UTF16LE_UTF16BE = new Charset[] { UTF_8, ISO_8859_1, UTF_16LE, UTF_16BE };
	public static final Charset[] TRY_ENC_UTF16LE_UTF16BE_UTF8_ISO88591 = new Charset[] { UTF_16LE, UTF_16BE, UTF_8, ISO_8859_1 };

	private final Charset[] charsets;

	public CharsetEncoding() {
		this(TRY_ENC_UTF8_ISO88591_UTF16LE_UTF16BE);
	}

	public CharsetEncoding(Charset[] charsets) {
		this.charsets = charsets;
	}

	// Detect the charset of a data file
    public Charset detectFileCharset(File f) {
		Charset charset = null;
		BufferedInputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(f));
            byte[] buffer = new byte[512];
            while ((input.read(buffer) != -1) && (charset == null)) {
                charset = guessCharset(buffer);
                if (charset != null) {
                	return charset;
                } else {
                	return null;
                }
            }
        } catch (Exception e) {
			return null;
        } finally {
			try {
				input.close();
			} catch (Exception e) {
				return null;
			}
		}
        return null;
    }
	
	private Charset guessCharset(byte[] chars) throws Exception {
		for (Charset charset : charsets) {
			try {
				decode(chars, charset);
				return charset;
			} catch (CharacterCodingException e) {
			}
		}
		throw new IllegalStateException("Unable to encode string using these encodings: " + Arrays.toString(charsets));
	}

	private String decode(byte[] chars, Charset encodingToTry) throws CharacterCodingException {
		CharsetDecoder decoder = encodingToTry.newDecoder().onMalformedInput(CodingErrorAction.REPORT);
		ByteBuffer byteBuffer = ByteBuffer.wrap(chars);
		CharBuffer decoded = decoder.decode(byteBuffer);
		return decoded.toString();
	}

}
