package util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MsgLog {

	
	public static void write(String s) {
        try {
            String currentTime = getCurentDateTime("yyyy.MM.dd");
            write(ConstantsUtil.DEFAULT_LOG_FILE + "_" + currentTime + ConstantsUtil.DEFAULT_LOG_FILE_EXTENSION, s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void write(String f, String s) throws IOException {

        String currentTime = getCurentDateTime("yyyy.MM.dd hh:mm:ss a");

        FileWriter aWriter = new FileWriter(f, true);
        aWriter.write("( " + currentTime + " ) : " + s + "\n");
        aWriter.flush();
        aWriter.close();
    }

    private static String getCurentDateTime(String format) {
        //   TimeZone tz = TimeZone.getTimeZone("Europe/Dublin");
        TimeZone tz = TimeZone.getDefault();
        Date now = new Date();

        DateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(tz);

        return df.format(now);
    }
}
