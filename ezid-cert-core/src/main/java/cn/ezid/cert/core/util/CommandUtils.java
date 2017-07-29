package cn.ezid.cert.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ezid.cert.core.EzidException;

public class CommandUtils {
	private static final Logger log = LoggerFactory.getLogger(CommandUtils.class);

	public static String executeCommand(String command) throws EzidException {

		Process process = null;		
		try {
			process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			log.error("Execute command {} failed with exception {}.", command, e);
			throw new EzidException(e);
		}
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			StringBuffer output = new StringBuffer();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				output.append(line);
			}
		
			return output.toString();
		} catch (IOException e) {
			log.error("Command {} return result failed with exception {}.", command, e);
			throw new EzidException(e);
		}
	}
}
