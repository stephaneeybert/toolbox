package com.thalasoft.toolbox.utils;

import java.util.UUID;

public class CommonTools {

  public static String formatSortableStringNumber(int num, int digits) {
    String output = Integer.toString(num);
    while (output.length() < digits) {
      output = "0" + output;
    }
    return output;
  }

	public static String generateUniqueId(int length) {
		return UUID.randomUUID().toString().substring(0, length);
	}

	public static boolean validUUID(String uuid) {
		try {
			UUID.fromString(uuid);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static String trimSpaces(String str) {
		if (str != null) {
			return str.replaceAll("\\s+", "");
		} else {
			return null;
		}
	}

	public static String trimNonNumeric(String str) {
		if (str != null) {
			return str.replaceAll("[^\\d]", "");
		} else {
			return null;
		}
	}

}
